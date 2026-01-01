package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public ProductController(ProductService productService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/product")
    public String getPoduct(Model model) {
        List<Product> pro = this.productService.getAllProducts();
        model.addAttribute("products", pro);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create") // GET
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping(value = "/admin/product/create")
    public String createUserPage(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductbindingResult, @RequestParam("productFile") MultipartFile file) {

        // validate
        if (newProductbindingResult.hasErrors()) {
            return "/admin/product/create";
        }
        //
        String image = this.uploadService.handleSaveUploadFile(file, "product");

        product.setImage(image);

        this.productService.handleSaveProduct(product);

        return "redirect:/admin/product";
    }
}
