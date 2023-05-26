package com.scumm.loom.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private String name;
    public ResourceNotFoundException(String name) {
        this.name = name;
    }
}
