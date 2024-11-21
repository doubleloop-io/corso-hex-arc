package io.doubleloop.bank.adapter;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionTask {
  public SubscriptionTask() {
  }

  @Scheduled(cron = "0 0 1 * *")
  public void executeTask() throws Exception {
  }
}
