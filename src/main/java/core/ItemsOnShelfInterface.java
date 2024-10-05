package core;

import Controller.ItemsOnShelfController;
import View.ItemsOnShelfView;

public class ItemsOnShelfInterface implements PointOfSales {

    @Override
    public void getInterface() {
        ItemsOnShelfView view = new ItemsOnShelfView();
        ItemRepository itemRepository = new ItemRepository();


        String product = "Electronics";


        Item model = new Item();

        ItemsOnShelfController controller = new ItemsOnShelfController(view, model, itemRepository);
        view.setVisible(true);
    }
}
