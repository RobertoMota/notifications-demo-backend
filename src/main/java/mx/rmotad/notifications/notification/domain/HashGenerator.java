package mx.rmotad.notifications.notification.domain;

import java.security.NoSuchAlgorithmException;

/**
 * Function that calculate the hash value of a given string
 */
@FunctionalInterface
public interface HashGenerator {

  String calculateHash(String sourceValue) throws NoSuchAlgorithmException;
}
