package com.wso2telco.simulator.dto;

/**
 * Created by isuru dilshan on 9/22/16.
 */
public class OutboundUSSDMessageRequest {

    private String address;
    private String shortCode;
    private String keyword;
    private String outboundUSSDMessage;
    private String clientCorrelator;
    private ResponseRequest responseRequest;
    private String ussdAction;
    private String deliveryStatus;

    public OutboundUSSDMessageRequest() {
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShortCode() {
        return this.shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOutboundUSSDMessage() {
        return this.outboundUSSDMessage;
    }

    public void setOutboundUSSDMessage(String outboundUSSDMessage) {
        this.outboundUSSDMessage = outboundUSSDMessage;
    }

    public String getClientCorrelator() {
        return this.clientCorrelator;
    }

    public void setClientCorrelator(String clientCorrelator) {
        this.clientCorrelator = clientCorrelator;
    }

    public ResponseRequest getResponseRequest() {
        return this.responseRequest;
    }

    public void setResponseRequest(ResponseRequest responseRequest) {
        this.responseRequest = responseRequest;
    }

    public String getUssdAction() {
        return this.ussdAction;
    }

    public void setUssdAction(String ussdAction) {
        this.ussdAction = ussdAction;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return "OutboundUSSDMessageRequest{" +
                "address='" + address + '\'' +
                ", shortCode='" + shortCode + '\'' +
                ", keyword='" + keyword + '\'' +
                ", outboundUSSDMessage='" + outboundUSSDMessage + '\'' +
                ", clientCorrelator='" + clientCorrelator + '\'' +
                ", responseRequest=" + responseRequest +
                ", ussdAction='" + ussdAction + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
    }

}
