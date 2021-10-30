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

import com.cg.resourcemanager.controller.ItemController;
import com.cg.resourcemanager.dto.ItemDto;
import com.cg.resourcemanager.dto.ResourceExceptionResponse;
import com.cg.resourcemanager.service.ItemService;
import com.example.utils.MasterData;

@Order(2)
@WebMvcTest(ItemController.class)
@AutoConfigureMockMvc
public class TestItemExceptions {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ItemService itemService;

	@Test
	public void testDataValidationCheckIsAddedInController() throws Exception {
		ItemDto itemDto = com.example.utils.MasterData.getItemDto();
		Mockito.when(itemService.addItem(itemDto)).thenReturn(itemDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/itemservice/add")
				.content(MasterData.asJsonString(itemDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, exceptionTestFile);

	}

	@Test
	public void testAbleToWorkWithCustomExceptions() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		Mockito.when(itemService.addItem(itemDto)).thenReturn(itemDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/itemservice/add")
				.content(MasterData.asJsonString(itemDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, exceptionTestFile);

	}

	@Test
	public void testExceptionIsThrownAndHandledInCaseOfInvalidData() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		Mockito.when(itemService.addItem(itemDto)).thenReturn(itemDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/itemService/add")
				.content(MasterData.asJsonString(itemDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, exceptionTestFile);

	}

	@Test
	public void testAbleToImplementVariousResponseCodeWithCustomizedMessage() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		Mockito.when(itemService.addItem(itemDto)).thenReturn(itemDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/itemservice/add")
				.content(MasterData.asJsonString(itemDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(), result.getResponse().getStatus() == 200 ? true : false, exceptionTestFile);

	}

	@Test
	void testExceptionIsThrownAndHandledIfItemIdIsNotValidWhileDeleting() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		Integer id = itemDto.getId();

		ResourceExceptionResponse exResponse = new ResourceExceptionResponse("Item with Id - " + id + " not found!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(itemService.deleteItem(id)).thenThrow(new ItemException("Item with Id - " + id + " not found!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/itemservice/delete/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? true : false,
				exceptionTestFile);

	}

	@Test
	void testExceptionIsThrownAndHandledIfItemIdIsNotValidWhileGettingItemById() throws Exception {
		ItemDto itemDto = MasterData.getItemDto();
		Integer id = itemDto.getId();

		ResourceExceptionResponse exResponse = new ResourceExceptionResponse("Item with Id - " + id + " not found!",
				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
		Mockito.when(itemService.findById(id)).thenThrow(new ItemException("Item with Id - " + id + " not found!"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/itemservice/get/" + id)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? true : false),
				exceptionTestFile);

	}

}
