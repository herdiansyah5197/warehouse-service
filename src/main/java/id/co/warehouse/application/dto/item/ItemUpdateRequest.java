package id.co.warehouse.application.dto.item;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateRequest implements Serializable {

    private static final long serialVersionUID = -7314409018873692661L;

    @NotNull
    private Long id;
    private String name;
    private Integer price;

}
