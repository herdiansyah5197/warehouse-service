package id.co.warehouse.application.controller;

import id.co.warehouse.application.dto.item.*;
import id.co.warehouse.application.model.Item;
import id.co.warehouse.application.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/getbyid/{id}")
    public Item getItemById(@PathVariable Long id){
        return itemService.getItemById(id);
    }

    @GetMapping("/getlist")
    public GetListItemResponsePagination getlist(@RequestBody GetListItemRequestPagination requestPagination){
        return itemService.getList(requestPagination);
    }
    @PostMapping("/save")
    public SaveItemResponse createdUsers(@RequestBody SaveItemRequest request) {
        return itemService.saveItem(request);
    }

    @PutMapping("/edit")
    public Item updateItem(@RequestBody ItemUpdateRequest itemUpdateRequest){
        return itemService.updateItem(itemUpdateRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ItemDeleteResponse deleteResponse(@PathVariable Long id){
        return itemService.deleteItem(id);
    }




}
