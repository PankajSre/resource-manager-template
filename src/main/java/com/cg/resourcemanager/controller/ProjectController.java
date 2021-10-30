package com.cg.resourcemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.resourcemanager.service.ProjectService;

@RestController
@RequestMapping("/projectservice")
public class ProjectController {

	@Autowired
	ProjectService projectService;

}
