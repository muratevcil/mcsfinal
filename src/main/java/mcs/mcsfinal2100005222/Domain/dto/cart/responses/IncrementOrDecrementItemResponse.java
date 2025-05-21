package mcs.mcsfinal2100005222.Domain.dto.cart.responses;


import lombok.Data;

@Data
public class IncrementOrDecrementItemResponse {

    private String itemUuid;

    private double newQuantity;

}
