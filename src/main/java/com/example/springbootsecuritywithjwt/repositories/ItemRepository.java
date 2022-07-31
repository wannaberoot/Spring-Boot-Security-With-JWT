package com.example.springbootsecuritywithjwt.repositories;

import com.example.springbootsecuritywithjwt.models.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemInfo, Integer> {

    ItemInfo findItemById(int id);
    List<ItemInfo> findAll();
}
