package mcs.mcsfinal2100005222.Infrastructure.mysql.entities.product;

import jakarta.persistence.*;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.User;
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

    @ManyToOne
    @JoinColumn(name="user_uuid")
    private User user;

    @CreatedDate
    private Date viewDate;

}
