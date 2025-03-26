package id.co.warehouse.application.service;

import id.co.warehouse.application.dto.GetListRequestPagination;
import id.co.warehouse.application.dto.inventory.*;
import id.co.warehouse.application.model.Inventory;

public interface InventoryService {

    SaveInventoryResponse saveInventory (SaveInventoryRequest saveInventoryRequest);

    GetInventoryStockResponse getStockByIdItem(Long id);

    Inventory getinventory(Long id);

    public InventoryUpdateResponse updateInventory(InventoryUpdateRequest itemUpdateRequest);

    GetListInventoryResponsePagination getlist(GetListRequestPagination requestPagination);
    GetListStockResponsePagination getlistStock(GetListRequestPagination requestPagination);

    InventoryDeleteResponse deleteInventory(Long id);
    void validateStock(Integer withdrawel, Long idItem);

}
