package id.co.warehouse.application.dto.inventory;

import id.co.warehouse.application.model.Inventory;
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
public class GetListStockResponsePagination implements Serializable {

    private static final long serialVersionUID = 2780164448850066064L;
    private int totalPages;
    private long totalElements;
    private int numberOfElements;
    private int pageNumber;
    private List<InventoryDtoStock> listItem;
}
