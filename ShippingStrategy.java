public interface ShippingStrategy{
    double calculateCost(Order order) throws ShippingException;
    int estimateDeliveryDays(Order order) throws ShippingException;
    TransportType getTransportType();
}
