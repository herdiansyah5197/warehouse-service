package id.co.warehouse.application.dto.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateOrderRequest implements Serializable {
    private static final long serialVersionUID = -2849197132868012427L;

    private Long idItem;
    @NotNull
    private String orderId;
    private Integer qty;
    @JsonIgnore
    private Integer price;

}
