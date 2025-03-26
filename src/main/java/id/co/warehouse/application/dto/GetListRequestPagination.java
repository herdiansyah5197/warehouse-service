package id.co.warehouse.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetListRequestPagination implements Serializable {
    private static final long serialVersionUID = 1881472860301340931L;
    @NotNull
    private Integer pageSize;
    @NotNull
    private Integer pageNumber;
}
