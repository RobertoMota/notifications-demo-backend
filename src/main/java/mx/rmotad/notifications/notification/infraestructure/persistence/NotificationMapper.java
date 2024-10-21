package mx.rmotad.notifications.notification.infraestructure.persistence;

import mx.rmotad.notifications.notification.domain.model.NotificationDomain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

  NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

  NotificationDocument domainToDocument(NotificationDomain domain);
}