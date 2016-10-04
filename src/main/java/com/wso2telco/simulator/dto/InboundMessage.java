package com.wso2telco.simulator.dto;

/**
 * Created by isuru dilshan on 9/27/16.
 */
public class InboundMessage {

    private InboundUSSDMessageRequest inboundUSSDMessageRequest;

    public InboundMessage() {
    }

    public InboundUSSDMessageRequest getInboundUSSDMessageRequest() {
        return this.inboundUSSDMessageRequest;
    }

    public void setInboundUSSDMessageRequest(InboundUSSDMessageRequest inboundUSSDMessageRequest) {
        this.inboundUSSDMessageRequest = inboundUSSDMessageRequest;
    }

    @Override
    public String toString() {
        return "InboundMessage{" +
                "inboundUSSDMessageRequest=" + inboundUSSDMessageRequest +
                '}';
    }
}
