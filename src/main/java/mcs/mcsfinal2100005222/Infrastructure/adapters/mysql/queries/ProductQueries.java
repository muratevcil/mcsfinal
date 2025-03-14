package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.queries;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


public class ProductQueries {

    public static final String getProductByIdQuery = """
            SELECT * FROM product p WHERE p.product_uuid = :uuid
            """;

    public static final String getAllProducts = """
            SELECT p.product_uuid AS productUuid,
             p.product_category_id AS productCategoryId,
             p.product_description AS productDescription,
             p.product_name AS productName,
             p.product_seller_user_uuid AS productSellerUserUuid,
             p.product_stock_quantity AS productStockQuantity,
             c.category_id AS categoryId,
             c.category_description AS categoryDescription,
             c.category_name AS categoryName
             FROM product p LEFT JOIN product_category c ON p.product_category_id = c.category_id
            
            """;

    public static final String getAllProductsForCategory = """
            SELECT * FROM product p
            WHERE p.category_id = :category_id
            """;

    public static final String getProductCategories = """
            SELECT * FROM product_category
            """;

}
