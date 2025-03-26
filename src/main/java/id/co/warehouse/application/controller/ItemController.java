package id.co.warehouse.application.controller;

import id.co.warehouse.application.dto.GetListRequestPagination;
import id.co.warehouse.application.dto.item.*;
import id.co.warehouse.application.model.Item;
import id.co.warehouse.application.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/getbyid/{id}")
    public Item getItemById(@PathVariable Long id){
        return itemService.getItemById(id);
    }

    @GetMapping("/getlist")
    public GetListItemResponsePagination getlist(@Valid @RequestBody GetListRequestPagination requestPagination){
        return itemService.getList(requestPagination);
    }
    @PostMapping("/save")
    public SaveItemResponse createdUsers(@Valid @RequestBody SaveItemRequest request) {
        return itemService.saveItem(request);
    }

    @PutMapping("/edit")
    public ItemUpdateResponse updateItem(@Valid @RequestBody ItemUpdateRequest itemUpdateRequest){
        return itemService.updateItem(itemUpdateRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ItemDeleteResponse deleteResponse(@PathVariable Long id){
        return itemService.deleteItem(id);
    }




}
