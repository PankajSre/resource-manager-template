package com.cg.resourcemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.resourcemanager.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
