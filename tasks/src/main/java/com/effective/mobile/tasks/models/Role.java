package com.effective.mobile.tasks.models;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
    USER("USER"),
    ADMIN("ADMIN");

    private final String roleName;

    public String getRoleName() {
        return name().toLowerCase();
    }
}
