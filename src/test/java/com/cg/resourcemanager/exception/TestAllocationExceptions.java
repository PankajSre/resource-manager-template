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

import com.cg.resourcemanager.controller.AllocationController;
import com.cg.resourcemanager.dto.AllocationDto;
import com.cg.resourcemanager.dto.ResourceExceptionResponse;
import com.cg.resourcemanager.service.AllocationService;
import com.example.utils.MasterData;

@Order(2)
@WebMvcTest(AllocationController.class)
@AutoConfigureMockMvc
public class TestAllocationExceptions {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AllocationService allocationservice;

	@Test
	public void testDataValidationCheckIsAddedInController() throws Exception {
		AllocationDto allocationDto = com.example.utils.MasterData.getAllocationDto();
		Mockito.when(allocationservice.addAllocation(allocationDto)).thenReturn(allocationDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/allocationservice/add")
				.content(MasterData.asJsonString(allocationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, exceptionTestFile);

	}

	@Test
	public void testAbleToWorkWithCustomExceptions() throws Exception {
		AllocationDto allocationDto = MasterData.getAllocationDto();
		Mockito.when(allocationservice.addAllocation(allocationDto)).thenReturn(allocationDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/allocationservice/add")
				.content(MasterData.asJsonString(allocationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, exceptionTestFile);

	}

	@Test
	public void testExceptionIsThrownAndHandledInCaseOfInvalidData() throws Exception {
		AllocationDto allocationDto = MasterData.getAllocationDto();
		Mockito.when(allocationservice.addAllocation(allocationDto)).thenReturn(allocationDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/allocationservice/add")
				.content(MasterData.asJsonString(allocationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, exceptionTestFile);

	}

	@Test
	public void testAbleToImplementVariousResponseCodeWithCustomizedMessage() throws Exception {
		AllocationDto allocationDto = MasterData.getAllocationDto();
		Mockito.when(allocationservice.addAllocation(allocationDto)).thenReturn(allocationDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/allocationservice/add")
				.content(MasterData.asJsonString(allocationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, exceptionTestFile);

	}

	@Test
	void testExceptionIsThrownAndHandledIfAllocationIdIsNotValidWhileDeleting() throws Exception {

		AllocationDto allocationDto = MasterData.getAllocationDto();
		Integer id = allocationDto.getId();

		ResourceExceptionResponse exResponse = new ResourceExceptionResponse(
				"Allocation with Id - " + id + " not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(allocationservice.deleteAllocation(id))
				.thenThrow(new AllocationException("Allocation with Id - " + id + " not found!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/allocationservice/delete/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? true : false,
				exceptionTestFile);

	}

	@Test
	void testExceptionIsThrownAndHandledIfAllocationIdIsNotValidWhileGettingAllocationById() throws Exception {
		AllocationDto allocationDto = MasterData.getAllocationDto();
		Integer id = allocationDto.getId();

		ResourceExceptionResponse exResponse = new ResourceExceptionResponse(
				"Allocation with Id - " + id + " not found!", System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(allocationservice.findById(id))
				.thenThrow(new AllocationException("Allocation with Id - " + id + " not found!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allocationservice/get/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? true : false),
				exceptionTestFile);

	}

}
