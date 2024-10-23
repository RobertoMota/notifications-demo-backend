package mx.rmotad.notifications.notification;

import com.github.f4b6a3.ulid.UlidCreator;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import mx.rmotad.notifications.notification.domain.model.NotificationCategory;
import mx.rmotad.notifications.notifier.application.model.User;
import mx.rmotad.notifications.notifier.domain.model.NotifierCategory;
import mx.rmotad.notifications.notification.domain.model.NotificationDomain;
import mx.rmotad.notifications.notifier.domain.model.NotifierChannel;
import net.datafaker.Faker;

@UtilityClass
public class NotificationTestUtils {

  private static final Faker faker = new Faker();
  public static final String MESSAGE = "message";
  public static final String HASH = "hash";

  public static User getRandomUser() {
    return getRandomUser(
        getEnumRandomSublist(NotifierCategory.class),
        getEnumRandomSublist(NotifierChannel.class));
  }

  public static User getRandomUser(List<NotifierCategory> categories,
      List<NotifierChannel> channels) {
    return new User(
        UlidCreator.getMonotonicUlid().toLowerCase(),
        faker.name().name(),
        faker.internet().emailAddress(),
        faker.phoneNumber().cellPhone(),
        categories,
        channels
    );
  }

  public static List<User> getRandomUserList() {
    List<List<Set<?>>> combinations = generateCombinations(Set.of(NotifierCategory.values()),
        Set.of(NotifierChannel.values()));

    return combinations.stream()
        .map(combination ->
            getRandomUser(
                combination.get(0).stream().map(cat -> (NotifierCategory) cat)
                    .collect(Collectors.toList()),
                combination.get(1).stream().map(cat -> (NotifierChannel) cat)
                    .collect(Collectors.toList())
            ))
        .toList();
  }

  public static User[] getRandomUserArray() {
    return getRandomUserList().toArray(User[]::new);
  }

  public static NotificationDomain createRandomNotificationDomain() {
    return new NotificationDomain(UlidCreator.getMonotonicUlid().toLowerCase(),
        getRandomValueFromEnum(
            NotificationCategory.class), MESSAGE, HASH, Instant.now());
  }

  private static <T extends Enum<T>> T getRandomValueFromEnum(
      Class<T> enumClass) {
    T[] enumConstants = enumClass.getEnumConstants();
    int randomIndex = ThreadLocalRandom.current().nextInt(enumConstants.length);
    return enumConstants[randomIndex];
  }

  private static <T extends Enum<T>> List<T> getEnumRandomSublist(Class<T> enumeration) {
    var enumValues = Arrays.asList(enumeration.getEnumConstants());
    Collections.shuffle(enumValues);
    int items = ThreadLocalRandom.current().nextInt(0, enumValues.size());
    return enumValues.subList(0, items);
  }


  /**
   * Generates all possible combinations between the subsets of the 2 sets
   *
   * @param setA a set of elements to combine
   * @param setB a set of elements to combine
   * @param <T>  type of elements in set A
   * @param <U>  type of elements in set B
   * @return a list with where each element is a list of 2 subset from A and B
   */
  public static <T, U> List<List<Set<?>>> generateCombinations(Set<T> setA, Set<U> setB) {
    Set<Set<T>> subsetsA = getSubsets(setA);
    Set<Set<U>> subsetsB = getSubsets(setB);

    List<List<Set<?>>> result = new ArrayList<>();
    for (Set<T> subsetA : subsetsA) {
      for (Set<U> subsetB : subsetsB) {
        if (!subsetA.isEmpty() && !subsetB.isEmpty()) {
          List<Set<?>> combination = new ArrayList<>();
          combination.add(subsetA);
          combination.add(subsetB);
          result.add(combination);
        }
      }
    }

    return result;
  }

  /**
   * Gets all subsets of a given set
   *
   * @param set the set of values
   * @param <T> the type of the elements in set
   * @return a set containing the subsets of the given set
   */
  private static <T> Set<Set<T>> getSubsets(Set<T> set) {
    Set<Set<T>> subsets = new HashSet<>();
    if (set.isEmpty()) {
      subsets.add(new HashSet<>());
      return subsets;
    }

    T first = set.iterator().next();
    Set<T> rest = new HashSet<>(set);
    rest.remove(first);

    for (Set<T> subset : getSubsets(rest)) {
      Set<T> newSubset = new HashSet<>(subset);
      newSubset.add(first);
      subsets.add(newSubset);
      subsets.add(subset);
    }

    return subsets;
  }
}
