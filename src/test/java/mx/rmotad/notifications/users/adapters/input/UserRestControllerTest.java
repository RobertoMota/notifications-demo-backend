package mx.rmotad.notifications.users.adapters.input;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.github.f4b6a3.ulid.UlidCreator;
import java.util.Collections;
import java.util.List;
import mx.rmotad.notifications.common.enums.NotificationCategory;
import mx.rmotad.notifications.common.enums.NotificationChannel;
import mx.rmotad.notifications.config.NoSecurityConfig;
import mx.rmotad.notifications.notification.application.dto.UserDTO;
import mx.rmotad.notifications.users.UserTestUtil;
import mx.rmotad.notifications.users.application.IUserApplicationService;
import mx.rmotad.notifications.users.domain.Category;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserRestController.class)
@Import(NoSecurityConfig.class)
class UserRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IUserApplicationService service;

  private static final Faker faker = new Faker();

  @Test
  void testGetUsersByCategory_whenCategoryExistsAndUsersSubscribed_success() throws Exception {
    var user = UserTestUtil.getRandomUser();
    when(service.getUsersByCategory(any(Category.class))).thenReturn(
        Collections.singletonList(user));
    mockMvc.perform(
            get("/users")
                .param("category", user.subscriptionList().get(0).name()))
        .andExpect(status().isOk());
  }

  @Test
  void testGetUsersByCategory_whenCategoryExistsAndUsersIsNotSubscribed_emptyList()
      throws Exception {
    var user = UserTestUtil.getRandomUser();
    when(service.getUsersByCategory(any(Category.class))).thenReturn(
        Collections.singletonList(user));
    var res = mockMvc.perform(
            get("/users").param("category", user.subscriptionList().get(0).name()))
        .andExpect(status().isOk()).andReturn().getResponse();
    System.out.println(res);
  }

  public static UserDTO getRandomUser(List<NotificationCategory> categories,
      List<NotificationChannel> channels) {

    return new UserDTO(
        UlidCreator.getMonotonicUlid().toLowerCase(),
        faker.name().name(),
        faker.internet().emailAddress(),
        faker.phoneNumber().cellPhone(),
        categories,
        channels
    );
  }

}