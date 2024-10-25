package mx.rmotad.notifications.delivery.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import mx.rmotad.notifications.delivery.domain.Delivery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeliveryPersistenceRepositoryTest {

  private DeliveryPersistenceRepository deliveryRepo;
  private DeliveryMongoRepository mongoRepository;

  @BeforeEach
  void setUp() {
    mongoRepository = mock(DeliveryMongoRepository.class);
    deliveryRepo = new DeliveryPersistenceRepository(mongoRepository,
        DeliveryMapper.INSTANCE);
  }

  @Test
  void save() {
    deliveryRepo.save(mock(Delivery.class));
  }

  @Test
  void saveNull() {
    when(mongoRepository.save(any())).thenThrow(IllegalArgumentException.class);
    assertThrows(RuntimeException.class, () ->
        deliveryRepo.save(null));
  }

}