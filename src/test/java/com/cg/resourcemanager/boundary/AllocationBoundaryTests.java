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

import com.cg.resourcemanager.dto.AllocationDto;
import com.example.utils.MasterData;

@Order(3)
public class AllocationBoundaryTests {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfAllocationIdIsNotNull() throws Exception {
		AllocationDto allocationDto = MasterData.getAllocationDto();
		allocationDto.setId(null);
		Set<ConstraintViolation<AllocationDto>> violations = validator.validate(allocationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfAllocationIdGreaterThanZero() throws Exception {
		AllocationDto allocationDto = MasterData.getAllocationDto();
		allocationDto.setId(-1);
		Set<ConstraintViolation<AllocationDto>> violations = validator.validate(allocationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfPOAmtIsNotNull() throws Exception {
		AllocationDto allocationDto = MasterData.getAllocationDto();
		allocationDto.setPoAmount(null);
		Set<ConstraintViolation<AllocationDto>> violations = validator.validate(allocationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfPorjectIDNotNull() throws Exception {
		AllocationDto allocationDto = MasterData.getAllocationDto();
		allocationDto.setProjectId(null);
		Set<ConstraintViolation<AllocationDto>> violations = validator.validate(allocationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfItemIDNotNull() throws Exception {
		AllocationDto allocationDto = MasterData.getAllocationDto();
		allocationDto.setItemId(null);
		Set<ConstraintViolation<AllocationDto>> violations = validator.validate(allocationDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

}
