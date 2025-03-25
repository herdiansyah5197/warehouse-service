package id.co.warehouse.application.dto.item;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateRequest implements Serializable {

    private static final long serialVersionUID = -7314409018873692661L;

    @NonNull
    private Long id;
    private String name;
    private Integer price;

}
