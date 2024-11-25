## TODO:

- [ ] Leggi il codice di [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java).
- [ ] Aggiungi una nuova classe infrastrutturale `SpringRabbitMqPostNotifier` con il codice relativo alla pubblicazione
  del `PostMessageCommand` preso
  da [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java#L34-L35)
- [ ] Utilizza `SpringRabbitMqPostNotifier`
  in [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java) al posto di `RabbitTemplate` e
  `Jsonb`.
- [ ] Estrai un'interfaccia `PostNotifier`.
- [ ] Utilizza `PostNotifier` in [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java) al
  posto di `SpringRabbitMqPostNotifier`.
- [ ] Elimina l'uso di `@Testcontainers`, del container MongoDB, - del vero repository dalla e l'uso di
  `@SpringBootTest` dalla
  suite [DefaultDailyBalanceServiceTest](src/test/java/io/doubleloop/drivenpush/DefaultPostServiceTest.java) attraverso
  l'uso di `Mockito.mock(class)`.
- [ ] Sposta i file nei rispettivi package/folder in base alla loro responsabilità.
- [ ] Rimuovi l'annotazione `@Disabled`
  da [DependencyRulesTest](src/test/java/io/doubleloop/drivenpush/DependencyRulesTest.java#L9) e
  verifica che i test siano verdi.


