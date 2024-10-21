package mx.rmotad.notifications.notification.infraestructure.persistence;

import static mx.rmotad.notifications.notification.NotificationTestUtils.createRandomNotificationDomain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class NotificationMapperTest {

  private final NotificationMapper mapper = NotificationMapper.INSTANCE;

  @Test
  void domainToDocument_whenDomain_returnDocument() {
    var domain = createRandomNotificationDomain();
    var document = mapper.domainToDocument(domain);

    assertNotNull(document);
    assertEquals(domain.id(), document.id());
    assertEquals(domain.category(), document.category());
    assertEquals(domain.content(), document.content());
    assertEquals(domain.hash(), document.hash());
    assertEquals(domain.createdAt(), document.createdAt());
  }

  @Test
  void domainToDocument_whenDomainNull_returnNull() {
    assertNull(mapper.domainToDocument(null));
  }
}