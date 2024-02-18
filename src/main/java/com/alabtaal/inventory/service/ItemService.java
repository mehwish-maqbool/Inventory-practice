package com.alabtaal.inventory.service;

import com.alabtaal.inventory.entity.ItemEntity;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Optional<ItemEntity> findByName(String name);

    ItemEntity getByName2(String name);

    List<ItemEntity> findAll();


    Boolean existsByName(String name);

    ItemEntity save(ItemEntity entity);

    void delete(Long id);
}
