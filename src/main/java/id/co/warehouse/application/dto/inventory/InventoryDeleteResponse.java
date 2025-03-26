package id.co.warehouse.application.dto.inventory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDeleteResponse implements Serializable {
    private static final long serialVersionUID = 4991977364110676208L;

    private boolean isSuccess;
    private String message;
}
