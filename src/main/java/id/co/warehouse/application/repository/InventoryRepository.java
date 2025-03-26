package id.co.warehouse.application.repository;

import id.co.warehouse.application.dto.inventory.InventoryDtoStock;
import id.co.warehouse.application.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {



    @Query(value = "SELECT " +
            "    SUM(CASE WHEN i.\"TYPE\" = 'T' THEN i.qty ELSE 0 END) - " +
            "    SUM(CASE WHEN i.\"TYPE\" = 'W' THEN i.qty ELSE 0 END) AS total_stock " +
            "FROM DVF.INVENTORY i WHERE i.ITEM_ID = :idItem", nativeQuery = true)
    Optional<Long> findStockByIdItem(@Param("idItem") Long idItem);
    @Query(value = "SELECT i.ITEM_ID, itm.NAME, " +
            "SUM(CASE WHEN i.TYPE = 'T' THEN i.qty ELSE 0 END) - " +
            "SUM(CASE WHEN i.TYPE = 'W' THEN i.qty ELSE 0 END) AS total_stock " +
            "FROM DVF.INVENTORY i JOIN DVF.ITEM itm ON i.ITEM_ID = itm.ID " +
            "GROUP BY i.ITEM_ID, itm.NAME", nativeQuery = true)
    Page<Object[]> findListStockNative(Pageable pageable);

}
