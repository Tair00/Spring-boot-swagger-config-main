package com.techinterface.swagger;

import com.techinterface.controller.CategoryController;
import com.techinterface.model.Category;
import com.techinterface.repository.CategoryRepository;
import lombok.Data;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SwaggerIntegrationApplicationTests {
@Autowired
	CategoryRepository categoryRepository;
@Autowired
private	JdbcTemplate jdbcTemplate;
@Autowired
private DataSource dataSource;
	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveCategoryTest() {
		Category category= Category.builder().id(1).name("c1").build();

		categoryRepository.save(category);
		assertThat(category.getId()).isGreaterThan(0);
	}

	@Test
	@Order(2)
	@Rollback(value = false)
	public void getCategoryTest() {
		Category category= categoryRepository.findById(1).get();


		assertThat(category.getId()).isEqualTo(1);
	}
	@Test
	@Order(3)
	@Rollback(value = false)
	public void listCategoryTest() {
		List<Category> categories = categoryRepository.findAll();


		assertThat(categories.size()).isGreaterThan(0);
	}

	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateCategoryTest(){
		Category category = categoryRepository.findById(1).get();
		category.setName("C1");
		Category categoryUpdate = categoryRepository.save(category);

		assertThat(categoryUpdate.getName());
	}

	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteCategoryTest(){
		Category category = categoryRepository.findById(1).get();
		categoryRepository.delete(category);
//		categoryRepository.deleteById(12);
		Category category1 = null;
		Optional<Category> optionalCategory = categoryRepository.findByName("C1");
		if(optionalCategory.isPresent()){
			category1 = optionalCategory.get();
		}
		assertThat(category1).isNull();

	}


}



