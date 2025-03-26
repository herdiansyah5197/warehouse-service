package id.co.warehouse.application.service.imp;

import id.co.warehouse.application.constant.MessageConstant;
import id.co.warehouse.application.dto.GetListRequestPagination;
import id.co.warehouse.application.dto.inventory.GetListInventoryResponsePagination;
import id.co.warehouse.application.dto.inventory.SaveInventoryRequest;
import id.co.warehouse.application.dto.item.*;
import id.co.warehouse.application.dto.order.*;
import id.co.warehouse.application.exception.ErrorBussinessException;
import id.co.warehouse.application.exception.GeneralErrorException;
import id.co.warehouse.application.model.Inventory;
import id.co.warehouse.application.model.Item;
import id.co.warehouse.application.model.Order;
import id.co.warehouse.application.repository.InventoryRepository;
import id.co.warehouse.application.repository.ItemRepository;
import id.co.warehouse.application.repository.OrderRepository;
import id.co.warehouse.application.service.InventoryService;
import id.co.warehouse.application.service.ItemService;
import id.co.warehouse.application.service.OrderService;
import id.co.warehouse.application.util.PaginationUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    InventoryRepository inventoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order getorderById(String id) {
        Order response;
        try {
            var respItm = orderRepository.findByOrderId(id);
            if (respItm.isPresent()) {
                response = (Order) respItm.get();
            } else {
                throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
            }
        } catch (GeneralErrorException e) {
            log.error("error get data");
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public SaveOrderResponse saveOrder(SaveOrderRequest saveOrderRequest) {
        SaveOrderResponse response = SaveOrderResponse.builder().build();
        try {
            var idItem = saveOrderRequest.getIdItem();
            var respItm = itemRepository.findById(idItem);
            if (!respItm.isPresent()) {
                throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
            }
            var item = (Item)respItm.get();
            var qty = saveOrderRequest.getQty();
            var price = item.getPrice();
            saveOrderRequest.setPrice(item.getPrice());
            inventoryService.validateStock(qty, idItem);
            orderRepository.saveAndFlush(populateNewData(saveOrderRequest));
            //update stock
            updateStock(qty, idItem);

            response.setQty(qty);
            response.setIdItem(idItem);
            response.setPrice(price);
            response.setSuccess(true);
        } catch (GeneralErrorException e) {
            log.error("error save data");
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public SaveOrderResponse updateOrder(UpdateOrderRequest updateOrderRequest) {
        SaveOrderResponse response = SaveOrderResponse.builder().build();
        try {
            Item item  = Item.builder().build();
            if(null!=updateOrderRequest.getIdItem()) {
                var respItm = itemRepository.findById(updateOrderRequest.getIdItem());
                if (!respItm.isPresent()) {
                    throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
                }
                item = (Item) respItm.get();
            }
            var respOrder = orderRepository.findByOrderId(updateOrderRequest.getOrderId());
            if (respOrder.isPresent()) {
                Order order = populateUpdateData(updateOrderRequest,(Order) respOrder.get(),item);
                orderRepository.saveAndFlush(order);
                response.setPrice(order.getPrice());
                response.setSuccess(true);
                response.setQty(order.getQty());
                response.setIdItem(order.getItem().getId());
            }else {
                throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
            }
        } catch (GeneralErrorException e) {
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    @Transactional
    public OrderDeleteResponse deleteOrder(String id) {
        OrderDeleteResponse response = OrderDeleteResponse.builder().build();
        try{
            validateOrderById(id);
            var respItm = orderRepository.findByOrderId(id);
            if (!respItm.isPresent()) {
                throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
            }else {
                entityManager.remove(respItm.get());
            }
            response.setMessage("id order " + id + " successfully deleted");
            response.setSuccess(true);
        }catch (GeneralErrorException e){
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public GetListOrderResponsePagination getlist(GetListRequestPagination requestPagination) {
        GetListOrderResponsePagination response = GetListOrderResponsePagination.builder().build();
        try {
            var pageSize = requestPagination.getPageSize();
            var pageNumber = requestPagination.getPageNumber();
            var respPaging = orderRepository.findAll(PaginationUtil.setPagination(pageNumber, pageSize));
            response.setTotalPages(respPaging.getTotalPages());
            response.setPageNumber(response.getPageNumber());
            response.setTotalElements(respPaging.getTotalElements());
            response.setNumberOfElements(respPaging.getNumberOfElements());
            response.setListItem(respPaging.toList());
        } catch (GeneralErrorException e) {
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    protected Order populateNewData(SaveOrderRequest request) {
        Order order = new Order();
        var item = new Item();
        item.setId(request.getIdItem());
        order.setItem(item);
        order.setQty(request.getQty());
        order.setPrice(request.getPrice());
        return order;
    }

    protected void updateStock(Integer qty, Long idItem) {
        try {
            var requestPopulate = SaveInventoryRequest.builder().build();
            requestPopulate.setType("W");
            requestPopulate.setQty(qty);
            requestPopulate.setIdItem(idItem);
            Inventory saveInventoryRequest = inventoryService.populateNewData(requestPopulate);
            inventoryRepository.saveAndFlush(saveInventoryRequest);
        } catch (Exception e) {
            log.error("failed update stock");
        }
    }

    protected Order populateUpdateData(UpdateOrderRequest request, Order order, Item item) {
        var qty = null == request.getQty() ? order.getQty() : request.getQty();
        var idItem = null == request.getIdItem() ? order.getItem().getId() : request.getIdItem();
        var updateItem = idItem==item.getId();
        item.setId(idItem);
        order.setItem(item);
        order.setQty(qty);
        order.setPrice(updateItem?item.getPrice():order.getPrice());
        return order;
    }

    public void validateOrderById(String id){
        var respItm = orderRepository.findByOrderId(id);
        if (!respItm.isPresent()) {
            throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
        }
    }
}
