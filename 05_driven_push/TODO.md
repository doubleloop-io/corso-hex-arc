## TODO:

Obiettivo: testare [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java) senza l'utilizzo dell'infrastruttura (containers) introducendo le Driven Port di tipo Push.

- [X] Leggi il codice di [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java).
- [X] Aggiungi una nuova classe infrastrutturale `SpringRabbitMqPostNotifier` con il codice relativo alla pubblicazione
  del `PostMessageCommand` preso
  da [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java#L34-L35)
- [X] Utilizza `SpringRabbitMqPostNotifier`
  in [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java) al posto di `RabbitTemplate` e
  `Jsonb`.
- [X] Estrai un'interfaccia `PostNotifier`.
- [X] Utilizza `PostNotifier` in [DefaultPostService](src/main/java/io/doubleloop/drivenpush/DefaultPostService.java) al
  posto di `SpringRabbitMqPostNotifier`.
- [X] Elimina l'uso di `@Testcontainers`, del container MongoDB, RabbitMQ, dell'adapter Repository, del Consumer e l'uso
  di `@SpringBootTest` dalla
  suite [DefaultPostServiceTest](src/test/java/io/doubleloop/drivenpush/DefaultPostServiceTest.java) attraverso l'uso di
  `Mockito.mock(class)`.
- [X] Sposta i file nei rispettivi package/folder in base alla loro responsabilità.
- [X] Rimuovi l'annotazione `@Disabled`
  da [DependencyRulesTest](src/test/java/io/doubleloop/drivenpush/DependencyRulesTest.java#L9) e
  verifica che i test siano verdi.