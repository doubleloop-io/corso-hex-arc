## TODO:

- [ ] Leggi il codice
  di [DefaultDailyBalanceService](src/main/java/io/doubleloop/drivenpull/DefaultDailyBalanceService.java) e le relative
  dipendenze [OperationRepository](src/main/java/io/doubleloop/drivenpull/OperationRepository.java)
  e [ExchangeProvider](src/main/java/io/doubleloop/drivenpull/ExchangeProvider.java). Che problemi hanno questi nomi?
- [ ] Rinomina la classe
  infrastrutturale [OperationRepository](src/main/java/io/doubleloop/drivenpull/OperationRepository.java) aggiungendo un
  prefisso che identifica la tecnologia utilizzata.
- [ ] Estrai l'interfaccia `OperationRepository` di dominio.
- [ ] Rinomina la classe
  infrastrutturale [ExchangeProvider](src/main/java/io/doubleloop/drivenpull/ExchangeProvider.java) aggiungendo un
  prefisso che identifica la tecnologia utilizzata.
- [ ] Estrai l'interfaccia `ExchangeProvider` di dominio.
- [ ] Elimina l'uso del container MongoDB e del vero repository dalla
  suite [DefaultDailyBalanceServiceTest](src/test/java/io/doubleloop/drivenpull/DefaultDailyBalanceServiceTest.java).
- [ ] Sposta i file nei rispettivi package/folder in base alla responsabilità.
- [ ] Rimuovi l'annotazione `@Disabled`
  da [DependencyRulesTest](src/test/java/io/doubleloop/drivenpull/DependencyRulesTest.java#L9) e
  verifica che i test siano verdi.
