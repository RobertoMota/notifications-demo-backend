package mx.rmotad.notifications.notification.adapters.input;

import static mx.rmotad.notifications.notification.NotificationTestUtils.createRandomNotificationDomain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import mx.rmotad.notifications.common.enums.NotificationCategory;
import mx.rmotad.notifications.notification.application.NotificationUseCases;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(NotificationRestController.class)
class NotificationRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private NotificationUseCases notificationUseCases;
  private String request;

  @SneakyThrows
  @BeforeEach
  void setUp() {
    request = new ObjectMapper().writeValueAsString(
        new NotificationRequest(NotificationCategory.SPORTS, "SomeMEssage"));
  }

  @Test
  void testCreateNotification_whenRequestOk_success() throws Exception {
    NotificationDomain notificationDomain = createRandomNotificationDomain();
    doReturn(
        notificationDomain)
        .when(notificationUseCases).newNotificationUseCase(
            any(NotificationCategory.class), anyString());

    var responseBytes = mockMvc.perform(
            post("/notifications")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
        .andExpect(status().isCreated())
        .andReturn().getResponse().getContentAsByteArray();
    var response = new ObjectMapper().readValue(responseBytes, NotificationResponse.class);
    assertNotNull(response);
    assertEquals(notificationDomain.id(), response.id());
  }

}