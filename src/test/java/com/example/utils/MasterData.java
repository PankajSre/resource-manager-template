package com.example.utils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cg.resourcemanager.dto.AllocationDto;
import com.cg.resourcemanager.dto.ItemDto;
import com.cg.resourcemanager.dto.ProjectDto;
import com.cg.resourcemanager.model.Allocation;
import com.cg.resourcemanager.model.Item;
import com.cg.resourcemanager.model.Project;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MasterData {

	public static AllocationDto getAllocationDto() throws ParseException {
		AllocationDto allocationDto = new AllocationDto();
		allocationDto.setId(1);
		allocationDto.setItemId(1);
		allocationDto.setPoAmount(200.10f);
		allocationDto.setProjectId(1);
		//allocationDto.setLife(new SimpleDateFormat("dd-MMM-yyyy").parse("20-Oct-2021"));
		return allocationDto;
	}

	public static Allocation getAllocation() {
		Allocation allocation = new Allocation();
		allocation.setId(1);
		allocation.setItemId(2);
		return allocation;
	}

	public static Item getItem() {
		Item item = new Item();
		item.setId(1);
		item.setType("HardWare");
		item.setName("Dell Laptop");
		item.setCost(200F);
		return item;
	}

	public static ItemDto getItemDto() {
		ItemDto itemDto = new ItemDto();
		itemDto.setId(1);
		itemDto.setType("HardWare");
		itemDto.setName("Dell Laptop");
		itemDto.setCost(200F);
		return itemDto;
	}

	public static Project getProject() {
		Project project = new Project();
		project.setId(1);
		project.setDescription("Product Managerment SME");
		project.setName("Product Manage");
		project.setStartDate(new Date());
		return project;
	}

	public static ProjectDto getProjectDto() {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setId(1);
		projectDto.setDescription("Product Managerment SME");
		projectDto.setName("Product Manage");
		//projectDto.setStartDate(new Date());
		return projectDto;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] toJson(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

	public static Allocation createAllocation(Integer id, int itemId, int userId, int projectId, Date date, int poNo,
			float poAmount, Date life) {
		Allocation allocation = new Allocation();
		return allocation;
	}

	public static Item createItem(int id, String name, String type, float cost) {
		Item item = new Item();
		return item;
	}

	public static Project createProject(int id, String name, String description, Date startDate, Date endDate) {
		Project project = new Project();
		return project;
	}

}
