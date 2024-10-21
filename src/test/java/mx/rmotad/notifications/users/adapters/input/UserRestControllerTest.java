package mx.rmotad.notifications.users.adapters.input;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import mx.rmotad.notifications.users.application.IUserApplicationService;
import mx.rmotad.notifications.users.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IUserApplicationService service;

  @Test
  void testGetUsersByCategory_whenCategoryExistsAndUsersSubscribed_success() throws Exception {
    when(service.getUsersByCategory(anyString())).thenReturn(
        List.of(new User("", "", "", "", null, null)));
    mockMvc.perform(get("/users").param("category", "EMAIL"))
        .andExpect(status().isOk());
  }

  @Test
  void testGetUsersByCategory_whenCategoryExistsAndUsersIsNotSubscribed_emptyList()
      throws Exception {
    when(service.getUsersByCategory(anyString())).thenReturn(
        List.of(new User("", "", "", "", null, null)));
    var res = mockMvc.perform(get("/users").param("category", "EMAIL"))
        .andExpect(status().isOk()).andReturn().getResponse();
    System.out.println(res);
  }

}