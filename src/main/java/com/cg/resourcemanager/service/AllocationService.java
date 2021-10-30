package com.cg.resourcemanager.service;

import java.util.List;

import com.cg.resourcemanager.dto.AllocationDto;

public interface AllocationService {

	public AllocationDto findById(Integer id);

	public AllocationDto addAllocation(AllocationDto allocation);

	public AllocationDto deleteAllocation(Integer id);

	public List<AllocationDto> findAllAllocation();

}
