package id.co.warehouse.application.dto.item;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveItemRequest implements Serializable {
    private static final long serialVersionUID = -5736891265159161015L;

    @NotNull
    private String name;
    @NotNull
    private Integer price;

}
