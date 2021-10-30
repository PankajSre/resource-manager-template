package com.cg.resourcemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.resourcemanager.service.ItemService;

@RestController
@RequestMapping("/itemservice")
public class ItemController {

	@Autowired
	ItemService itemService;

}
