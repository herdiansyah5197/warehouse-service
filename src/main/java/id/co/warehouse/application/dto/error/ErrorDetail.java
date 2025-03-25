package id.co.warehouse.application.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetail implements Serializable {
    private static final long serialVersionUID = 4054049738003949216L;

    private String massageError;

}
