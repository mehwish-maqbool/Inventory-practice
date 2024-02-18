package com.alabtaal.inventory.repository;

import com.alabtaal.inventory.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepo extends JpaRepository<ItemEntity,Long> {
    Optional<ItemEntity> findByNameIgnoreCase(String name);

    Boolean existsByNameIgnoreCase(String name);

    ItemEntity getByNameIgnoreCase(String name);

    List<ItemEntity> findAllByBrandIgnoreCaseAndPriceLessThanEqual(String brand , Double price);

    List<ItemEntity> findAllByNameIgnoreCaseOrBrandIgnoreCase(String name , String brand);
}
