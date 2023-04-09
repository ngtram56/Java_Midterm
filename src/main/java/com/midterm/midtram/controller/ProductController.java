package com.midterm.midtram.controller;

import com.midterm.midtram.model.Product;
import com.midterm.midtram.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @RequestMapping("/detail")
    public String detailProduct(Model model, @RequestParam String id) {
        Product p = productService.get(Integer.parseInt(id));
        model.addAttribute("detail_product", p);
        return "detail-product";
    }

    @RequestMapping("/shop")
    public String renderShop(Model model) {
        List<Product> lp = productService.getAll();
        if(lp != null) {
            model.addAttribute("products", lp);
        }
        return "shop";
    }

    @RequestMapping("/json-products")
    public @ResponseBody List<Product> getCarList() {
        return productService.getAll();
    }
}
