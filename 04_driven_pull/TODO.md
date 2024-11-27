## TODO:

Obiettivo: testare [DefaultDailyBalanceService](src/main/java/io/doubleloop/drivenpull/DefaultDailyBalanceService.java) senza l'utilizzo dell'infrastruttura (containers) introducendo le Driven Port di tipo Pull.

- [X] Leggi il codice
  di [DefaultDailyBalanceService](src/main/java/io/doubleloop/drivenpull/DefaultDailyBalanceService.java) e le relative
  dipendenze [OperationRepository](src/main/java/io/doubleloop/drivenpull/OperationRepository.java)
  e [ExchangeProvider](src/main/java/io/doubleloop/drivenpull/ExchangeProvider.java). Che problemi hanno questi nomi?
- [X] Rinomina la classe
  infrastrutturale [OperationRepository](src/main/java/io/doubleloop/drivenpull/OperationRepository.java) aggiungendo un
  prefisso che identifica la tecnologia utilizzata.
- [X] Estrai l'interfaccia `OperationRepository` di dominio e usala
  in [DefaultDailyBalanceService](src/main/java/io/doubleloop/drivenpull/DefaultDailyBalanceService.java) al posto della
  relativa classe.
- [X] Rinomina la classe
  infrastrutturale [ExchangeProvider](src/main/java/io/doubleloop/drivenpull/ExchangeProvider.java) aggiungendo un
  prefisso che identifica la tecnologia utilizzata.
- [X] Estrai l'interfaccia `ExchangeProvider` di dominio e usala
  in [DefaultDailyBalanceService](src/main/java/io/doubleloop/drivenpull/DefaultDailyBalanceService.java) al posto della
  relativa classe.
- [X] Elimina l'uso di `@Testcontainers`, del container MongoDB e del vero repository dalla
  suite [DefaultDailyBalanceServiceTest](src/test/java/io/doubleloop/drivenpull/DefaultDailyBalanceServiceTest.java)
  attraverso l'uso di `@MockBean`.
- [X] Elimina l'uso di `@SpringBootTest` dalla
  suite [DefaultDailyBalanceServiceTest](src/test/java/io/doubleloop/drivenpull/DefaultDailyBalanceServiceTest.java)
  attraverso l'uso di `Mockito.mock(class)`.
- [X] Sposta i file nei rispettivi package/folder in base alla loro responsabilità.
- [X] Rimuovi l'annotazione `@Disabled`
  da [DependencyRulesTest](src/test/java/io/doubleloop/drivenpull/DependencyRulesTest.java#L9) e
  verifica che i test siano verdi.
