package com.cg.resourcemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.resourcemanager.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
