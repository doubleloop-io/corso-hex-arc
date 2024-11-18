## TODO:

- [ ] Leggi il codice di [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java).
- [ ] Aggiungi una nuova classe infrastrutturale `SpringRabbitMqPostNotifier` con il codice relativo alla pubblicazione
  di `PostMessageCommand` preso da [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java)
- [ ] Utilizza `SpringRabbitMqPostNotifier`
  in [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java) al posto di `RabbitTemplate` e
  `Jsonb`.
- [ ] Estrai un'interfaccia `PostNotifier`.
- [ ] Utilizza `PostNotifier` in [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java) al
  posto di `SpringRabbitMqPostNotifier`.
- [ ] Sposta i file nei rispettivi package/folder in base alla responsabilità.
- [ ] Rimuovi l'annotazione `@Disabled`
  da [DependencyRulesTest](src/test/java/io/doubleloop/drivenpush/DependencyRulesTest.java#L9) e
  verifica che i test siano verdi.
