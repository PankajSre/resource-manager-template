package com.cg.resourcemanager.service;

import java.util.List;

import com.cg.resourcemanager.dto.ProjectDto;

public interface ProjectService {

	public List<ProjectDto> findAll();

	public ProjectDto findById(Integer id);

	public ProjectDto add(ProjectDto project);

	public ProjectDto deleteByID(Integer id);

}
