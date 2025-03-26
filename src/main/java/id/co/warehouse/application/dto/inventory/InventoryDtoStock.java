package id.co.warehouse.application.dto.inventory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "inventory")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDtoStock implements Serializable {
    private static final long serialVersionUID = 6589106571178593742L;

    Long itemId;
    String name;
    BigDecimal totalStock;

}
