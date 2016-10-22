package com.wso2telco.simulator.dto;

/**
 * Created by isuru dilshan on 9/27/16.
 */
public class InboundUSSDMessageRequest {

    private String address;
    private String sessionID;
    private String shortCode;
    private String keyword;
    private String inboundUSSDMessage;
    private String clientCorrelator;
    private ResponseRequest responseRequest;
    private USSDAction ussdAction;

    public InboundUSSDMessageRequest() {
    }

    public String getAddress() {
        return this.address;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public String getShortCode() {
        return this.shortCode;
    }

    public String getInboundUSSDMessage() {
        return this.inboundUSSDMessage;
    }

    public String getClientCorrelator() {
        return this.clientCorrelator;
    }

    public ResponseRequest getResponseRequest() {
        return this.responseRequest;
    }

    public USSDAction getUssdAction() {
        return this.ussdAction;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setUssdAction(USSDAction ussdAction) {
        this.ussdAction = ussdAction;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInboundUSSDMessage(String inboundUSSDMessage) {
        this.inboundUSSDMessage = inboundUSSDMessage;
    }

    public void setClientCorrelator(String clientCorrelator) {
        this.clientCorrelator = clientCorrelator;
    }

    public void setResponseRequest(ResponseRequest responseRequest) {
        this.responseRequest = responseRequest;
    }

    @Override
    public String toString() {
        return "InboundUSSDMessageRequest{" +
                "address='" + address + '\'' +
                ", sessionID='" + sessionID + '\'' +
                ", shortCode='" + shortCode + '\'' +
                ", keyword='" + keyword + '\'' +
                ", inboundUSSDMessage='" + inboundUSSDMessage + '\'' +
                ", clientCorrelator='" + clientCorrelator + '\'' +
                ", responseRequest=" + responseRequest +
                ", ussdAction=" + ussdAction +
                '}';
    }
}
