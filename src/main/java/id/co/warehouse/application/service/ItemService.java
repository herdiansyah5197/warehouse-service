package id.co.warehouse.application.service;

import id.co.warehouse.application.dto.item.*;
import id.co.warehouse.application.model.Item;

public interface ItemService {


    Item getItemById(Long id);
    SaveItemResponse saveItem(SaveItemRequest saveItemRequest);
    GetListItemResponsePagination getList(GetListItemRequestPagination requestPagination);
    Item updateItem(ItemUpdateRequest item);
    ItemDeleteResponse deleteItem(Long id);

}
