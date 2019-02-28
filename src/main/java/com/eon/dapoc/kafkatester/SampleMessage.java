package com.eon.dapoc.kafkatester;

import lombok.Getter;
import lombok.Setter;

public class SampleMessage {

    @Getter
    @Setter
    private String timeStamp;

    @Getter
    @Setter
    private String message;
}
