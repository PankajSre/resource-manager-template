package com.cg.resourcemanager.boundary;

import static com.example.utils.TestUtils.boundaryTestFile;
import static com.example.utils.TestUtils.currentTest;
import static com.example.utils.TestUtils.yakshaAssert;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.core.annotation.Order;

import com.cg.resourcemanager.dto.ItemDto;
import com.example.utils.MasterData;

@Order(3)
public class ItemBoundaryTests {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfItemIdIsNotNull() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		itemDto.setId(null);
		Set<ConstraintViolation<ItemDto>> violations = validator.validate(itemDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfNameIsNotNull() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		itemDto.setName(null);
		Set<ConstraintViolation<ItemDto>> violations = validator.validate(itemDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);

	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfNameIsNotLessThanFourChars() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		itemDto.setName("Tes");
		Set<ConstraintViolation<ItemDto>> violations = validator.validate(itemDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);

	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfTitleIsNotMoreThanTwentyChars() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		itemDto.setName("Business Service Group of Department");
		Set<ConstraintViolation<ItemDto>> violations = validator.validate(itemDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);

	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfCostIsNotNull() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		itemDto.setCost(null);
		Set<ConstraintViolation<ItemDto>> violations = validator.validate(itemDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfTypeIsNotMoreThanTwentyChar() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		itemDto.setType("Test Item Type of More Than Twenty Character");
		Set<ConstraintViolation<ItemDto>> violations = validator.validate(itemDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

}
