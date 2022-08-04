package com.example.toygraphqls.repository.jpa;

import com.example.toygraphqls.model.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CategoryJpaRepositoryTest {

    @Autowired
    CategoryJpaRepository categoryJpaRepository;

    @Test
    void findByCategoryCode() {
        Integer categoryCode = 860;
        Optional<Category> category = categoryJpaRepository.findByCategoryCode(categoryCode);

        assertThat(category).isNotEmpty();

        Category categoryInfo = category.get();
        System.out.println(categoryInfo.toString());
    }
}