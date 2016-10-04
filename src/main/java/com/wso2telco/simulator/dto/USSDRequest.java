package com.wso2telco.simulator.dto;

/**
 * Created by isuru dilshan on 9/22/16.
 */
public class USSDRequest {

    private OutboundUSSDMessageRequest outboundUSSDMessageRequest = null;

    public USSDRequest() {
    }

    public OutboundUSSDMessageRequest getOutboundUSSDMessageRequest() {
        return this.outboundUSSDMessageRequest;
    }

    public void setOutboundUSSDMessageRequest(OutboundUSSDMessageRequest outboundUSSDMessageRequest) {
        this.outboundUSSDMessageRequest = outboundUSSDMessageRequest;
    }

    @Override
    public String toString() {
        return "USSDRequest{" +
                "outboundUSSDMessageRequest=" + outboundUSSDMessageRequest +
                '}';
    }
}
