package mcs.mcsfinal2100005222.Infrastructure.mysql.adapters;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import mcs.mcsfinal2100005222.Domain.dto.cart.requests.*;
import mcs.mcsfinal2100005222.Domain.dto.cart.responses.IncrementOrDecrementItemResponse;
import mcs.mcsfinal2100005222.Domain.entities.GenericResponse;
import mcs.mcsfinal2100005222.Domain.entities.cart.CartItemEntity;
import mcs.mcsfinal2100005222.Domain.ports.CartServicePort;
import mcs.mcsfinal2100005222.Domain.security.concretes.JwtManager;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart.Cart;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart.CartItem;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.cart.CartProductDiscount;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product.Product;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.User;
import mcs.mcsfinal2100005222.Infrastructure.mysql.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceAdapter implements CartServicePort {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartCategoryDiscountRepository cartCategoryDiscountRepository;
    @Autowired
    private CartProductDiscountRepository cartProductDiscountRepository;
    @Autowired
    private JwtManager jwtManager;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public GenericResponse<Cart> addItemToCart(String jwt, AddItemToCartRequest addItemToCartRequest) {

        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(jwt));
        user.orElseThrow(RuntimeException::new);
        Cart cartOfTheUser = user.get().getCart();
        System.out.println(addItemToCartRequest.getProductQuantity());
        List<CartItem> cartItems = cartOfTheUser.getCartItems();
        Product productWillBeAddedToCart = productRepository.getProductByProductUuid(addItemToCartRequest.getProductUuid());
        if(productWillBeAddedToCart == null){
            throw new EntityNotFoundException("A product with this puuid is not found.");
        }
        CartItem cartItem = new CartItem(cartOfTheUser,
                productWillBeAddedToCart,
                addItemToCartRequest.getProductQuantity()
        );
        for(CartItem eachCartItem : cartItems){ // Update the quantity if a cart item already exists.
            if(eachCartItem.getCartProduct().getProductUuid().equals(addItemToCartRequest.getProductUuid())){
                eachCartItem.setQuantity(eachCartItem.getQuantity()+addItemToCartRequest.getProductQuantity());
                cartOfTheUser.setCartItems(cartItems);
                cartItemRepository.save(eachCartItem);
                cartRepository.save(cartOfTheUser);
                System.out.println(eachCartItem.getCartItemId());
                return null;
            }
        }
        cartItems.add(cartItem);
        cartOfTheUser.setCartItems(cartItems);  // Add the new cartitems if it doesn't exists in the cart already.
        cartItemRepository.save(cartItem);
        cartRepository.save(cartOfTheUser);


        return null;
    }

    public GenericResponse<List<CartItemEntity>> getCartContent(String jwt){
        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(jwt));
        user.orElseThrow(EntityNotFoundException::new);
        List<CartItem> allCartItemsForUse = cartItemRepository.findAllByCart_CartUuid(user.get().getCart().getCartUuid());
        List<CartItemEntity> cartItemEntities = new ArrayList<>();
        for(CartItem cartItem:allCartItemsForUse){

            CartItemEntity entityToAdd = new CartItemEntity();
            entityToAdd.setCartItemId(cartItem.getCartItemId());
            entityToAdd.setProductName(cartItem.getCartProduct().getProductName());
            entityToAdd.setProductDescription(cartItem.getCartProduct().getProductDescription());
            entityToAdd.setProductQuantity(cartItem.getQuantity());
            entityToAdd.setProductImgUrl(!cartItem.getCartProduct().getProductMediaURLs().isEmpty() ?cartItem.getCartProduct().getProductMediaURLs().get(0):null);
            entityToAdd.setProductUuid(cartItem.getCartProduct().getProductUuid());
            entityToAdd.setProductPrice(cartItem.getCartProduct().getProductPrice());
            cartItemEntities.add(entityToAdd);
        }


        return new GenericResponse<List<CartItemEntity>>(200,cartItemEntities);
    }

    public GenericResponse<IncrementOrDecrementItemResponse> incrementOrDecrementItemCount(IncrementOrDecrementCartItemRequest incrementOrDecrementCartItemRequest){
        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(incrementOrDecrementCartItemRequest.getJwt()));
        user.orElseThrow(EntityNotFoundException::new);
        List<CartItem> allCartItemsForUse = cartItemRepository.findAllByCart_CartUuid(user.get().getCart().getCartUuid());
        CartItemEntity effectedCartItemEntity = new CartItemEntity();
        if(incrementOrDecrementCartItemRequest.isIncrementOrDecrement()){ // if isIncrementOrDecrement true = increment it
            for(CartItem cartItem : allCartItemsForUse){
                if(cartItem.getCartProduct().getProductUuid().equals(incrementOrDecrementCartItemRequest.getProductUuid())){
                    cartItem.setQuantity(cartItem.getQuantity()+1);
                    effectedCartItemEntity.setProductQuantity(cartItem.getQuantity());
                    effectedCartItemEntity.setProductUuid(cartItem.getCartProduct().getProductUuid());
                    cartItemRepository.save(cartItem);
                }
            }
        }
        else{ // if isIncrementOrDecrement false = decrement it
            for(CartItem cartItem : allCartItemsForUse){
                if(cartItem.getCartProduct().getProductUuid().equals(incrementOrDecrementCartItemRequest.getProductUuid())){
                    cartItem.setQuantity(cartItem.getQuantity()-1);
                    effectedCartItemEntity.setProductQuantity(cartItem.getQuantity());
                    effectedCartItemEntity.setProductUuid(cartItem.getCartProduct().getProductUuid());
                    cartItemRepository.save(cartItem);
                }
            }
        }
        IncrementOrDecrementItemResponse returningObject = new IncrementOrDecrementItemResponse();
        returningObject.setItemUuid(effectedCartItemEntity.getProductUuid());
        returningObject.setNewQuantity(effectedCartItemEntity.getProductQuantity());
        return new GenericResponse<IncrementOrDecrementItemResponse>(returningObject);
    }

    @Override
    public GenericResponse<Integer> getCartItemCount(String jwt) {

        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(jwt));
        user.orElseThrow(EntityNotFoundException::new);
        List<CartItem> allCartItemsForUse = cartItemRepository.findAllByCart_CartUuid(user.get().getCart().getCartUuid());
        return new GenericResponse<Integer>(200,allCartItemsForUse.size());

    }

    @Override
    public GenericResponse<Double> getCartTotalprice(String jwt) {
        double totalPrice =0;
        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(jwt));
        user.orElseThrow(EntityNotFoundException::new);
        List<CartItem> allCartItemsForUse = cartItemRepository.findAllByCart_CartUuid(user.get().getCart().getCartUuid());
        for(CartItem eachItem : allCartItemsForUse){
            totalPrice += eachItem.getQuantity() * eachItem.getUnitPrice();
        }
        return new GenericResponse<Double>(200,totalPrice);
    }

    @Override
    @Transactional
    public GenericResponse<Object> removeCartItem(String jwt,String productUuid) {
        Optional<User> user = userRepository.findByUsername(jwtManager.extractUser(jwt));
        user.orElseThrow(EntityNotFoundException::new);
        Cart userCart = user.get().getCart();
        CartItem cartItem = cartItemRepository.findByCartProduct_ProductUuid(productUuid);
        userCart.getCartItems().remove(cartItem);
        cartRepository.save(userCart);
        cartItemRepository.removeByCartProduct_ProductUuid(productUuid);
        return new GenericResponse<Object>(200,"success");
    }


    public List<CartProductDiscount> extractDiscountsFromUuidList(List<String> discountUuidList){

        return null;
    }

}
