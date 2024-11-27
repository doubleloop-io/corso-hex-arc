## TODO:

Obiettivo: testare il Driver Adapter [UserController](src/main/java/io/doubleloop/driverimplicit/UserController.java) senza dipendere dall'Application.

- [X] Leggi i test del
  controller [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java), come vedi sono
  implementati come Integration Test e utilizzano un container MongoDB per funzionare, a causa dell'accoppiameno
  ApiController (Adapter) -> DomainService -> Repository.
- [X] Rinomina la classe [UserService](src/main/java/io/doubleloop/driverexplicit/UserService.java) in
  `DefaultUserService`.
- [X] Estrai l'interfaccia `UserService` partendo
  dalla classe [DefaultUserService](src/main/java/io/doubleloop/driverexplicit/DefaultUserService.java).
- [X] Assicurati che il field [userService](src/main/java/io/doubleloop/driverexplicit/UserController.java#L16) sia
  di tipo `UserService` (interface).
- [X] Aggiungi un field `userService`
  in [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java) e annotalo con `@MockBean`.
- [X] Aggiungi una `when(userService...).thenReturns(...)` expression in ogni test
  di [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java) per pilotare lo stub.
- [X] Verifica che tutti i test
  di [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java) siano verdi.
- [X] Elimina il field `container` e relative annotazioni
  da [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java).
- [X] Elimina il field `userRepository`, annotazioni e relativi utilizzi
  da [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java).
- [X] Verifica che tutti i test
  di [UserControllerTest](src/test/java/io/doubleloop/driverexplicit/UserControllerTest.java) siano verdi.
- [X] Sposta i file nei rispettivi package/folder in base alla loro responsabilità.
- [X] Rimuovi l'annotazione `@Disabled`
  da [DependencyRulesTest](src/test/java/io/doubleloop/driverexplicit/DependencyRulesTest.java#L9) e
  verifica che i test siano verdi.
