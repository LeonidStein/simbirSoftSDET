package com.simbirsoft.constant;

public enum UIConst {

    ALERT_TEXT_AFTER_POSITIVE_SUBMIT("Message received!"),
    ALERT_TEXT_BEFORE_NEGATIVE_SUBMIT("Message not received!"),
    NAME_FIELD_FILLING_PAGE("Form Fields");

    private final String text;

    UIConst(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
