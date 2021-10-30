package com.cg.resourcemanager.service;

import java.util.List;

import com.cg.resourcemanager.dto.ItemDto;

public interface ItemService {

	public List<ItemDto> findAll();

	public ItemDto findById(Integer id);

	public ItemDto addItem(ItemDto itemDto);

	public ItemDto deleteItem(Integer id);

	public ItemDto updateItem(ItemDto itemDto);
}
