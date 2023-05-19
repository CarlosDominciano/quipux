package com.app.quipux.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "One or more musics don't exists")
public class MusicNotFoundException extends RuntimeException{
}
