package mx.rmotad.notifications.applog.infraestructure.persistance;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.Size;
import org.springframework.data.mongodb.core.aggregation.ConvertOperators.ToString;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.StringOperators.Concat;
import org.springframework.data.mongodb.core.aggregation.UnionWithOperation;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AppLogRepository {

  private final MongoTemplate mongoTemplate;

  public List<AppLog> findLogRegisters() {
    var collection = mongoTemplate.getCollection("notifications");

    ProjectionOperation notificationProjectOperation = Aggregation.project("_id", "createdAt")
        .and(Concat.valueOf("category")
            .concat(" NOTIFICATION")).as("name");
    ProjectionOperation deliveryProjectOperation = Aggregation.project("_id", "createdAt")
        .and(Concat.valueOf("channel")
            .concat(" to ")
            .concatValueOf(ToString.toString(
                Size.lengthOfArray("destinations")))
            .concat( " subscribers")
        )
        .as("name");
    var union = UnionWithOperation.unionWith("deliveries").pipeline(deliveryProjectOperation);
    var agg = Aggregation.newAggregation(
        Arrays.asList(
            notificationProjectOperation,
            union
        )
    );

    var res = mongoTemplate.aggregate(agg, LogNotificationDocument.class,
        AppLog.class);
    return res.getMappedResults();
  }

  public record AppLog(String id, String name, Instant createdAt) {

  }
}
