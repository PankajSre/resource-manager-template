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

import com.cg.resourcemanager.controller.ItemController;
import com.cg.resourcemanager.dto.ItemDto;
import com.cg.resourcemanager.service.ItemService;
import com.example.utils.MasterData;

@Order(1)
@WebMvcTest(ItemController.class)
@AutoConfigureMockMvc
class ItemControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ItemService itemService;

	@Test
	void testRestEndpointForFindAllItemsIsExposedAndWorking() throws Exception {
		List<ItemDto> list = new ArrayList<ItemDto>();
		list.add(MasterData.getItemDto());
		Mockito.when(itemService.findAll()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/itemservice/all")
				.content(MasterData.asJsonString(MasterData.getItemDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list)) ? "true" : "false"),
				businessTestFile);

	}

	@Test
	void testAbleToDefineAppropriateClassesAndObjectsForAGivenScenario() throws Exception {
		List<ItemDto> list = new ArrayList<ItemDto>();
		list.add(MasterData.getItemDto());
		Mockito.when(itemService.findAll()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/itemservice/all")
				.content(MasterData.asJsonString(MasterData.getItemDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list)) ? "true" : "false"),
				businessTestFile);

	}

	@Test
	void testAbleToConfigureAndConnectToDatabase() throws Exception {
		List<ItemDto> list = new ArrayList<ItemDto>();
		list.add(MasterData.getItemDto());
		Mockito.when(itemService.findAll()).thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/itemservice/all")
				.content(MasterData.asJsonString(MasterData.getItemDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(list)) ? "true" : "false"),
				businessTestFile);

	}

	@Test
	void testRestEndpointForFindAllItemsIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];
		List<ItemDto> list = new ArrayList<ItemDto>();
		list.add(MasterData.getItemDto());

		Mockito.when(itemService.findAll()).then(new Answer<List<ItemDto>>() {

			@Override
			public List<ItemDto> answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return list;
			}
		});

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/itemservice/all")
				.content(MasterData.asJsonString(MasterData.getItemDto())).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	/** add item ** */

	@Test
	void testRestEndpointForAddItemIsExposedAndWorking() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		Mockito.when(itemService.addItem(any(ItemDto.class))).thenReturn(itemDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/itemservice/add")
				.content(MasterData.asJsonString(itemDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(asJsonString(itemDto)) ? true : false,
				businessTestFile);

	}

	@Test
	void testRestEndpointForAddNewItemIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];

		ItemDto itemDto = MasterData.getItemDto();
		Mockito.when(itemService.addItem(any(ItemDto.class))).then(new Answer<ItemDto>() {

			@Override
			public ItemDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return itemDto;
			}
		});

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/itemservice/add")
				.content(MasterData.asJsonString(itemDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	/*** delete ****/

	@Test
	void testRestEndpointForDeletingItemIsExposedAndWorking() throws Exception {
		ItemDto ItemDto = MasterData.getItemDto();
		Integer id = ItemDto.getId();
		Mockito.when(itemService.deleteItem(id)).thenReturn(ItemDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/itemservice/delete/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(asJsonString(ItemDto)) ? true : false,
				businessTestFile);

	}

	@Test
	void testRestEndpointForDeletingItemIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];
		ItemDto ItemDto = MasterData.getItemDto();
		Integer id = ItemDto.getId();
		Mockito.when(itemService.deleteItem(id)).then(new Answer<ItemDto>() {

			@Override
			public ItemDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return MasterData.getItemDto();
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/itemservice/delete/{id}", id)
				.content(MasterData.asJsonString(ItemDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

	/***** get item by id ***/
	@Test
	void testRestEndpointForFindingItemByIdIsExposedAndWorking() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		Integer id = itemDto.getId();
		Mockito.when(itemService.findById(id)).thenReturn(itemDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/itemservice/get/{id}" , id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(asJsonString(itemDto)) ? true : false),
				businessTestFile);

	}

	@Test
	void testRestEndpointForFindingItemByIdIsBeingImplementedUsingMultilayerdArchitecture() throws Exception {
		final int count[] = new int[1];
		ItemDto ItemDto = MasterData.getItemDto();
		Integer id = ItemDto.getId();
		Mockito.when(itemService.findById(id)).then(new Answer<ItemDto>() {

			@Override
			public ItemDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return MasterData.getItemDto();
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/itemservice/get/{id}", id)
				.content(MasterData.asJsonString(ItemDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}

}
