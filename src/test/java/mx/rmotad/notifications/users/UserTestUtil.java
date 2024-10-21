package mx.rmotad.notifications.users;

import com.github.f4b6a3.ulid.UlidCreator;
import java.util.Arrays;
import lombok.experimental.UtilityClass;
import mx.rmotad.notifications.users.domain.Category;
import mx.rmotad.notifications.users.domain.Channel;
import mx.rmotad.notifications.users.domain.User;
import net.datafaker.Faker;

@UtilityClass
public class UserTestUtil {

  private static final Faker faker = new Faker();

  public static User getRandomUser() {
    return new User(
        UlidCreator.getMonotonicUlid().toLowerCase(),
        faker.name().name(),
        faker.internet().emailAddress(),
        faker.phoneNumber().cellPhone(),
        Arrays.asList(Category.values()),
        Arrays.asList(Channel.values())
    );
  }
}
