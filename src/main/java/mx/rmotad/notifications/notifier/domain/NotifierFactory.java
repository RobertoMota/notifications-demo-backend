package mx.rmotad.notifications.notifier.domain;

import mx.rmotad.notifications.notifier.domain.model.NotifierChannel;

@FunctionalInterface
public interface NotifierFactory {

  Notifier getNotifier(NotifierChannel channel);
}
