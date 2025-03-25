package id.co.warehouse.application.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDeleteResponse implements Serializable {
    private static final long serialVersionUID = -4685900138143822763L;

    private boolean isSuccess;
    private String message;
}
