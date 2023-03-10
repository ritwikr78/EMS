package com.app.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
	@NotNull
	private String message;
	@NotNull
	private LocalDateTime timestamp;
}
