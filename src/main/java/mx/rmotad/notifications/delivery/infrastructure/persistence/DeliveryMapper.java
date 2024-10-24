package mx.rmotad.notifications.delivery.infrastructure.persistence;

import mx.rmotad.notifications.delivery.domain.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {

  DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);

  DeliveryDocument domainToDocument(Delivery domain);
}