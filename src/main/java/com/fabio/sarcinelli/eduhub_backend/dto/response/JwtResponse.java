package com.fabio.sarcinelli.eduhub_backend.dto.response;

import java.util.Set;

import com.fabio.sarcinelli.eduhub_backend.util.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private Set<ERole> roles;

  public JwtResponse(String token, Long id, String username, String email, Set<ERole> roles2) {
    this.token = token;
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles2;
  }

}