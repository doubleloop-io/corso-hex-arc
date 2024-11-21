package io.doubleloop.bank.adapter;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

  @PostMapping("/")
  public ResponseEntity<String> openAccount(@Valid @RequestBody OpenAccountRequest request) {
    return ResponseEntity.internalServerError().build();
  }

  @PostMapping("/{accountId}")
  public ResponseEntity<String> makeTransaction(@PathVariable("accountId") String accountId, @Valid @RequestBody MakeTransactionRequest request) {
    return ResponseEntity.internalServerError().build();
  }

  @DeleteMapping("/{accountId}")
  public ResponseEntity<Void> closeAccount(@PathVariable("accountId") String accountId) {
    return ResponseEntity.internalServerError().build();
  }

  @PostMapping("/transfer")
  public ResponseEntity<Void> makeTransfer(@Valid @RequestBody TransferAmountRequest request) {
    return ResponseEntity.internalServerError().build();
  }
}
