package mx.rmotad.notifications.delivery.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DeliveryMongoRepository extends MongoRepository<DeliveryDocument, String> {

}
