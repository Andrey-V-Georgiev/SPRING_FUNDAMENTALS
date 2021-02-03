package softuni.andreys.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.andreys.models.binding.ItemAddBindingModel;
import softuni.andreys.models.entity.Item;
import softuni.andreys.models.service.ItemServiceModel;
import softuni.andreys.repositories.ItemRepository;
import softuni.andreys.services.CategoryService;
import softuni.andreys.services.ItemService;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper, CategoryService categoryService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @Override
    public ItemServiceModel findItemByName(String name) {
        ItemServiceModel itemServiceModel = this.itemRepository
                .findItemByName(name)
                .map(i -> this.modelMapper.map(i, ItemServiceModel.class))
                .orElse(null);
        return itemServiceModel;
    }

    @Override
    public ItemServiceModel addItem(ItemAddBindingModel itemAddBindingModel) {
        ItemServiceModel itemServiceModel = this.modelMapper.map(itemAddBindingModel, ItemServiceModel.class);
        itemServiceModel.setCategory(this.categoryService.findCategoryByName(itemAddBindingModel.getCategory()));

        /* SAVE TO DB */
        Item itemSaved = this.itemRepository.saveAndFlush(this.modelMapper.map(itemServiceModel, Item.class));
        return this.modelMapper.map(itemSaved, ItemServiceModel.class);
    }
}
