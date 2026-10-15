// I restrict this class to one job (Chapter 10: Classes) to maintain high cohesion.
public class StandardGroundStrategy implements ShippingStrategy{
    public double calculateCost(Order order) throws ShippingException{
        validateZone(order);
        double weightCost = order.getTotalWeight() * PricingRules.GROUND_WEIGHT_MULTIPLIER;
        double distanceCost = order.getDistance() * PricingRules.GROUND_DISTANCE_MULTIPLIER;
        return PricingRules.GROUND_BASE_RATE + weightCost + distanceCost;
    }

    public int estimateDeliveryDays(Order order) throws ShippingException{
        validateZone(order);
        if(order.getZone() == DeliveryZone.LOCAL){
            return PricingRules.LOCAL_DELIVERY_DAYS;
        }
        return PricingRules.NATIONAL_DELIVERY_DAYS;
    }

    public TransportType getTransportType(){
        return TransportType.TRUCK;
    }

    private void validateZone(Order order) throws ShippingException{
        if(order.getZone() == DeliveryZone.INTERNATIONAL){
            throw new ShippingException("trucks cannot cross oceans");
        }
    }
}
