package id.co.warehouse.application.dto.inventory;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveInventoryRequest implements Serializable {
    private static final long serialVersionUID = -5736891265159161015L;

    @NotNull
    private Long idItem;
    private Integer qty;
    private String type;

}
