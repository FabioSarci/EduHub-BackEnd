package com.fabio.sarcinelli.eduhub_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class SigninRequest {
	@NotBlank
	private String username;

	@NotBlank
	private String password;

}