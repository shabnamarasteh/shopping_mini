package com.learn.shopping.modules.product.controller;

import com.learn.shopping.modules.product.controller.validate.ProductValidate;
import com.learn.shopping.modules.product.model.component.ProductAddDTO;
import com.learn.shopping.modules.product.model.entity.Product;
import com.learn.shopping.modules.product.model.service.ProductService;
import com.learn.shopping.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;
    private ProductValidate productValidate;

    @Autowired
    public ProductController(ProductService productService, ProductValidate productValidate) {
        this.productService = productService;
        this.productValidate = productValidate;
    }

    @RequestMapping(value = "/product/save", method = RequestMethod.GET)
    public String saveProductForm(Model model){
        model.addAttribute("product", new Product());
        return "product/product";
    }

    @RequestMapping(value = "/product/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute ProductAddDTO productAddDTO){
        Product product = productAddDTO.convert2Object();
        ValidateObject validateObject = this.productValidate.addItem(product);
        if(validateObject.getResult().equals("error")){
//err
            return "redirect:/product";
        }
        try {
            this.productService.save(product);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/product";
    }


    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getAllProduct(Model model){
        List<Product> productList = this.productService.findAll();
        model.addAttribute("productList", productList);
        return "product/allProduct";
    }

    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(Model model, @PathVariable("id") String id){
        //validation
        Product product = this.productService.findById(Long.parseLong(id));
        try {
            this.productService.delete(product);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return "redirect:/product";
    }
}
