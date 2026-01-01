package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Role;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UploadService uploadService;

    public ProductService(ProductRepository productRepository, UploadService uploadService) {
        this.productRepository = productRepository;
        this.uploadService = uploadService;
    }

    public String handleHello() {
        return "hello from Service";
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public Product handleSaveProduct(Product product) {
        Product huy = this.productRepository.save(product);
        return huy;
    }

    public void deleteAProduct(long id) {
        this.productRepository.deleteById(id);
    }
}
