package id.co.warehouse.application.dto.item;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateResponse implements Serializable {

    private static final long serialVersionUID = 4600217859858375915L;

    private String name;
    private int price;
    private boolean isUpdate;
    private String message;

}
