package com.garwan.eshop.mapper;

import com.garwan.eshop.model.AnimalCategory;
import java.util.List;
import java.util.stream.Collectors;

public final class AnimalCategoryMapper {
    public static List<AnimalCategory> fromAnimalCategories(
        List<com.garwan.eshop.jpa.entity.AnimalCategory> animalCategories
    ) {
        return animalCategories.stream()
            .map(AnimalCategoryMapper::fromAnimalCategory)
            .collect(Collectors.toList());
    }

    private static AnimalCategory fromAnimalCategory(com.garwan.eshop.jpa.entity.AnimalCategory animalCategory) {
        return AnimalCategory.builder()
            .name(animalCategory.getName())
            .build();
    }
}
