// I keep the context algorithm-agnostic (Chapter 8: Boundaries) so it doesn't depend on concrete strategy details.
public class ShippingContext{
    private ShippingStrategy strategy;
    private Order order;

    public ShippingContext(Order order, ShippingStrategy strategy){
        this.order = order;
        this.strategy = strategy;
    }

    public void setStrategy(ShippingStrategy strategy){
        this.strategy = strategy;
    }

    public double executeCostCalculation() throws ShippingException{
        return strategy.calculateCost(order);
    }

    public int executeDeliveryEstimation() throws ShippingException{
        return strategy.estimateDeliveryDays(order);
    }

    public TransportType getCurrentTransportType(){
        return strategy.getTransportType();
    }
}
