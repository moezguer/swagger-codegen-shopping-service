package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.dto.model.Product;
import io.swagger.dto.request.ProductRequest;
import io.swagger.dto.response.ProductResponse;
import io.swagger.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
                            date = "2020-03-27T16:32:59.829Z[GMT]")
@Slf4j
@Controller
public class ProductsApiController implements ProductsApi {

    private final ObjectMapper objectMapper;
    private final HttpServletRequest request;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductService productService;

    @org.springframework.beans.factory.annotation.Autowired
    public ProductsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<ProductResponse> addProduct(@ApiParam(value = "",
                                                                required = true) @Valid @RequestBody ProductRequest body) {

        final Product product = modelMapper.map(body, Product.class);

        final Product savedProduct = productService.addProduct(product);

        final ProductResponse productResponse = modelMapper.map(savedProduct, ProductResponse.class);

        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);

    }

    public ResponseEntity<Void> deleteProduct(@ApiParam(value = "Product identifier.",
                                                        required = true) @PathVariable("productid") UUID productid) {

        productService.deleteById(productid);
        return new ResponseEntity<>(HttpStatus.RESET_CONTENT);

    }

    public ResponseEntity<ProductResponse> getProductById(@ApiParam(value = "Product identifier.",
                                                                    required = true) @PathVariable("productid") UUID productid) {

        final Product product = modelMapper.map(productService.findById(productid), Product.class);
        final ProductResponse productResponse = modelMapper.map(product, ProductResponse.class);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);

    }

    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        final List<ProductResponse> productResponsesList = productService.findAll()
                                                                         .stream()
                                                                         .map(products -> modelMapper.map(products,
                                                                                 ProductResponse.class))
                                                                         .collect(Collectors.toList());

        return new ResponseEntity<List<ProductResponse>>(productResponsesList, HttpStatus.OK);

    }

    public ResponseEntity<Void> importProductsViaFile(@ApiParam(value = "") @Valid @RequestBody Object body) {
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> importProductsViaList(@ApiParam(value = "",
                                                                required = true) @Valid @RequestBody List<ProductRequest> body) {

        final List<Product> products = body.stream()
                                           .map(productRequest -> modelMapper.map(productRequest, Product.class))
                                           .collect(Collectors.toList());
        productService.saveAll(products);

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<ProductResponse> updateProducts(@ApiParam(value = "",
                                                                    required = true) @Valid @RequestBody ProductRequest body,
                                                          @ApiParam(value = "Product identifier.",
                                                                    required = true) @PathVariable("productid") UUID productid) {
        final Product product = modelMapper.map(body, Product.class);
        final ProductResponse productResponse =
                modelMapper.map(productService.updateProduct(product, productid), ProductResponse.class);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.NO_CONTENT);

    }
}

