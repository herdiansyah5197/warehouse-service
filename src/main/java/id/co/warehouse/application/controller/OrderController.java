package id.co.warehouse.application.controller;

import id.co.warehouse.application.dto.GetListRequestPagination;
import id.co.warehouse.application.dto.inventory.SaveInventoryRequest;
import id.co.warehouse.application.dto.inventory.SaveInventoryResponse;
import id.co.warehouse.application.dto.item.GetListItemResponsePagination;
import id.co.warehouse.application.dto.item.ItemDeleteResponse;
import id.co.warehouse.application.dto.item.ItemUpdateRequest;
import id.co.warehouse.application.dto.item.ItemUpdateResponse;
import id.co.warehouse.application.dto.order.*;
import id.co.warehouse.application.model.Inventory;
import id.co.warehouse.application.model.Order;
import id.co.warehouse.application.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderService orderService;
    @GetMapping("/getbyid/{id}")
    public Order getorderById(@PathVariable String id){
        return orderService.getorderById(id);
    }
    @PostMapping("/save")
    public SaveOrderResponse saveOrder(@Valid @RequestBody SaveOrderRequest request) {
        return orderService.saveOrder(request);
    }

    @PutMapping("/edit")
    public SaveOrderResponse updateItem(@Valid @RequestBody UpdateOrderRequest updateOrderRequest){
        return orderService.updateOrder(updateOrderRequest);
    }

    @DeleteMapping("/delete/{id}")
    public OrderDeleteResponse deleteOrder(@PathVariable String id){
        return orderService.deleteOrder(id);
    }

    @GetMapping("/getlist")
    public GetListOrderResponsePagination getlist(@Valid @RequestBody GetListRequestPagination requestPagination){
        return orderService.getlist(requestPagination);
    }

}
