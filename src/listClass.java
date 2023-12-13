import java.util.ArrayList;

public class listClass {
    private static ArrayList<Item> itemList = new ArrayList<>();

    public static ArrayList<Item> getItemList() {
        return itemList;
    }

    public static void addItem(Item item) {
        itemList.add(item);
    }
}
