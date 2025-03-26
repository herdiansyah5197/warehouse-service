package id.co.warehouse.application.dto.order;

import id.co.warehouse.application.model.Item;
import id.co.warehouse.application.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListOrderResponsePagination implements Serializable {

    private static final long serialVersionUID = 9172031145194395859L;
    private int totalPages;
    private long totalElements;
    private int numberOfElements;
    private int pageNumber;
    private List<Order> listItem;
}
