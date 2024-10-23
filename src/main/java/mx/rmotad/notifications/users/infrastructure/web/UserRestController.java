package mx.rmotad.notifications.users.infrastructure.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.users.application.UserApplicationService;
import mx.rmotad.notifications.users.application.UserService;
import mx.rmotad.notifications.users.domain.Category;
import mx.rmotad.notifications.users.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This REST controller holds the available endpoints for users
 */
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserRestController {

  private final UserService userService;

  @GetMapping
  public List<User> getUsers(@RequestParam Category category) {
    return userService.getUsersByCategory(category);
  }
}
