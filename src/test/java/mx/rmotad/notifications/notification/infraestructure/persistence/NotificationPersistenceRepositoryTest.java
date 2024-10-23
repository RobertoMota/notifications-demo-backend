package mx.rmotad.notifications.notification.infraestructure.persistence;

import static mx.rmotad.notifications.notification.NotificationTestUtils.createRandomNotificationDomain;
import static mx.rmotad.notifications.notification.domain.model.NotificationCategory.SPORTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import mx.rmotad.notifications.notification.domain.model.NotificationCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class NotificationPersistenceRepositoryTest {

  public static final String HASH_STRING = "hash";
  private NotificationMongoRepository mongoRepository;
  private NotificationPersistenceRepository repository;

  @BeforeEach
  void setUp() {
    mongoRepository = mock(NotificationMongoRepository.class);
    repository = new NotificationPersistenceRepository(mongoRepository,
        mock(NotificationMapper.class));
  }

  @ParameterizedTest
  @EnumSource(NotificationCategory.class)
  void testExistsByCategoryHash_whenExists_returnsTrue(NotificationCategory category) {
    when(mongoRepository.existsByHash(HASH_STRING)).thenReturn(true);
    var exists = repository.existsByCategoryChecksum(category, HASH_STRING);
    assertTrue(exists);
  }

  @ParameterizedTest
  @ValueSource(booleans = {true, false})
  void testExistsByCategoryHash_whenExists_returnsTrue(boolean exists) {
    when(mongoRepository.existsByHash(HASH_STRING)).thenReturn(exists);

    assertEquals(exists, repository.existsByCategoryChecksum(SPORTS, HASH_STRING));
  }

  @Test
  void testSave_whenAllIsOk_success() {
    repository.save(createRandomNotificationDomain());
  }

  @Test
  void testSave_whenMongoError_error() {
    when(mongoRepository.save(any())).thenThrow(RuntimeException.class);
    assertThrows(RuntimeException.class, () -> repository.save(createRandomNotificationDomain()));
  }

}