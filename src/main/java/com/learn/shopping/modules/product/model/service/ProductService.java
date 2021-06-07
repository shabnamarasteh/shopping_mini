package com.learn.shopping.modules.product.model.service;

import com.learn.shopping.modules.product.model.entity.Product;
import com.learn.shopping.modules.product.model.entity.ProductGallery;
import com.learn.shopping.modules.product.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductGalleryService productGalleryService;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductGalleryService productGalleryService) {
        this.productRepository = productRepository;
        this.productGalleryService = productGalleryService;
    }

    @Transactional
    public Product save(Product product) throws IOException {
        if(product.getImage() != null && !product.getImage().isEmpty()){
            File theDir = new File("static/img/products");
            System.out.println("--------38---------"+theDir.getAbsolutePath());
            if (!theDir.exists()){
                System.out.println("--------40---------"+theDir.getAbsolutePath());
                theDir.mkdirs();
            }
            String path = ResourceUtils.getFile("static/img/products").getAbsolutePath();
            byte[] bytes = product.getImage().getBytes();
            String name = UUID.randomUUID()+ "." + Objects.requireNonNull(product.getImage().getContentType()).split("/")[1];
            Files.write(Paths.get(path+ File.separator +name) , bytes);
            product.setImgSrc(name);
        }
        return this.productRepository.save(product);
    }

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Transactional
    public void delete(Product product) throws FileNotFoundException {
        List<ProductGallery> productGalleryList = this.productGalleryService.findAllByProduct(product);
        File theDir = new File("static/img/products");
        String path = ResourceUtils.getFile("static/img/products").getAbsolutePath();
        if(productGalleryList.size() > 0){
            if(theDir.exists()){
                productGalleryList.forEach(productGallery -> {
                    try {
                        Files.delete(Paths.get(path+ File.separator + productGallery.getImageSrc()));
                    }  catch (NoSuchFileException x) {
                        System.err.format("%s: no such" + " file or directory%n", path);
                    } catch (DirectoryNotEmptyException x) {
                        System.err.format("%s not empty%n", path);
                    } catch (IOException x) {
                        // File permission problems are caught here.
                        System.err.println(x.getMessage());
                    }
                });
            }
            this.productGalleryService.deleteAll(productGalleryList);
        }
        try {
            Files.delete(Paths.get(path+ File.separator + product.getImgSrc()));
        }  catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x.getMessage());
        }
        this.productRepository.delete(product);
    }

    public Product findById(long id) {
        return this.productRepository.getById(id);
    }
}
