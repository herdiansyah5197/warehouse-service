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
public class SaveInventoryResponse implements Serializable {
    private static final long serialVersionUID = -1503401733995653108L;

    private boolean isSuccess;
}
