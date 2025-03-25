package id.co.warehouse.application.dto.item;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListItemRequestPagination implements Serializable {
    private static final long serialVersionUID = 1881472860301340931L;
    @NonNull
    private Integer pageSize;
    @NonNull
    private Integer pageNumber;
}
