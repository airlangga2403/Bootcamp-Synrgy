package challange2.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<challange2.model.Menu> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(challange2.model.Menu item) {
        items.add(item);
    }

    public List<challange2.model.Menu> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0;
        for (challange2.model.Menu item : items) {
            total += item.getHarga();
        }
        return total;
    }
}
