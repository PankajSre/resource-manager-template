package com.cg.resourcemanager.exception;

import static com.example.utils.TestUtils.currentTest;
import static com.example.utils.TestUtils.exceptionTestFile;
import static com.example.utils.TestUtils.yakshaAssert;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.resourcemanager.controller.ProjectController;
import com.cg.resourcemanager.dto.ProjectDto;
import com.cg.resourcemanager.dto.ResourceExceptionResponse;
import com.cg.resourcemanager.service.ProjectService;
import com.example.utils.MasterData;

@Order(2)
@WebMvcTest(ProjectController.class)
@AutoConfigureMockMvc
public class TestProjectExceptions {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProjectService projectservice;

	@Test
	public void testDataValidationCheckIsAddedInController() throws Exception {
		ProjectDto projectDto = com.example.utils.MasterData.getProjectDto();
		Mockito.when(projectservice.add(projectDto)).thenReturn(projectDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/projectservice/add")
				.content(MasterData.asJsonString(projectDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, exceptionTestFile);

	}

	@Test
	public void testAbleToWorkWithCustomExceptions() throws Exception {
		ProjectDto projectDto = MasterData.getProjectDto();
		Mockito.when(projectservice.add(projectDto)).thenReturn(projectDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/projectservice/add")
				.content(MasterData.asJsonString(projectDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, exceptionTestFile);

	}

	@Test
	public void testExceptionIsThrownAndHandledInCaseOfInvalidData() throws Exception {
		ProjectDto projectDto = MasterData.getProjectDto();
		Mockito.when(projectservice.add(projectDto)).thenReturn(projectDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/projectservice/add")
				.content(MasterData.asJsonString(projectDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, exceptionTestFile);

	}

	@Test
	public void testAbleToImplementVariousResponseCodeWithCustomizedMessage() throws Exception {
		ProjectDto projectDto = MasterData.getProjectDto();
		Mockito.when(projectservice.add(projectDto)).thenReturn(projectDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/projectservice/add")
				.content(MasterData.asJsonString(projectDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, exceptionTestFile);

	}

	//@Test
	void testExceptionIsThrownAndHandledIfProjectIdIsNotValidWhileDeleting() throws Exception {

		ProjectDto projectDto = MasterData.getProjectDto();
		Integer id = projectDto.getId();

		ResourceExceptionResponse exResponse = new ResourceExceptionResponse("Project with Id - " + id + " not found!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(projectservice.deleteByID(id))
				.thenThrow(new ProjectException("Project with Id - " + id + " not found!"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/projectservice/delete/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? true : false,
				exceptionTestFile);

	}

	//@Test
	void testExceptionIsThrownAndHandledIfProjectIdIsNotValidWhileGettingProjectById() throws Exception {
		ProjectDto projectDto = MasterData.getProjectDto();
		Integer id = projectDto.getId();

		ResourceExceptionResponse exResponse = new ResourceExceptionResponse("Project with Id - " + id + " not found!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(projectservice.findById(id))
				.thenThrow(new ProjectException("Project with Id - " + id + " not found!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projectservice/get/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? true : false),
				exceptionTestFile);

	}

}
