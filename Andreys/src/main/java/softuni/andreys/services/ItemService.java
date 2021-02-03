package softuni.andreys.services;

import softuni.andreys.models.binding.ItemAddBindingModel;
import softuni.andreys.models.service.ItemServiceModel;

public interface ItemService {
    ItemServiceModel findItemByName(String name);

    ItemServiceModel addItem(ItemAddBindingModel itemAddBindingModel);
}
