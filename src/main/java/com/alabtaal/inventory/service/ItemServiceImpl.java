package com.alabtaal.inventory.service;

import com.alabtaal.inventory.entity.ItemEntity;
import com.alabtaal.inventory.repository.ItemRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepo itemRepo;


    @Override
    public Optional<ItemEntity> findByName(String name) {
        return itemRepo.findByNameIgnoreCase(name);
    }

    @Override
    public ItemEntity getByName2(String name) {
        return itemRepo.getByNameIgnoreCase(name);
    }

    @Override
    public List<ItemEntity> findAll() {
        return itemRepo.findAll();
    }

    @Override
    public Boolean existsByName(String name) {
        return itemRepo.existsByNameIgnoreCase(name);
    }

    @Override
    public ItemEntity save(ItemEntity entity) {
        return itemRepo.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id) {
    itemRepo.deleteById(id);
    }
}
