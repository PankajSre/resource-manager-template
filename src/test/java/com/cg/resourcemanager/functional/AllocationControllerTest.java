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

import com.cg.resourcemanager.controller.AllocationController;
import com.cg.resourcemanager.dto.AllocationDto;
import com.cg.resourcemanager.service.AllocationService;
import com.example.utils.MasterData;

@Order(1)
@WebMvcTest(AllocationController.class)
@AutoConfigureMockMvc
class AllocationControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AllocationService allocationService;

	@Test
	void testRestEndpointForFindAllAllocationsIsExposedAndWorking() throws Exception {
		List<AllocationDto> list = new ArrayList<AllocationDto>();
		list.add(MasterData.getAllocationDto());
		Mockito.when(allocationService.findAllAllocation()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allocationservice/all")
				.content(MasterData.asJsonString(MasterData.getAllocationDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list)) ? "true" : "false"),
				businessTestFile);

	}

	@Test
	void testAbleToDefineAppropriateClassesAndObjectsForAGivenScenario() throws Exception {
		List<AllocationDto> list = new ArrayList<AllocationDto>();
		list.add(MasterData.getAllocationDto());
		Mockito.when(allocationService.findAllAllocation()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allocationservice/all")
				.content(MasterData.asJsonString(MasterData.getAllocationDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list)) ? "true" : "false"),
				businessTestFile);

	}

	@Test
	void testAbleToConfigureAndConnectToDatabase() throws Exception {
		List<AllocationDto> list = new ArrayList<AllocationDto>();
		list.add(MasterData.getAllocationDto());
		Mockito.when(allocationService.findAllAllocation()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allocationservice/all")
				.content(MasterData.asJsonString(MasterData.getAllocationDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list)) ? "true" : "false"),
				businessTestFile);

	}

	@Test
	void testRestEndpointForFindAllAllocationIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];
		List<AllocationDto> list = new ArrayList<AllocationDto>();
		list.add(MasterData.getAllocationDto());

		Mockito.when(allocationService.findAllAllocation()).then(new Answer<List<AllocationDto>>() {
			@Override
			public List<AllocationDto> answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return list;
			}
		});

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allocationservice/all")
				.content(MasterData.asJsonString(MasterData.getAllocationDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	/** add allocation ** */

	@Test
	void testRestEndpointForAddAllocationIsExposedAndWorking() throws Exception {
		AllocationDto allocationDto = MasterData.getAllocationDto();
		Mockito.when(allocationService.addAllocation(any(AllocationDto.class))).thenReturn(allocationDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/allocationservice/add")
				.accept(MediaType.APPLICATION_JSON).content(MasterData.asJsonString(allocationDto))
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(asJsonString(allocationDto)) ? true : false,
				businessTestFile);

	}

	@Test
	void testRestEndpointForAddNewAllocationIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];

		AllocationDto allocationDto = MasterData.getAllocationDto();
		Mockito.when(allocationService.addAllocation(any(AllocationDto.class))).then(new Answer<AllocationDto>() {

			@Override
			public AllocationDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return allocationDto;
			}
		});

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/allocationService/add")
				.accept(MediaType.APPLICATION_JSON).content(MasterData.asJsonString(allocationDto))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), count[0] == 0 ? true : false, businessTestFile);

	}

	/*** delete ****/

	//@Test
	void testRestEndpointForDeletingAllocationIsExposedAndWorking() throws Exception {
		AllocationDto allocationDto = MasterData.getAllocationDto();
		Integer id = allocationDto.getId();
		Mockito.when(allocationService.deleteAllocation(id)).thenReturn(allocationDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/allocationService/delete/{id}", id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, businessTestFile);

	}

	@Test
	void testRestEndpointForDeletingAllocationIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];
		AllocationDto allocationDto = MasterData.getAllocationDto();
		Integer id = allocationDto.getId();
		Mockito.when(allocationService.deleteAllocation(id)).then(new Answer<AllocationDto>() {
			@Override
			public AllocationDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return MasterData.getAllocationDto();
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/allocationService/delete/{id}", id)
				.accept(MediaType.APPLICATION_JSON).content(MasterData.asJsonString(allocationDto))
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), count[0] == 0 ? true : false, businessTestFile);

	}

	/***** get allocation by id ***/
	//@Test
	void testRestEndpointForFindingAllocationByIdIsExposedAndWorking() throws Exception {
		AllocationDto allocationDto = MasterData.getAllocationDto();
		Integer id = allocationDto.getId();
		Mockito.when(allocationService.findById(id)).thenReturn(allocationDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allocationService/get/{id}", id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(allocationDto)) ? true : false),
				businessTestFile);
	}

	@Test
	void testRestEndpointForFindingAllocationByIdIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];
		AllocationDto allocationDto = MasterData.getAllocationDto();
		Integer id = allocationDto.getId();
		Mockito.when(allocationService.findById(id)).then(new Answer<AllocationDto>() {

			@Override
			public AllocationDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return MasterData.getAllocationDto();
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/allocationService/get/{id}", id)
				.content(MasterData.asJsonString(allocationDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), count[0] == 0 ? true : false, businessTestFile);

	}

}
