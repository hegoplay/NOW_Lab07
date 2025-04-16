package iuh.se.fit.repo;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import iuh.se.fit.model.Product;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Integer> {
	
}
