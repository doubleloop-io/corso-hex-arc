## TODO:

- [ ] Leggi il codice di [UserController](src/main/java/io/doubleloop/driverimplicit/UserController.java)
  e [UserService](src/main/java/io/doubleloop/driverimplicit/UserService.java).
- [ ] Implementa i metodi di mapping da DTO del WebController a DTO del
  DomainService [as*](src/main/java/io/doubleloop/driverimplicit/RegisterUserRequest.java#L19-L25).
- [ ] Aggiungi [qui il codice](src/main/java/io/doubleloop/driverimplicit/UserController.java#L26) per restituire una
  risposta 200 OK con l'email nel body della risposta quando il risultato è "success".
- [ ] Aggiungi [qui il codice](src/main/java/io/doubleloop/driverimplicit/UserController.java#L25) per restituire una
  risposta 422 UNPROCESSABLE_ENTITY con l'errore nel body della risposta quando il risultato è "error".
- [ ] Rimuovi l'annotazione `@Disabled`
  da [UserControllerTest](src/test/java/io/doubleloop/driverimplicit/UserControllerTest.java#L16) e verifica che tutti i
  test siano verdi.
- [ ] Rimuovi l'annotazione `@Disabled`
  da [UserControllerNoSpringTest](src/test/java/io/doubleloop/driverimplicit/UserControllerNoSpringTest.java#L9) e
  verifica che tutti i test siano verdi. Un test è fallito, perché?
- [ ] Sposta i file nei rispettivi package/folder in base alla loro responsabilità.
- [ ] Rimuovi l'annotazione `@Disabled`
  da [DependencyRulesTest](src/test/java/io/doubleloop/driverimplicit/DependencyRulesTest.java#L9) e
  verifica che i test siano verdi.
