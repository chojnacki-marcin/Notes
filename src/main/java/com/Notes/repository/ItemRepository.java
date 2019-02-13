package com.Notes.repository;

import com.Notes.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
