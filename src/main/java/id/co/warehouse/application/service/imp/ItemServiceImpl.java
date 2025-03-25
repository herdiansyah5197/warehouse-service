package id.co.warehouse.application.service.imp;

import id.co.warehouse.application.dto.item.*;
import id.co.warehouse.application.exception.ErrorBussinessException;
import id.co.warehouse.application.exception.GeneralErrorException;
import id.co.warehouse.application.model.Item;
import id.co.warehouse.application.repository.ItemRepository;
import id.co.warehouse.application.service.ItemService;
import id.co.warehouse.application.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    private static final String DATA_NOT_FOUND = "Data Not Found";

    @Override
    public Item getItemById(Long id) {
        Item response;
        try {
            var respItm = itemRepository.findById(id);
            if (respItm.isPresent()) {
                response = (Item) respItm.get();
            } else {
                throw new ErrorBussinessException(DATA_NOT_FOUND);
            }
        } catch (GeneralErrorException e) {
            log.error("error get data");
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public SaveItemResponse saveItem(SaveItemRequest saveItemRequest) {
        SaveItemResponse response = SaveItemResponse.builder().build();
        try {
            itemRepository.saveAndFlush(populateNewDataItem(saveItemRequest));
            response.setSuccess(true);
        } catch (GeneralErrorException e) {
            log.error("error save data");
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public GetListItemResponsePagination getList(GetListItemRequestPagination requestPagination) {
        GetListItemResponsePagination response = GetListItemResponsePagination.builder().build();
        try {
            var pageSize = requestPagination.getPageSize();
            var pageNumber = requestPagination.getPageNumber();
            var respPaging = itemRepository.findAll(PaginationUtil.setPagination(pageNumber,pageSize));
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
    public ItemUpdateResponse updateItem(ItemUpdateRequest request) {
        ItemUpdateResponse response = ItemUpdateResponse.builder().build();
        try {
            var respItm = itemRepository.findById(request.getId());
            if (respItm.isPresent()) {
                Item item = populateUpdateDataItem(request,(Item) respItm.get());
                itemRepository.saveAndFlush(item);
                response.isUpdate();
                response.setMassage("id item " + request.getId() + " successfully updated");
            }else {
                throw new ErrorBussinessException(DATA_NOT_FOUND);
            }
        } catch (GeneralErrorException e) {
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public ItemDeleteResponse deleteItem(Long id) {
        ItemDeleteResponse response = ItemDeleteResponse.builder().build();
        try{
            var respItm = itemRepository.findById(id);
            if (!respItm.isPresent()) {
                throw new ErrorBussinessException(DATA_NOT_FOUND);
            }
            itemRepository.deleteById(id);
            response.setMessage("id item " + id + " successfully deleted");
            response.setSuccess(true);
        }catch (GeneralErrorException e){
            throw new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    protected Item populateNewDataItem(SaveItemRequest request) {
        Item item = new Item();
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        return item;
    }

    protected Item populateUpdateDataItem(ItemUpdateRequest request, Item item) {
        var name = null==request.getName()?item.getName():request.getName();
        var price = null==request.getPrice()?item.getPrice():request.getPrice();
        item.setName(name);
        item.setPrice(price);
        return item;
    }

}
