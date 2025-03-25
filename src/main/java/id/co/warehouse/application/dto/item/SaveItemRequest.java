package id.co.warehouse.application.dto.item;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaveItemRequest implements Serializable {
    private static final long serialVersionUID = -5736891265159161015L;

    @NonNull
    private String name;
    @NonNull
    private Integer price;

}
