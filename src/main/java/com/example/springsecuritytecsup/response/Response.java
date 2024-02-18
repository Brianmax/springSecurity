package com.example.springsecuritytecsup.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
public class Response {
    private String message;
    private String code;
    private Optional data;
}
