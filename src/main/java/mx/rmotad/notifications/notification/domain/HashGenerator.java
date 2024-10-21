package mx.rmotad.notifications.notification.domain;

import java.security.NoSuchAlgorithmException;

@FunctionalInterface
public interface HashGenerator {

  String calculateHash(String sourceValue) throws NoSuchAlgorithmException;
}
