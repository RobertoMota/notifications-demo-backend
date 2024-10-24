package mx.rmotad.notifications.applog.application;

import lombok.RequiredArgsConstructor;
import mx.rmotad.notifications.applog.infraestructure.persistance.AppLogRepository;
import mx.rmotad.notifications.common.UseCase;

@UseCase
@RequiredArgsConstructor
public class AppLogUseCases {

  private final AppLogRepository appLogRepository;


  public void getAllLogRecords() {
    appLogRepository.findLogRegistes();
  }
}