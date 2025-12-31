package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.hoidanit.laptopshop.domain.User;

@Controller
public class ItemController {
    @GetMapping("/product/{id}")
    public String getProductPage(Model model, @PathVariable long id) {
        return "client/product/detail";
    }

    // @RequestMapping("/admin/user/{id}")
    // public String getUserDetailPage(Model model, @PathVariable long id) {
    // User user = this.userService.getUserById(id);
    // model.addAttribute("user", user);
    // model.addAttribute("id", id);
    // return "admin/user/detail";
    // }

}
