## TODO:

- [ ] Leggi i test del
  controller [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java), come vedi sono
  implementati come Integration Test ed utilizzano un container MongoDB per funzionare, a causa dell'accoppiameno ApiController (Adapter) -> DomainService -> Repository.
- [ ] Rinomina la classe [UserService](src/main/java/io/doubleloop/driverexplicit/UserService.java) in
  `DefaultUserService`.
- [ ] Estrai l'interfaccia `UserService` partendo
  dalla classe [DefaultUserService](src/main/java/io/doubleloop/driverexplicit/DefaultUserService.java).
- [ ] Assicurati che il field [userService](src/main/java/io/doubleloop/driverexplicit/UserController.java#L16) sia
  di tipo `UserService` (interface).
- [ ] Aggiungi un field `userService`
  in [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java) e annotalo con `@Mock`.
- [ ] Aggiungi una `when(userService...).thenReturns(...)` expression in ogni test di [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java) per pilotare lo stub.
- [ ] Verifica che tutti i test
  di [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java) siano verdi.
- [ ] Elimina il field `container` e relative annotazioni
  da [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java).
- [ ] Elimina il field `userRepository`, annotazioni e relativi utilizzi
  da [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java).
- [ ] Verifica che tutti i test
  di [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java) siano verdi.
- [ ] Sposta i file nei rispettivi package/folder in base alla responsabilità.
- [ ] Rimuovi l'annotazione `@Disabled`
  da [DependencyRulesTest](src/test/java/io/doubleloop/driverexplicit/DependencyRulesTest.java#L9) e
  verifica che i test siano verdi.
