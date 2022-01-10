package com.company.jmixtest1.pojo;

import java.io.Serializable;

public class RequestResult implements Serializable {
    private String resultMessage;

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
