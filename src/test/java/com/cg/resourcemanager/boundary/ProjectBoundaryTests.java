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

import com.cg.resourcemanager.dto.ProjectDto;
import com.example.utils.MasterData;

@Order(3)
public class ProjectBoundaryTests {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfProjectIdIsNotNull() throws Exception {
		ProjectDto projectDto = MasterData.getProjectDto();
		projectDto.setId(null);
		Set<ConstraintViolation<ProjectDto>> violations = validator.validate(projectDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfProjectIdGreaterThanZero() throws Exception {
		ProjectDto projectDto = MasterData.getProjectDto();
		projectDto.setId(-1);
		Set<ConstraintViolation<ProjectDto>> violations = validator.validate(projectDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfNameIsNotNull() throws Exception {
		ProjectDto projectDto = MasterData.getProjectDto();
		projectDto.setName(null);
		Set<ConstraintViolation<ProjectDto>> violations = validator.validate(projectDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);

	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfNameIsNotMoreThanTwentyChars() throws Exception {
		ProjectDto projectDto = MasterData.getProjectDto();
		projectDto.setName("Test Name is not more than twenty character");
		Set<ConstraintViolation<ProjectDto>> violations = validator.validate(projectDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);

	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfDescriptionIsNotNull() throws Exception {
		ProjectDto projectDto = MasterData.getProjectDto();
		projectDto.setDescription(null);
		Set<ConstraintViolation<ProjectDto>> violations = validator.validate(projectDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);

	}

	@Test
	public void testHibernateValidationIsAddedToCheckIfDescriptionIsNotLessThanFiveChars() throws Exception {
		ProjectDto projectDto = MasterData.getProjectDto();
		projectDto.setDescription("Test Description is not more than twenty character");
		Set<ConstraintViolation<ProjectDto>> violations = validator.validate(projectDto);
		yakshaAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

}
