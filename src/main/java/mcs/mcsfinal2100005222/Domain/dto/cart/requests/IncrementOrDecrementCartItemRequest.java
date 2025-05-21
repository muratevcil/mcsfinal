package mcs.mcsfinal2100005222.Domain.dto.cart.requests;


import lombok.Data;

@Data
public class IncrementOrDecrementCartItemRequest {

    private String jwt;

    private String productUuid;

    private boolean incrementOrDecrement; //increment is true, decrement is false

}
