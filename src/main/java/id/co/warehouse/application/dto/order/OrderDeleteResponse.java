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
public class OrderDeleteResponse implements Serializable {
    private static final long serialVersionUID = -8992999552367104305L;

    private boolean isSuccess;
    private String message;
}
