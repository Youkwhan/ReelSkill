package org.reelskill.controllers;

import org.reelskill.domain.Result;
import org.reelskill.domain.ResultType;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public class ResponseEntityExtensions {

    public static <T> ResponseEntity<Object> makeResponseEntity(Result<T> result, HttpStatus status) {
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(result.getMessages(), HttpStatus.BAD_REQUEST);
        } else if (result.getType() == ResultType.NOT_FOUND) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result.getPayload(), status);
    }

    public static ResponseEntity<?> makeBindingResultResponseEntity(BindingResult bindingResult) {
        List<String> messages = bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }
}
