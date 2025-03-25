package id.co.warehouse.application.dto.item;

import id.co.warehouse.application.model.Item;
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
public class GetListItemResponsePagination implements Serializable {

    private static final long serialVersionUID = 9172031145194395859L;
    private int totalPages;
    private long totalElements;
    private int numberOfElements;
    private int pageNumber;
    private List<Item> listItem;
}
