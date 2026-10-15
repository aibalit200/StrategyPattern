// I keep the client free of conditional logic (Chapter 17: Smells and Heuristics) by delegating entirely to the context.
public class ShippingClient{
    public static void main(String[] args){
        System.out.println("Setting up a small local order.");
        Order localOrder = new Order(DeliveryZone.LOCAL, 50);
        localOrder.addItem(new Item("Laptop", 5.0));
        localOrder.addItem(new Item("Mouse", 1.0));

        ShippingContext context = new ShippingContext(localOrder, new StandardGroundStrategy());

        System.out.println("Trying ground shipping.");
        try{
            printDetails(context);
        }
        catch(ShippingException error){
            System.out.println("Ground failed: " + error.getMessage());
        }

        System.out.println("Switching to air shipping.");
        try{
            context.setStrategy(new ExpressAirStrategy());
            printDetails(context);
        }
        catch(ShippingException error){
            System.out.println("Air failed: " + error.getMessage());
        }

        System.out.println("Switching to maritime shipping.");
        try{
            context.setStrategy(new MaritimeFreightStrategy());
            printDetails(context);
        }
        catch(ShippingException error){
            System.out.println("Maritime failed: " + error.getMessage());
        }

        System.out.println("Setting up a heavy international order.");
        Order heavyOrder = new Order(DeliveryZone.INTERNATIONAL, 5000);
        heavyOrder.addItem(new Item("Generator", 150.0));
        heavyOrder.addItem(new Item("Steel Beams", 300.0));

        context = new ShippingContext(heavyOrder, new ExpressAirStrategy());

        System.out.println("Trying air shipping.");
        try{
            printDetails(context);
        }
        catch(ShippingException error){
            System.out.println("Air failed: " + error.getMessage());
        }

        System.out.println("Switching to ground shipping.");
        try{
            context.setStrategy(new StandardGroundStrategy());
            printDetails(context);
        }
        catch(ShippingException error){
            System.out.println("Ground failed: " + error.getMessage());
        }

        System.out.println("Switching to maritime shipping.");
        try{
            context.setStrategy(new MaritimeFreightStrategy());
            printDetails(context);
        }
        catch(ShippingException error){
            System.out.println("Maritime failed: " + error.getMessage());
        }
    }

    private static void printDetails(ShippingContext context) throws ShippingException{
        double cost = context.executeCostCalculation();
        int days = context.executeDeliveryEstimation();
        TransportType type = context.getCurrentTransportType();

        System.out.println("Success with " + type + ".");
        System.out.println("Cost is $" + cost + " and it takes " + days + " days.");
    }
}