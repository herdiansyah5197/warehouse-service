package id.co.warehouse.application.dto.inventory;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveInventoryRequest implements Serializable {
    private static final long serialVersionUID = -5736891265159161015L;

    @NonNull
    private Long idItem;
    private Integer qty;
    private String type;

}
