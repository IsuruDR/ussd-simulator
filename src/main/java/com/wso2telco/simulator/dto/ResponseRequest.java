package com.wso2telco.simulator.dto;

/**
 * Created by isuru dilshan on 9/22/16.
 */
public class ResponseRequest {

    private String notifyURL;
    private String callbackData;

    public ResponseRequest() {
    }

    public String getNotifyURL() {
        return this.notifyURL;
    }

    public void setNotifyURL(String notifyURL) {
        this.notifyURL = notifyURL;
    }

    public String getCallbackData() {
        return this.callbackData;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }

    @Override
    public String toString() {
        return "ResponseRequest{" +
                "notifyURL='" + notifyURL + '\'' +
                ", callbackData='" + callbackData + '\'' +
                '}';
    }
}
