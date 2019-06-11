package net.samurai.katana.controller;

import lombok.AllArgsConstructor;
import net.samurai.katana.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * JayendraSingh
 */
@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductRepository productRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(p -> {
                    try {
                        return ResponseEntity.ok()
                                .eTag(p.getId() + "")
                                .location(new URI("/product/" + p.getId()))
                                .body(p);
                    } catch (URISyntaxException e) {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }
}
