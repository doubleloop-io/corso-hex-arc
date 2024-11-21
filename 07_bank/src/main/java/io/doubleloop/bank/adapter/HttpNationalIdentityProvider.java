package io.doubleloop.bank.adapter;

import java.time.LocalDate;

public class HttpNationalIdentityProvider {

  // NOTE: First of all define the Domain's Port (method name, parameters and return value)
  // and then implement it with this Adapter.

  private GetInfoResponse getInfo(String cf) {
    // NOTE: This is a fake implementation, it should be replaced with a real one
    return new GetInfoResponse(
        "foo",
        "bar",
        LocalDate.of(1982, 11, 8)
    );
  }

  private static class GetInfoResponse {
    public final String firstName;
    public final String lastName;
    public final LocalDate dateOfBirth;

    private GetInfoResponse(String firstName, String lastName, LocalDate dateOfBirth) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.dateOfBirth = dateOfBirth;
    }
  }
}
