package mx.rmotad.notifications.applog.infraestructure.persistance;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AppLogRepository {

  private final MongoTemplate mongoTemplate;

  public AggregationResults<LogDeliveryDocument> findLogRegistes() {
    var lookup = LookupOperation.newLookup().from("notifications")
        .localField("_id")
        .foreignField("notificationId")
        .as("deliveries");
    var agg = Aggregation.newAggregation(lookup);
    var res = mongoTemplate.aggregate(agg, LogNotificationDocument.class,
        LogDeliveryDocument.class);
    return res;
  }

}
