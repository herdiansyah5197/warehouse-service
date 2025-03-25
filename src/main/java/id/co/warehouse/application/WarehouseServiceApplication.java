package id.co.warehouse.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EntityScan(basePackages = "id.co.warehouse.application.model")
@EnableJpaRepositories(basePackages = "id.co.warehouse.application.repository")
public class WarehouseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseServiceApplication.class, args);
	}

}
