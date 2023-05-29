package com.app.quipux.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Music already exists")
public class MusicAlreadyExistsException extends RuntimeException{
}
