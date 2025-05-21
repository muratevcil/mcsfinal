package mcs.mcsfinal2100005222.Domain.dto.product.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainPageProductResponse {

    private String productUuid;

    private String productName;

    private List<String> productMediaURLs;

    private Double productReviewScore;

    private String productSellerName;

    private String productSellerUuid;

    private Double productPrice;

}
