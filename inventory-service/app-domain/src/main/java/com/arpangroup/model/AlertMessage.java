package com.arpangroup.model;

import com.arpangroup.common.AlertType;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
public class AlertMessage {
    private String content;
    private String type;

    public AlertMessage(@NotNull AlertType alertType, String content) {
        this.type = alertType.name().toLowerCase();
        this.content = content;
    }

}
