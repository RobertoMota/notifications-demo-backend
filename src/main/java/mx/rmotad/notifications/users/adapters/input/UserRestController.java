package mx.rmotad.notifications.users.adapters.input;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.users.application.IUserApplicationService;
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

  private final IUserApplicationService applicationService;

  @GetMapping
  public List<User> getUsers(@RequestParam Category category) {
    return applicationService.getUsersByCategory(category);
  }
}
