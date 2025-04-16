package iuh.se.fit.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import iuh.se.fit.model.Product;
import iuh.se.fit.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private final ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Product> getProduct(@PathVariable Integer id) {
        return productService.getProduct(id)
            .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }
    
    @GetMapping("/")
    public Flux<Product> getAllProducts() {
        return productService.getAllProducts();
    }
   
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Product> createProduct(@RequestBody Product product) {
	    return productService.createProduct(product);
	}
    
    @PutMapping
    public Mono<ResponseEntity<Product>> updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product)
            .map(ResponseEntity::ok)
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }
}