package com.cg.resourcemanager.functional;

import static com.example.utils.TestUtils.asJsonString;
import static com.example.utils.TestUtils.businessTestFile;
import static com.example.utils.TestUtils.currentTest;
import static com.example.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cg.resourcemanager.controller.ProjectController;
import com.cg.resourcemanager.dto.ProjectDto;
import com.cg.resourcemanager.service.ProjectService;
import com.example.utils.MasterData;

@Order(1)
@WebMvcTest(ProjectController.class)
@AutoConfigureMockMvc
class ProjectControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProjectService projectService;

	@Test
	void testRestEndpointForFindAllProjectsIsExposedAndWorking() throws Exception {
		List<ProjectDto> list = new ArrayList<ProjectDto>();
		list.add(MasterData.getProjectDto());
		Mockito.when(projectService.findAll()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projectservice/all")
				.content(MasterData.asJsonString(MasterData.getProjectDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list)) ? "true" : "false"),
				businessTestFile);

	}

	@Test
	void testAbleToDefineAppropriateClassesAndObjectsForAGivenScenario() throws Exception {
		List<ProjectDto> list = new ArrayList<ProjectDto>();
		list.add(MasterData.getProjectDto());
		Mockito.when(projectService.findAll()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projectservice/all")
				.content(MasterData.asJsonString(MasterData.getProjectDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list)) ? "true" : "false"),
				businessTestFile);

	}

	@Test
	void testAbleToConfigureAndConnectToDatabase() throws Exception {
		List<ProjectDto> list = new ArrayList<ProjectDto>();
		list.add(MasterData.getProjectDto());
		Mockito.when(projectService.findAll()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projectservice/all")
				.content(MasterData.asJsonString(MasterData.getProjectDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list)) ? "true" : "false"),
				businessTestFile);

	}

	@Test
	void testRestEndpointForFindAllProjectsIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];
		List<ProjectDto> list = new ArrayList<ProjectDto>();
		list.add(MasterData.getProjectDto());

		Mockito.when(projectService.findAll()).then(new Answer<List<ProjectDto>>() {

			@Override
			public List<ProjectDto> answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return list;
			}
		});

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projectservice/all")
				.content(MasterData.asJsonString(MasterData.getProjectDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	/** add project ** */

	@Test
	void testRestEndpointForAddProjectIsExposedAndWorking() throws Exception {
		ProjectDto projectDto = com.example.utils.MasterData.getProjectDto();
		Mockito.when(projectService.add(any(ProjectDto.class))).thenReturn(projectDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/projectservice/add")
				.content(com.example.utils.MasterData.asJsonString(projectDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(asJsonString(projectDto)) ? true : false,
				businessTestFile);

	}

	@Test
	void testRestEndpointForAddNewProjectIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];

		ProjectDto projectDto = com.example.utils.MasterData.getProjectDto();
		Mockito.when(projectService.add(any(ProjectDto.class))).then(new Answer<ProjectDto>() {

			@Override
			public ProjectDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return projectDto;
			}
		});

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/projectservice/add")
				.content(com.example.utils.MasterData.asJsonString(projectDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	/*** delete ****/

	@Test
	void testRestEndpointForDeletingProjectIsExposedAndWorking() throws Exception {
		ProjectDto projectDto = com.example.utils.MasterData.getProjectDto();
		Integer id = projectDto.getId();
		Mockito.when(projectService.deleteByID(id)).thenReturn(projectDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/projectservice/delete/{id}", id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(asJsonString(projectDto)) ? true : false,
				businessTestFile);

	}

	@Test
	void testRestEndpointForDeletingProjectIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];
		ProjectDto projectDto = com.example.utils.MasterData.getProjectDto();
		Integer id = projectDto.getId();
		Mockito.when(projectService.deleteByID(id)).then(new Answer<ProjectDto>() {

			@Override
			public ProjectDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return MasterData.getProjectDto();
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/projectservice/delete/{id}", id)
				.content(com.example.utils.MasterData.asJsonString(projectDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	/***** get Project by id ***/
	@Test
	void testRestEndpointForFindingProjectByIdIsExposedAndWorking() throws Exception {
		ProjectDto projectDto = com.example.utils.MasterData.getProjectDto();
		Integer id = projectDto.getId();
		Mockito.when(projectService.findById(id)).thenReturn(projectDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projectservice/get/{id}", id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(projectDto)) ? true : false),
				businessTestFile);

	}

	@Test
	void testRestEndpointForFindingProjectByIdIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];
		ProjectDto projectDto = com.example.utils.MasterData.getProjectDto();
		Integer id = projectDto.getId();
		Mockito.when(projectService.findById(id)).then(new Answer<ProjectDto>() {

			@Override
			public ProjectDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return MasterData.getProjectDto();
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/projectservice/get/{id}", id)
				.content(com.example.utils.MasterData.asJsonString(projectDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

}
