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

//
//    @Query(value = "SELECT new id.co.warehouse.application.dto.inventory.InventoryDtoStock( " +
//            "i.itemId, itm.name, " +
//            "    SUM(CASE WHEN i.\"TYPE\" = 'T' THEN i.qty ELSE 0 END) - " +
//            "    SUM(CASE WHEN i.\"TYPE\" = 'W' THEN i.qty ELSE 0 END) AS totalStock )" +
//            "FROM DVF.INVENTORY i JOIN DVF.ITEM itm ON i.itemId = itm.ID " +
//            "GROUP BY i.itemId, itm.name")
//@Query(value = "SELECT i.ITEM_ID AS iditem, itm.NAME ," +
//        "    SUM(CASE WHEN i.\"TYPE\" = 'T' THEN i.qty ELSE 0 END) -" +
//        "    SUM(CASE WHEN i.\"TYPE\" = 'W' THEN i.qty ELSE 0 END) AS totalstock " +
//        "FROM DVF.INVENTORY i JOIN DVF.ITEM itm  ON i.ITEM_ID =itm.ID " +
//        "GROUP BY i.ITEM_ID, itm.NAME;",nativeQuery = true)
//    List <InventoryDtoStock> findListStock();
//
//    @Query("SELECT new id.co.warehouse.application.dto.inventory.InventoryDtoStock( " +
//            "i.item.id, itm.name, " +
//            "SUM(CASE WHEN i.type = 'T' THEN i.qty ELSE 0 END) - " +
//            "SUM(CASE WHEN i.type = 'W' THEN i.qty ELSE 0 END)) " +
//            "FROM Inventory i JOIN i.item itm " +
//            "GROUP BY i.item.id, itm.name")
//    Page<InventoryDtoStock> findListStock(Pageable pageable);

    @Query(value = "SELECT i.ITEM_ID, itm.NAME, " +
            "SUM(CASE WHEN i.TYPE = 'T' THEN i.qty ELSE 0 END) - " +
            "SUM(CASE WHEN i.TYPE = 'W' THEN i.qty ELSE 0 END) AS total_stock " +
            "FROM DVF.INVENTORY i JOIN DVF.ITEM itm ON i.ITEM_ID = itm.ID " +
            "GROUP BY i.ITEM_ID, itm.NAME", nativeQuery = true)
    Page<Object[]> findListStockNative(Pageable pageable);

}
