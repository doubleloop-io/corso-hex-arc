## TODO:

- [ ] Come cambiano le interazioni se non hai un ApiController come Driver Adapter ma un queue Consumer?
- [ ] Leggi il codice
  di [WorkRecordDemoProducer](src/main/java/io/doubleloop/driverreactive/WorkRecordDemoProducer.java) e relative
  dipendenze, in particolare `WorkRecordRequest` e i tipi di richiesta che il producer puo' inviare
- [ ] Leggi il codice di [WorkRecordConsumer](src/main/java/io/doubleloop/driverreactive/WorkRecordConsumer.java) e
  relative dipendenze, in particolare osserva il dispatch al service basato sul tipo di action richiesta.
- [ ] Sposta i file nei rispettivi package/folder in base alla loro responsabilità.
- [ ] Rimuovi l'annotazione `@Disabled`
  da [DependencyRulesTest](src/test/java/io/doubleloop/driverreactive/DependencyRulesTest.java#L9) e verifica che i test
  siano verdi.
