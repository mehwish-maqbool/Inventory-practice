package com.alabtaal.inventory.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(schema = "inventory" , name = "item_sale")
public class ItemSaleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "item_id")
    private long id;

    @Column(name = "quantity")
    @NotNull(message = "Quantity must be enterd")
    private Long quantity;

    @Column(name = "sale_date")
    private Date saleDate = new Date();

   @Column(name = "price")
    @NotNull(message = "Price must be enterd")
    private Double price;

    @Column(name = "discount")
    private Long discount;

    @ManyToOne
    private ItemEntity item;
}
//mehwish
