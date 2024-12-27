package com.infobasic.sviluppo_sowftare.utility;

import java.util.Arrays;
import java.util.List;

public enum UserType {
    STUDENT (Arrays.asList()),
    TEACHER (Arrays.asList()),
    ADMIN (Arrays.asList(Action.MANAGE_USERS, Action.MANAGE_CLASSES));

    private final List<Action> allowedActions;

    private UserType(List<Action> allowedActions){
        this.allowedActions = allowedActions;
    }

    public List<Action> getAllowedActions() {
        return allowedActions;
    }
};