package id.co.warehouse.application.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryUpdateResponse implements Serializable {

    private static final long serialVersionUID = -88313884057343807L;

    private boolean isUpdate;
    private String message;
    private Long id;
    private Long idItem;
    private String type;
    private Integer qty;

}
