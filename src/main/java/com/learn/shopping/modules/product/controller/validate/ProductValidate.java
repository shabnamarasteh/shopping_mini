package com.learn.shopping.modules.product.controller.validate;

import com.learn.shopping.modules.product.model.entity.Product;
import com.learn.shopping.modules.product.model.service.ProductService;
import com.learn.shopping.utils.transientObject.ValidateObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class ProductValidate {
    private ProductService productService;

    @Autowired
    public ProductValidate(ProductService productService) {
        this.productService = productService;
    }

    public ValidateObject addItem(Product product){
        ValidateObject validateObject = new ValidateObject();
        List<String> errorList = new ArrayList<>();
        List<String> types = Arrays.asList("png", "jpg", "jpeg");
        if(product == null){
            errorList.add("product data in empty");
        }else{
            if(product.getImage() != null){
                String type = Objects.requireNonNull(product.getImage().getContentType()).split("/")[1];
                System.out.println("-----type-----------"+type);
                if(!types.contains(type)){
                    errorList.add("type not correct");
                }
            }
        }

        if(errorList.size() > 0){
            validateObject.setResult("error");
            validateObject.setCount(errorList.size());
            validateObject.setMessages(errorList);
        }else {
            validateObject.setResult("success");
        }
        return validateObject;
    }
}
