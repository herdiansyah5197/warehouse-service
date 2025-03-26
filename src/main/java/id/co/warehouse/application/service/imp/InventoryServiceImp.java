package id.co.warehouse.application.service.imp;

import id.co.warehouse.application.constant.MessageConstant;
import id.co.warehouse.application.dto.GetListRequestPagination;
import id.co.warehouse.application.dto.inventory.*;
import id.co.warehouse.application.dto.item.GetListItemResponsePagination;
import id.co.warehouse.application.dto.item.ItemDeleteResponse;
import id.co.warehouse.application.dto.item.ItemUpdateRequest;
import id.co.warehouse.application.dto.item.ItemUpdateResponse;
import id.co.warehouse.application.exception.ErrorBussinessException;
import id.co.warehouse.application.exception.GeneralErrorException;
import id.co.warehouse.application.model.Inventory;
import id.co.warehouse.application.model.Item;
import id.co.warehouse.application.repository.InventoryRepository;
import id.co.warehouse.application.repository.ItemRepository;
import id.co.warehouse.application.service.InventoryService;
import id.co.warehouse.application.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class InventoryServiceImp implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    ItemRepository itemRepository;

    @Override
    public SaveInventoryResponse saveInventory(SaveInventoryRequest saveInventoryRequest) {
        SaveInventoryResponse response = SaveInventoryResponse.builder().build();
        try {
            validaFormatType(saveInventoryRequest.getType());
            var respItm = itemRepository.findById(saveInventoryRequest.getIdItem());
            if (!respItm.isPresent()) {
                throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
            }
//            if(saveInventoryRequest.getType().equalsIgnoreCase("W")) {
//                validateStock(saveInventoryRequest.getQty(), saveInventoryRequest.getIdItem());
//            }
            inventoryRepository.saveAndFlush(populateNewData(saveInventoryRequest));
            response.setSuccess(true);
        } catch (GeneralErrorException e) {
            log.error("error save data");
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public GetInventoryStockResponse getStockByIdItem(Long id) {
        GetInventoryStockResponse response = GetInventoryStockResponse.builder().build();
        try {
            response.setIdItem(id);
            var respItm = itemRepository.findById(id);
            if (!respItm.isPresent()) {
                throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
            }
            if (respItm.isPresent()) {
                Item item = (Item) respItm.get();
                response.setNameItem(item.getName());
                var respDB = inventoryRepository.findStockByIdItem(id);
                if (respDB.isPresent()) {
                    response.setStockQty(Math.toIntExact(respDB.get()));
                } else {
                    response.setStockQty(0);
                }
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
    public Inventory getinventory(Long id) {

        Inventory response;
        try {
            var respItm = inventoryRepository.findById(id);
            if (respItm.isPresent()) {
                response = (Inventory) respItm.get();
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
    public InventoryUpdateResponse updateInventory(InventoryUpdateRequest inventoryUpdateRequest) {
        InventoryUpdateResponse response = InventoryUpdateResponse.builder().build();
        try {
            var type = inventoryUpdateRequest.getType();
            if (null != type) {
                validaFormatType(type);
            }
            var respItm = inventoryRepository.findById(inventoryUpdateRequest.getId());
            if (respItm.isPresent()) {
                Inventory inventory = populateUpdateDataItem(inventoryUpdateRequest, respItm.get());
                inventoryRepository.saveAndFlush(inventory);
                response.setIdItem(inventory.getItem().getId());
                response.setQty(inventory.getQty());
                response.setType(inventory.getType());
                response.setUpdate(true);
                response.setId(inventoryUpdateRequest.getId());
                response.setMessage("id inventory " + inventoryUpdateRequest.getId() + " successfully updated");
            } else {
                throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
            }
        } catch (GeneralErrorException e) {
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public GetListInventoryResponsePagination getlist(GetListRequestPagination requestPagination) {
        GetListInventoryResponsePagination response = GetListInventoryResponsePagination.builder().build();
        try {
            var pageSize = requestPagination.getPageSize();
            var pageNumber = requestPagination.getPageNumber();
            var respPaging = inventoryRepository.findAll(PaginationUtil.setPagination(pageNumber, pageSize));
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

    @Override
    public GetListStockResponsePagination getlistStock(GetListRequestPagination requestPagination) {
        GetListStockResponsePagination response = GetListStockResponsePagination.builder().build();
        try {
            var pageSize = requestPagination.getPageSize();
            var pageNumber = requestPagination.getPageNumber();
            var respPaging = inventoryRepository.findListStockNative(PaginationUtil.setPagination(pageNumber, pageSize));
            response.setTotalPages(respPaging.getTotalPages());
            response.setPageNumber(response.getPageNumber());
            response.setTotalElements(respPaging.getTotalElements());
            response.setNumberOfElements(respPaging.getNumberOfElements());
            response.setListItem(populateListItem(respPaging.get()));
        } catch (GeneralErrorException e) {
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public InventoryDeleteResponse deleteInventory(Long id) {
        InventoryDeleteResponse response = InventoryDeleteResponse.builder().build();
        try {
            var respInventory = inventoryRepository.findById(id);
            log.info("respInventory update : {}" , respInventory);
            if (!respInventory.isPresent()) {
                throw new ErrorBussinessException(MessageConstant.DATA_NOT_FOUND);
            }
            inventoryRepository.deleteById(id);
            response.setMessage("id inventory " + id + " successfully deleted");
            response.setSuccess(true);
        } catch (GeneralErrorException e) {
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    protected Inventory populateNewData(SaveInventoryRequest request) {
        Inventory inventory = new Inventory();
        Item item = new Item();
        item.setId(request.getIdItem());
        inventory.setItem(item);
        inventory.setQty(request.getQty());
        inventory.setType(request.getType());
        return inventory;
    }

    protected Inventory populateUpdateDataItem(InventoryUpdateRequest request, Inventory inventory) {
        var qty = null == request.getQty() ? inventory.getQty() : request.getQty();
        var type = null == request.getType() ? inventory.getType() : request.getType();
        var idItem = null == request.getIdItem() ? inventory.getItem().getId() : request.getIdItem();
        var item = new Item();
        item.setId(idItem);
        inventory.setItem(item);
        inventory.setQty(qty);
        inventory.setType(type);
        return inventory;
    }

    protected List<InventoryDtoStock> populateListItem(Stream<Object[]> objects) {
        List<InventoryDtoStock> resp = new ArrayList<>();
        try {
            objects.forEach(obj -> {
                Long itemId = (Long) obj[0];
                String name = (String) obj[1];
                BigDecimal totalStock = (BigDecimal) obj[2];
                log.info("value Objeck idName: {}, name {}, totalStock :{}", itemId, name, totalStock);
                resp.add(InventoryDtoStock.builder()
                        .itemId(itemId)
                        .name(name)
                        .totalStock(totalStock)
                        .build());
            });
        } catch (GeneralErrorException e) {
            throw new GeneralErrorException(e.getMessage());
        }
        return resp;
    }

    public void validateStock(Integer withdrawel, Long idItem) {
        var result = inventoryRepository.findStockByIdItem(idItem);
        if (result.isPresent()) {
            var qtyStock = result.get();
            if (qtyStock < withdrawel) {
                throw new ErrorBussinessException(MessageConstant.INSUFFICIENT_STOCK);
            }
        }
    }

    public void validaFormatType(String type) {
        Set<String> validTypes = Set.of("T", "W");
        if (!validTypes.contains(type.toUpperCase())) {
            throw new ErrorBussinessException(MessageConstant.FORMAT_DATA_NOT_FOUND);
        }
    }
}
