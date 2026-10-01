package com.springboot.basic.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

	@NotBlank(message = "User name is required")
	private String username;
	
	@NotBlank(message = "Password is required")
	private String password;
}
