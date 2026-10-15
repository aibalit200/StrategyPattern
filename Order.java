import java.util.List;
import java.util.ArrayList;

public class Order{
    private List<Item> items;
    private double totalWeight;
    private DeliveryZone zone;
    private int distance;

    public Order(DeliveryZone zone, int distance){
        this.items = new ArrayList<>();
        this.totalWeight = 0;
        this.zone = zone;
        this.distance = distance;
    }

    public void addItem(Item item){
        items.add(item);
        totalWeight = totalWeight + item.getWeight();
    }

    public Item getItem(int position){
        return items.get(position);
    }

    public int getItemCount(){
        return items.size();
    }

    public double getTotalWeight(){
        return totalWeight;
    }

    public DeliveryZone getZone(){
        return zone;
    }

    public int getDistance(){
        return distance;
    }
}
