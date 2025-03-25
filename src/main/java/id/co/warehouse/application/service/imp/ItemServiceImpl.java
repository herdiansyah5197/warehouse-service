package id.co.warehouse.application.service.imp;

import id.co.warehouse.application.dto.item.*;
import id.co.warehouse.application.exception.ErrorBussinessException;
import id.co.warehouse.application.exception.GeneralErrorException;
import id.co.warehouse.application.model.Item;
import id.co.warehouse.application.repository.ItemRepository;
import id.co.warehouse.application.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {


    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item getItemById(Long id) {
        Item response = new Item();
        try {
            var respItm = itemRepository.findById(id);
            if (respItm.isPresent()) {
                response = (Item) respItm.get();
            } else {
                throw new ErrorBussinessException("Data Not Found");
            }
        } catch (GeneralErrorException e) {
            log.error("error get data");
            new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public SaveItemResponse saveItem(SaveItemRequest saveItemRequest) {
        SaveItemResponse response = SaveItemResponse.builder().build();
        try {
            itemRepository.saveAndFlush(populateDataNewItem(saveItemRequest));
            response.setSuccess(true);
        }catch (GeneralErrorException e){
            log.error("error save data");
            new GeneralErrorException(e.getMessage());
        }
        return response;
    }

    @Override
    public GetListItemResponsePagination getList(GetListItemRequestPagination requestPagination) {
        return null;
    }

    @Override
    public Item updateItem(ItemUpdateRequest item) {
        return null;
    }

    @Override
    public ItemDeleteResponse deleteItem(Long id) {
        return null;
    }

    protected Item populateDataNewItem(SaveItemRequest request){
        Item item = new Item();
        item.setName(request.getName());
        item.setPrice(request.getPrice());
        return item;
    }
}
