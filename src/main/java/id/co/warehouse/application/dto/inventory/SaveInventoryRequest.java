package id.co.warehouse.application.dto.inventory;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveInventoryRequest implements Serializable {
    private static final long serialVersionUID = 2356764568839453669L;

    @NotNull
    private Long idItem;
    private Integer qty;
    private String type;

}
