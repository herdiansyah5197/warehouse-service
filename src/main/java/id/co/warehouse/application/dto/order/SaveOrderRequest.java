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
public class SaveOrderRequest implements Serializable {
    private static final long serialVersionUID = -5736891265159161015L;

    @NotNull
    private Long idItem;
    private Integer qty;
    @JsonIgnore
    private Integer price;

}
