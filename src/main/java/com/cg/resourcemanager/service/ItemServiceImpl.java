package com.cg.resourcemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.resourcemanager.dto.ItemDto;
import com.cg.resourcemanager.repo.ItemRepository;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepo;

	@Override
	public List<ItemDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemDto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemDto addItem(ItemDto itemDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemDto deleteItem(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemDto updateItem(ItemDto itemDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
