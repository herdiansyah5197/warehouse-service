package id.co.warehouse.application.controller;

import id.co.warehouse.application.dto.GetListRequestPagination;
import id.co.warehouse.application.dto.inventory.*;
import id.co.warehouse.application.model.Inventory;
import id.co.warehouse.application.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/getstockbyiditem/{id}")
    public GetInventoryStockResponse getStock(@PathVariable Long id){
        return inventoryService.getStockByIdItem(id);
    }

    @GetMapping("/getbyid/{id}")
    public Inventory getInventory(@PathVariable Long id){
        return inventoryService.getinventory(id);
    }

    @GetMapping("/getlist")
    public GetListInventoryResponsePagination getlist(@Valid @RequestBody GetListRequestPagination requestPagination){
        return inventoryService.getlist(requestPagination);
    }

    @GetMapping("/getliststock")
    public GetListStockResponsePagination getlistStock(@Valid @RequestBody GetListRequestPagination requestPagination){
        return inventoryService.getlistStock(requestPagination);
    }


    @PostMapping("/save")
    public SaveInventoryResponse createdUsers(@Valid @RequestBody  SaveInventoryRequest request) {
        return inventoryService.saveInventory(request);
    }
    @PutMapping("/edit")
    public InventoryUpdateResponse updateInventory(@Valid @RequestBody InventoryUpdateRequest itemUpdateRequest){
        return inventoryService.updateInventory(itemUpdateRequest);
    }

    @DeleteMapping("/delete/{id}")
    public InventoryDeleteResponse deleteInventory(@PathVariable Long id){
        return inventoryService.deleteInventory(id);
    }


}
