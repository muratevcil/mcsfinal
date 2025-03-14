package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.product;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name="product_view")
public class ProductViews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="product_uuid")
    private Product product;

    @Column(name="user_id")
    private int user_id;

    @CreatedDate
    private Date viewDate;

}
