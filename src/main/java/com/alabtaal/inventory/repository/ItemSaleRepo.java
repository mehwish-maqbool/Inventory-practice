package com.alabtaal.inventory.repository;

import com.alabtaal.inventory.entity.ItemEntity;
import com.alabtaal.inventory.entity.ItemSaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemSaleRepo extends JpaRepository<ItemSaleEntity,Long> {
}
