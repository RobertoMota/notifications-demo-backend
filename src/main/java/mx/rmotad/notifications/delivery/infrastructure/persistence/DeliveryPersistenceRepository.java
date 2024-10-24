package mx.rmotad.notifications.delivery.infrastructure.persistence;

import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.delivery.domain.Delivery;
import mx.rmotad.notifications.delivery.domain.DeliveryRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryPersistenceRepository implements DeliveryRepository {

  private final DeliveryMongoRepository mongoRepository;
  private final DeliveryMapper mapper;

  @Override
  public void save(Delivery delivery) {
    mongoRepository.save(mapper.domainToDocument(delivery));
  }

}
