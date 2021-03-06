package softuni.andreys.services;

import softuni.andreys.models.binding.ItemAddBindingModel;
import softuni.andreys.models.view.ItemViewModel;
import softuni.andreys.models.service.ItemServiceModel;

import java.util.List;

public interface ItemService {
    ItemServiceModel findItemByName(String name);

    ItemServiceModel addItem(ItemAddBindingModel itemAddBindingModel);

    List<ItemViewModel> findAll();

    ItemViewModel findItemById(String id);

    void deleteItemById(String id);
}
