// I eliminate duplication (Chapter 12: Emergence) by extracting validation into private helper methods.
public class MaritimeFreightStrategy implements ShippingStrategy{
    public double calculateCost(Order order) throws ShippingException{
        validateZone(order);
        double weightCost = order.getTotalWeight() * PricingRules.MARITIME_WEIGHT_MULTIPLIER;
        double distanceCost = order.getDistance() * PricingRules.MARITIME_DISTANCE_MULTIPLIER;
        return PricingRules.MARITIME_BASE_RATE + weightCost + distanceCost;
    }

    public int estimateDeliveryDays(Order order) throws ShippingException{
        validateZone(order);
        return PricingRules.INTERNATIONAL_DELIVERY_DAYS;
    }

    public TransportType getTransportType(){
        return TransportType.SHIP;
    }

    private void validateZone(Order order) throws ShippingException{
        if(order.getZone() == DeliveryZone.LOCAL){
            throw new ShippingException("ships are not used for local delivery");
        }
    }
}
