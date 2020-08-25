package com.garwan.eshop.mapper;

import com.garwan.eshop.jpa.entity.AnimalCategoryEntity;
import com.garwan.eshop.model.AnimalCategory;
import java.util.List;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static java.util.Arrays.asList;

class AnimalCategoryMapperTest {
    @Test
    void shouldMapFromAnimalCategoryEntity() {
        AnimalCategoryEntity entity = animalCategoryEntity(1);

        AnimalCategory actualResult = AnimalCategoryMapper.fromAnimalCategoryEntity(entity);

        assertThat(actualResult).isEqualTo(AnimalCategory.builder().name(entity.getName()).build());
    }

    @Test
    void shouldMapFromAnimalCategoryEntities() {
        AnimalCategoryEntity entity1 = animalCategoryEntity(1);
        AnimalCategoryEntity entity2 = animalCategoryEntity(3);

        List<AnimalCategory> actualResult = AnimalCategoryMapper.fromAnimalCategoryEntities(asList(entity1, entity2));

        assertThat(actualResult).containsExactly(
            AnimalCategoryMapper.fromAnimalCategoryEntity(entity1),
            AnimalCategoryMapper.fromAnimalCategoryEntity(entity2)
        ).inOrder();
    }

    private static AnimalCategoryEntity animalCategoryEntity(long id) {
        return AnimalCategoryEntity.builder()
            .id(id)
            .name("name" + id)
            .build();
    }
}