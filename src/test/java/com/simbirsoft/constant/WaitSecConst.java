package com.simbirsoft.constant;

public enum WaitSecConst {

    FIVE_SECONDS(5),
    EIGHT_SECONDS(8),
    TEN_SECONDS(10);

    private final int seconds;

    WaitSecConst(int waitSec) {
        this.seconds = waitSec;
    }

    public int getSeconds() {
        return seconds;
    }
}
