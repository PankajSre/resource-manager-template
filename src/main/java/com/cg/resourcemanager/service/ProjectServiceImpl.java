package com.cg.resourcemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.resourcemanager.dto.ProjectDto;
import com.cg.resourcemanager.repo.ProjectRepository;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projRepo;

	@Override
	public List<ProjectDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectDto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectDto add(ProjectDto project) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProjectDto deleteByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
