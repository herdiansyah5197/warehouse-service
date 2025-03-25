package id.co.warehouse.application.service;

import id.co.warehouse.application.dto.inventory.GetInventoryStockResponse;
import id.co.warehouse.application.dto.inventory.SaveInventoryRequest;
import id.co.warehouse.application.dto.inventory.SaveInventoryResponse;
import id.co.warehouse.application.model.Inventory;

public interface InventoryService {

    SaveInventoryResponse saveInventory (SaveInventoryRequest saveInventoryRequest);

    GetInventoryStockResponse getStockByIdItem(Long id);

    Inventory getinventory(Long id);


}
