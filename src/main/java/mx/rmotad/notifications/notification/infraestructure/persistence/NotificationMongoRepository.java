package mx.rmotad.notifications.notification.infraestructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationMongoRepository extends MongoRepository<NotificationDocument, String> {

  boolean existsByHash(String hash);
}
