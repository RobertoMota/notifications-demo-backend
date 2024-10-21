package mx.rmotad.notifications.users.application;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import mx.rmotad.notifications.users.domain.Category;
import mx.rmotad.notifications.users.domain.User;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "notifications-app.users")
@Getter@Setter
public class UsersConfig {

  Map<Category, List<User>> defaultUsers;
}