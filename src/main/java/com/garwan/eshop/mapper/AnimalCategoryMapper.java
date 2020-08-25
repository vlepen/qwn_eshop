package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.AnimalCategoryEntity;
import com.garwan.eshop.model.AnimalCategory;
import java.util.List;
import java.util.stream.Collectors;

public final class AnimalCategoryMapper {
    public static List<AnimalCategory> fromAnimalCategoryEntities(List<AnimalCategoryEntity> animalCategories) {
        return animalCategories.stream()
            .map(AnimalCategoryMapper::fromAnimalCategoryEntity)
            .collect(Collectors.toList());
    }

    static AnimalCategory fromAnimalCategoryEntity(AnimalCategoryEntity animalCategory) {
        return AnimalCategory.builder().name(animalCategory.getName()).build();
    }
}
