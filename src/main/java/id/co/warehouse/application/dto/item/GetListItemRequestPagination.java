package id.co.warehouse.application.dto.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListItemRequestPagination implements Serializable {
    private static final long serialVersionUID = 1881472860301340931L;
    private Integer pageSize;
    private Integer pageNumber;
}
