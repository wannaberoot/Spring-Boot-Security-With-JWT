package com.example.springbootsecuritywithjwt.services.item;

import com.example.springbootsecuritywithjwt.models.ItemInfo;
import com.example.springbootsecuritywithjwt.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional
    public List<ItemInfo> getAllItems() {
        return itemRepository.findAll();
    }

    public ItemInfo getItem(final int id) {
        return itemRepository.findItemById(id);
    }

    public ItemInfo createItem(final String itemName) {
        final ItemInfo newItem = new ItemInfo(itemName);
        itemRepository.save(newItem);
        return newItem;
    }

    public ItemInfo updateItem(final int id, final String itemName) {
        final ItemInfo itemToUpdate = itemRepository.findItemById(id);
        itemToUpdate.setItemName(itemName);
        itemRepository.save(itemToUpdate);
        return itemToUpdate;
    }

    public void deleteItem(final int id) {
        itemRepository.deleteById(id);
    }
}
