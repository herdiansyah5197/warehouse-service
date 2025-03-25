package id.co.warehouse.application.dto.inventory;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInventoryStockResponse implements Serializable {
    private static final long serialVersionUID = -9049929020484592275L;
    private Long idItem;
    private Integer stockQty;
    private String nameItem;

}
