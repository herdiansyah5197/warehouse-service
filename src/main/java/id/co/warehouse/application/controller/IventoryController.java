package id.co.warehouse.application.controller;

import id.co.warehouse.application.dto.inventory.GetInventoryStockResponse;
import id.co.warehouse.application.dto.inventory.SaveInventoryRequest;
import id.co.warehouse.application.dto.inventory.SaveInventoryResponse;
import id.co.warehouse.application.dto.item.*;
import id.co.warehouse.application.model.Inventory;
import id.co.warehouse.application.model.Item;
import id.co.warehouse.application.service.InventoryService;
import id.co.warehouse.application.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iventory")
public class IventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getstockbyiditem/{id}")
    public GetInventoryStockResponse getStock(@PathVariable Long id){
        return inventoryService.getStockByIdItem(id);
    }

    @GetMapping("/getiditem/{id}")
    public Inventory getInventory(@PathVariable Long id){
        return inventoryService.getinventory(id);
    }

    @PostMapping("/save")
    public SaveInventoryResponse createdUsers(@RequestBody SaveInventoryRequest request) {
        return inventoryService.saveInventory(request);
    }

}
