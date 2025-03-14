package mcs.mcsfinal2100005222.Infrastructure.adapters.mysql.entities.transactions;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name="tax_types")
public class TaxTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String taxTypeName;
    @Column(name="associated_category_id")
    private int categoryId;
    @Column(name="description",columnDefinition = "TEXT")
    private String description;
    @Column(name="tax_percentage_for_overall_price")
    private double taxPercentageForOverAllPrice;
    @Column(name="tax_percentage_for_unit_price")
    @Nullable
    private double taxPercentageForUnitPrice;

}
