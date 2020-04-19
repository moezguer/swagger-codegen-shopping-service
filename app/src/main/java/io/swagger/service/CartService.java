package io.swagger.service;

import io.swagger.database.model.CartEntity;
import io.swagger.database.repository.CartRepository;
import io.swagger.dto.model.Cart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByCustomerEmail(String email) {

        final CartEntity returnedCartEntity = cartRepository.getCartEntityByCustomer_Email(email);
        final Cart returnedCart = modelMapper.map(returnedCartEntity, Cart.class);
        return returnedCart;

    }


}
