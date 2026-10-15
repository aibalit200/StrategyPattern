// I encapsulate the air freight logic in this single class (Chapter 11: Systems) to separate concerns.
public class ExpressAirStrategy implements ShippingStrategy{
    public double calculateCost(Order order) throws ShippingException{
        validateWeight(order);
        double distanceCost = order.getDistance() * PricingRules.AIR_DISTANCE_MULTIPLIER;
        return PricingRules.AIR_BASE_RATE + distanceCost;
    }

    public int estimateDeliveryDays(Order order) throws ShippingException{
        validateWeight(order);
        if(order.getZone() == DeliveryZone.LOCAL){
            return PricingRules.AIR_LOCAL_DAYS;
        }
        if(order.getZone() == DeliveryZone.NATIONAL){
            return PricingRules.AIR_NATIONAL_DAYS;
        }
        return PricingRules.AIR_INTERNATIONAL_DAYS;
    }

    public TransportType getTransportType(){
        return TransportType.AIRPLANE;
    }

    private void validateWeight(Order order) throws ShippingException{
        if(order.getTotalWeight() > PricingRules.MAX_AIR_WEIGHT){
            throw new ShippingException("cargo too heavy for air transport");
        }
    }
}
