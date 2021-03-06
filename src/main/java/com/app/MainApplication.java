package com.app;

import com.app.service.CategoryService;
import com.app.dto.CategoryDto;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private static final Logger logger
            = Logger.getLogger(MainApplication.class.getName());

    private final CategoryService categoryService;

    public MainApplication(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public ApplicationRunner init() {
        return args -> {

            categoryService.populateCategories();
            List<CategoryDto> categories = categoryService.fetchCategories();

            categories.forEach((e) -> logger.info(() -> "Category: " + e));
        };
    }
}
/*
 * DTOs via SqlResultSetMapping

Description: Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on SqlResultSetMapping and EntityManager.

Key points:
- use SqlResultSetMapping and EntityManager
- for using Spring Data Projections check this recipe
 * 
 */

