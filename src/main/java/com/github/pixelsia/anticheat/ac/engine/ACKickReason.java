package com.github.pixelsia.anticheat.ac.engine;

public enum ACKickReason {

    SPEED("異常なスピード", "SPEEDHACK");

    String reason;
    String consoleReason;
    ACKickReason(String reason, String consoleReason) {
        this.reason = reason;
        this.consoleReason = consoleReason;
    }

    public String getReason() {
        return reason;
    }

    public String getConsoleReason() {
        return consoleReason;
    }
}
