package mcs.mcsfinal2100005222.Domain.entities.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryEntity {

    private int categoryId;

    private String categoryName;

    private String categoryDescription;

}
