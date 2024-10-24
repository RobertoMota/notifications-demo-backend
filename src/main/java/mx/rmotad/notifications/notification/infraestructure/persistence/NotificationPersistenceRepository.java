package mx.rmotad.notifications.notification.infraestructure.persistence;

import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.notification.domain.NotificationRepository;
import mx.rmotad.notifications.notification.domain.model.NotificationCategory;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;
import org.springframework.stereotype.Component;

/**
 * Implementation of the Domain Notification Repository
 */
@Component
@RequiredArgsConstructor
public class NotificationPersistenceRepository implements NotificationRepository {

  private final NotificationMongoRepository mongoRepository;
  private final NotificationMapper mapper;

  @Override
  public boolean existsByCategoryChecksum(NotificationCategory category, String hash) {
    return mongoRepository.existsByHash(hash);
  }

  @Override
  public void save(NotificationDomain notification) {

    mongoRepository.save(mapper.domainToDocument(notification));

  }
}
