package io.swagger.service;

import io.swagger.database.model.ProductEntity;
import io.swagger.database.repository.ProductRepository;
import io.swagger.dto.model.Product;
import io.swagger.exception.NotFoundException;
import io.swagger.util.CSVtoBean;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product) {

        final ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);
        final ProductEntity savedProductEntity = productRepository.save(productEntity);

        return modelMapper.map(savedProductEntity, Product.class);
    }


    public void deleteById(UUID productId) {

        if (findById(productId) != null) {
            productRepository.deleteById(productId);
        } else {
            throw new NotFoundException(productId.toString());
        }

    }


    public Product findById(UUID productId) {
        final Product product = modelMapper.map(productRepository.findById(productId)
                                                                 .orElseThrow(() -> new NotFoundException(
                                                                         productId.toString())), Product.class);
        return product;
    }

    public List<Product> findAll() {

        final List<Product> productList = productRepository.findAll()
                                                           .stream()
                                                           .map(productEntity -> modelMapper.map(productEntity,
                                                                   Product.class))
                                                           .collect(Collectors.toList());

        return productList;

    }

    public void saveAll(List<Product> productList) {

        final List<ProductEntity> productEntities = productList.stream()
                                                               .map(product -> modelMapper.map(product,
                                                                       ProductEntity.class))
                                                               .collect(Collectors.toList());

        productRepository.saveAll(productEntities);

    }

    public Product updateProduct(Product product, UUID productId) {

        final ProductEntity productEntity = modelMapper.map(product, ProductEntity.class);

        productRepository.findById(productId)
                         .map(x -> {
                             x.setTitle(productEntity.getTitle());
                             x.setPrice(productEntity.getPrice());
                             x.setCurrency(productEntity.getCurrency());
                             return productRepository.save(x);
                         })
                         .orElseThrow(() -> new NotFoundException(productId.toString()));

        final Product outputProduct = modelMapper.map(productRepository.findById(productId), Product.class);

        return outputProduct;
    }

    public void saveAllViaCSV() throws IOException {
        List<Product> productList = new CSVtoBean().setCsvToBean()
                                                   .stream()
                                                   .map(row -> modelMapper.map(row, Product.class))
                                                   .collect(Collectors.toList());
        List<ProductEntity> productEntityList = productList.stream()
                                                           .map(product -> modelMapper.map(product,
                                                                   ProductEntity.class))
                                                           .collect(Collectors.toList());
        productRepository.saveAll(productEntityList);

    }
}
