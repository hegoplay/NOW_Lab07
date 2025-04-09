package iuh.se.fit.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iuh.se.fit.model.Product;
import iuh.se.fit.repo.ProductRepository;
import iuh.se.fit.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class ProductServiceReactiveImpl implements ProductService {

	@Autowired
	private ProductRepository repository;
	
	
	
	@Override
	public Mono<Product> getProduct(String id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	@Override
	public Mono<Product> updateProduct(Product product) {
		return repository.findById(product.getId()).flatMap(databaseProduct -> {
			if (databaseProduct == null) throw new RuntimeException("Product id: " + product.getId() + " not found");
			// Cập nhật các thuộc tính của product
			databaseProduct.setName(product.getName());
			databaseProduct.setPrice(product.getPrice());
			databaseProduct.setQuantity(product.getQuantity());
			
			return repository.save(product);
		})
		.switchIfEmpty(Mono.error(new RuntimeException("Product with id " + product.getId() + " not found")));
	}

	@Override
    public Flux<Product> getAllProducts() {
        return repository.findAll();
    }

	@Override
    public Mono<Product> createProduct(Product product) {
        // Có thể thêm validation nếu cần
		return repository.findById(product.getId()).flatMap(databaseProduct -> {
			if (databaseProduct != null) throw new RuntimeException("Product id: " + product.getId() + " found");
			// Cập nhật các thuộc tính của product
			
			return repository.save(product);
		})
		.switchIfEmpty(repository.save(product));
    }

	@Override
	public Mono<Void> deleteProduct(String id) {
		// TODO Auto-generated method stub
		return repository.deleteById(id);
	}

}
