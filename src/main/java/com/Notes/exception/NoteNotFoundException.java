package com.Notes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Note does not exist")
public class NoteNotFoundException extends RuntimeException {

}
