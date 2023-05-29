package com.app.quipux.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Music can't have release year bigger than now")
public class MusicYearIsAfterTodayException extends RuntimeException {

}
