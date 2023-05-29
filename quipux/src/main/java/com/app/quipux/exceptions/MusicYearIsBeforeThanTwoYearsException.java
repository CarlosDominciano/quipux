package com.app.quipux.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Music can't have release year older than 2 years")
public class MusicYearIsBeforeThanTwoYearsException extends RuntimeException {

}
