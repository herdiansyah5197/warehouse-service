package id.co.warehouse.application.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SaveOrderResponse implements Serializable {
    private static final long serialVersionUID = 5225003007530019260L;

    private boolean isSuccess;
    private Long idItem;
    private Integer price;
    private Integer qty;
}
