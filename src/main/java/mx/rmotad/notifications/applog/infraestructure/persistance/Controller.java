package mx.rmotad.notifications.applog.infraestructure.persistance;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app-log")
@RequiredArgsConstructor
public class Controller {

  private final AppLogRepository repository;

  @GetMapping
  public Object getLog() {
    var reg = repository.findLogRegisters();
    return reg;
  }

}
