package id.co.warehouse.application.repository;

import id.co.warehouse.application.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {



    @Query(value = "SELECT " +
            "    SUM(CASE WHEN i.\"TYPE\" = 'T' THEN i.qty ELSE 0 END) - " +
            "    SUM(CASE WHEN i.\"TYPE\" = 'W' THEN i.qty ELSE 0 END) AS total_stock " +
            "FROM DVF.INVENTORY i WHERE i.ITEM_ID = :idItem", nativeQuery = true)
    Optional<Long> findStockByIdItem(@Param("idItem") Long idItem);
}
