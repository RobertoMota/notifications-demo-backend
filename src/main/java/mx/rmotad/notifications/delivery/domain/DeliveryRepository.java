package mx.rmotad.notifications.delivery.domain;

/**
 * Collection of deliveries
 */
public interface DeliveryRepository {

  /**
   * persists the given delivery
   *
   * @param delivery
   */
  void save(Delivery delivery);
}
