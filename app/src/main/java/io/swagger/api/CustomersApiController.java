package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.dto.model.Cart;
import io.swagger.dto.model.Customer;
import io.swagger.dto.model.Item;
import io.swagger.dto.model.Order;
import io.swagger.dto.request.CustomerRequest;
import io.swagger.dto.request.ItemRequest;
import io.swagger.dto.response.CartResponse;
import io.swagger.dto.response.CustomerResponse;
import io.swagger.dto.response.OrderResponse;
import io.swagger.exception.NotFoundException;
import io.swagger.service.CartService;
import io.swagger.service.CustomerService;
import io.swagger.service.ItemService;
import io.swagger.service.OrderService;
import io.swagger.service.ProductService;
import org.hibernate.usertype.CompositeUserType;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
                            date = "2020-03-27T16:32:59.829Z[GMT]")
@Controller
public class CustomersApiController implements CustomersApi {

    private static final Logger log = LoggerFactory.getLogger(CustomersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @org.springframework.beans.factory.annotation.Autowired
    public CustomersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<CustomerResponse> addCustomer(
            @ApiParam(value = "", required = true) @Valid @RequestBody CustomerRequest body) {

        final Customer customer = modelMapper.map(body, Customer.class);
        final Customer savedCustomer = customerService.addCustomer(customer);
        final CustomerResponse customerResponse = modelMapper.map(savedCustomer, CustomerResponse.class);
        return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.CREATED);
    }

    public ResponseEntity<CartResponse> addItemToCart(
            @ApiParam(value = "", required = true) @Valid @RequestBody ItemRequest body,
            @ApiParam(value = "Customer identifier.", required = true) @PathVariable("customerid") String customerid) {

        final Item item = modelMapper.map(body, Item.class);

        item.setProduct(productService.getById(item.getProductId()));
        item.setCart(cartService.getCartByCustomerEmail(customerid));
        itemService.addItemToCart(item);
        final Cart updatedCart = cartService.getCartByCustomerEmail(customerid);
        final CartResponse cartResponse = modelMapper.map(updatedCart, CartResponse.class);
        return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
    }

    public ResponseEntity<Void> clearCart(
            @ApiParam(value = "Customer identifier.", required = true) @PathVariable("customerid") String customerid) {

        final List<Item> itemList = itemService.getItemEntitiesByCart_Customer_Email(customerid);
        itemService.deleteListOfItems(itemList);

        return new ResponseEntity<Void>(HttpStatus.RESET_CONTENT);
    }

    public ResponseEntity<Void> deleteCustomer(
            @ApiParam(value = "Customer identifier.", required = true) @PathVariable("customerid") String customerid) {

        customerService.deleteCustomer(customerid);

        return new ResponseEntity<Void>(HttpStatus.RESET_CONTENT);
    }

    public ResponseEntity<CartResponse> deleteItemFromCart(
            @ApiParam(value = "Customer identifier.", required = true) @PathVariable("customerid") String customerid,
            @ApiParam(value = "Product identifier.", required = true) @PathVariable("productid") UUID productid) {

        itemService.deleteItem(customerid, productid);

        final Cart cart = cartService.getCartByCustomerEmail(customerid);
        final CartResponse cartResponse = modelMapper.map(cart, CartResponse.class);

        return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
    }

    public ResponseEntity<CustomerResponse> findCustomerById(
            @ApiParam(value = "Customer identifier.", required = true) @PathVariable("customerid") String customerid) {

        final Customer returnedCustomer = customerService.findCustomerById(customerid);
        final CustomerResponse customerResponse = modelMapper.map(returnedCustomer, CustomerResponse.class);
        return new ResponseEntity<CustomerResponse>(customerResponse, HttpStatus.OK);
    }

    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {

        final List<Customer> customerList = customerService.getAllCustomers();
        final List<CustomerResponse> customerResponseList = customerList.stream()
                                                                        .map(customer -> modelMapper.map(customer,
                                                                                                         CustomerResponse.class))
                                                                        .collect(Collectors.toList());

        return new ResponseEntity<List<CustomerResponse>>(customerResponseList, HttpStatus.OK);
    }

    public ResponseEntity<CartResponse> getAllItemsInCart(
            @ApiParam(value = "Customer identifier.", required = true) @PathVariable("customerid") String customerid) {
        final Cart cart = cartService.getCartByCustomerEmail(customerid);
        final CartResponse cartResponse = modelMapper.map(cart, CartResponse.class);

        return new ResponseEntity<CartResponse>(cartResponse, HttpStatus.OK);
    }

    public ResponseEntity<List<OrderResponse>> getAllOrdersOfCustomer(
            @ApiParam(value = "Customer identifier.", required = true) @PathVariable("customerid") String customerid) {

        final List<Order> orderList = orderService.getOrdersByCustomer_Email(customerid);
        final List<OrderResponse> orderResponseList = orderList.stream()
                                                               .map(order -> modelMapper.map(order,
                                                                                             OrderResponse.class))
                                                               .collect(Collectors.toList());

        return new ResponseEntity<List<OrderResponse>>(orderResponseList, HttpStatus.OK);
    }

    public ResponseEntity<OrderResponse> getOrderOfCustomerById(
            @ApiParam(value = "Order identifier.", required = true) @PathVariable("orderid") UUID orderid) {

        return new ResponseEntity<OrderResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> importCustomersViaList(
            @ApiParam(value = "", required = true) @Valid @RequestBody List<CustomerRequest> body) {

        final List<Customer> customerList = body.stream()
                                                .map(customerRequest -> modelMapper.map(customerRequest,
                                                                                        Customer.class))
                                                .collect(Collectors.toList());

        customerService.saveAll(customerList);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    public ResponseEntity<OrderResponse> placeOrder(
            @ApiParam(value = "Customer identifier.", required = true) @PathVariable("customerid") String customerid) {


        return new ResponseEntity<OrderResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<CustomerResponse> updateCustomer(
            @ApiParam(value = "", required = true) @Valid @RequestBody CustomerRequest body) {
        try {
            final Customer customer = modelMapper.map(body, Customer.class);
            final CustomerResponse customerResponse =
                    modelMapper.map(customerService.updateCustomer(customer, body.getEmail()), CustomerResponse.class);

            return new ResponseEntity<CustomerResponse>(HttpStatus.OK);
        } catch (NotFoundException e)
    }

    public ResponseEntity<CartResponse> updateItemQuantity(
            @ApiParam(value = "Customer identifier.", required = true) @PathVariable("customerid") String customerid,
            @ApiParam(value = "Product identifier.", required = true) @PathVariable("productid") UUID productid,
            @NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "quantity", required = true)
                    Integer quantity) {

        return new ResponseEntity<CartResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
