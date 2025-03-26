package id.co.warehouse.application.repository;

import id.co.warehouse.application.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional findByOrderId(String orderId);

}
