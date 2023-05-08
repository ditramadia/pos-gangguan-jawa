package com.example.if2210_tb2_nge.components;

import javafx.event.Event;
import javafx.event.EventType;

public class TogglePointsEvent extends Event {

    public static final EventType<TogglePointsEvent> TOGGLE_BUTTON_CLICKED =
            new EventType<>(Event.ANY, "TOGGLE_BUTTON_CLICKED");

    public TogglePointsEvent() {
        super(TOGGLE_BUTTON_CLICKED);
    }
}
