package id.co.warehouse.application.service;

import id.co.warehouse.application.dto.GetListRequestPagination;
import id.co.warehouse.application.dto.item.GetListItemResponsePagination;
import id.co.warehouse.application.dto.order.*;
import id.co.warehouse.application.model.Order;

public interface OrderService {

    Order getorderById(String id);

    SaveOrderResponse saveOrder(SaveOrderRequest request);

    SaveOrderResponse updateOrder(UpdateOrderRequest updateOrderRequest);

    OrderDeleteResponse deleteOrder( String id);

    GetListOrderResponsePagination getlist(GetListRequestPagination requestPagination);
}
