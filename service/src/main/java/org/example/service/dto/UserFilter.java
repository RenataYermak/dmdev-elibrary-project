package org.example.service.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserFilter {

    String email;
    String password;
    String firstname;
    String lastname;
}
