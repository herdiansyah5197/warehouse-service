package id.co.warehouse.application.dto.inventory;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryUpdateRequest implements Serializable {

    private static final long serialVersionUID = 9097767837760255555L;

    @NotNull
    private Long id;
    private Long idItem;
    private String type;
    private Integer qty;

}
