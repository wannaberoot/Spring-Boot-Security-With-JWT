package com.example.springbootsecuritywithjwt.repository;

import com.example.springbootsecuritywithjwt.model.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<ItemInfo, Integer> {

    ItemInfo findItemById(int id);
}
