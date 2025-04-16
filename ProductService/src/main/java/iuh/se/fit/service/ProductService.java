package iuh.se.fit.service;

import iuh.se.fit.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductService {
	Mono<Product> getProduct(Integer id);
	
	Flux<Product> getAllProducts();
    
    // Create
    Mono<Product> createProduct(Product product);
    
    // Update
    Mono<Product> updateProduct(Product product);
    
    // Delete
    Mono<Void> deleteProduct(Integer id);
	
	
}
