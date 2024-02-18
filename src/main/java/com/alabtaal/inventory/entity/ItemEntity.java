package com.alabtaal.inventory.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(schema = "inventory" , name = "items" , uniqueConstraints = {
        @UniqueConstraint(name = "Items_uk1" , columnNames = "item_name")
})
public class ItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "item_id")
    private long id;

    @Column(name = "item_name")
    @NotBlank(message = "item name must be enterd")
    private String name;

    @Column(name = "item_brand")
    private String brand;

    @Column(name = "item_type")
    private String itemType;

    @Column(name = "item_price")
    @NotNull(message = "Item price must be enterd")
    private Double price;

    @Column(name = "item_expiry_date")
    private Date expiryDate;

    @OneToMany(mappedBy = "item")
    private List<ItemSaleEntity> itemSales;

    @OneToMany(mappedBy = "item")
    private List<ItemPurchaseEntity> itemPurchases;

    //Faheem
}
