package com.example.springbootsecuritywithjwt.service.item;

import com.example.springbootsecuritywithjwt.model.ItemInfo;
import com.example.springbootsecuritywithjwt.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public List<ItemInfo> getAllItems(final int count) {
        return itemRepository.findAll().stream().limit(count).collect(Collectors.toList());
    }

    public ItemInfo getItem(final int id) {
        return itemRepository.findItemById(id);
    }
}
