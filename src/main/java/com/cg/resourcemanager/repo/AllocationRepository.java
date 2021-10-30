package com.cg.resourcemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.resourcemanager.model.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Integer> {

}
