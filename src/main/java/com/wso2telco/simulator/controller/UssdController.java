package com.wso2telco.simulator.controller;

import com.google.gson.Gson;
import com.wso2telco.simulator.dto.*;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Stack;

/**
 * Created by isuru dilshan on 9/22/16.
 */

@RestController
@Component
public class UssdController {

    @Value("${truststore.password}")
    private String truststorePassword;

    @Value("${truststore.path}")
    private String truststorePath;

    private Logger logger = Logger.getLogger(UssdController.class);

    private static Stack<OutboundUSSDMessageRequest> ussdStack = new Stack<>();

    private static boolean isAutoReply = false;

    @RequestMapping(value = "/ussd/v1/outbound/{senderAddress}", method = RequestMethod.POST)
    public USSDRequest handleUssd(@RequestBody String payload, @PathVariable String senderAddress) {

        USSDRequest ussdRequest = new USSDRequest();

        JSONObject json = (JSONObject) new JSONObject(payload).get("outboundUSSDMessageRequest");

        OutboundUSSDMessageRequest outboundUSSDMessageRequest = new Gson()
                .fromJson(json.toString(), OutboundUSSDMessageRequest.class);

        logger.info("Received Request" + outboundUSSDMessageRequest);

        InboundMessage inboundMsgRequest = getInboundMessage(outboundUSSDMessageRequest);

        OutboundUSSDMessageRequest outboundReq = getOutboundUSSDMessageRequest(outboundUSSDMessageRequest);

        ussdRequest.setOutboundUSSDMessageRequest(outboundReq);

        setSystemProprties();

        handleReply(outboundUSSDMessageRequest, inboundMsgRequest);

        return ussdRequest;
    }

    @RequestMapping(value = "/ussd/poll", method = RequestMethod.GET)
    public ResponseEntity pollRequest() {
        ResponseEntity responseEntity;

        if (!ussdStack.isEmpty()) {
            OutboundUSSDMessageRequest outboundUSSDMessageRequest = ussdStack.pop();
            responseEntity = new ResponseEntity(outboundUSSDMessageRequest, HttpStatus.OK);

            logger.info("Polling for new messages. Returning [ " + outboundUSSDMessageRequest + " ] ");
        } else {
            responseEntity = new ResponseEntity<OutboundUSSDMessageRequest>(HttpStatus.NO_CONTENT);
            logger.info("Polling for new messages. No new messages found");
        }
        return responseEntity;
    }

    @RequestMapping(value = "/ussd/updateAutoPlay/{isAutoPlay}")
    public ResponseEntity updateAutoReply(@PathVariable boolean isAutoPlay) {
        logger.info("Updating auto play to : " + isAutoPlay);

        isAutoReply = isAutoPlay;

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/ussd/reset")
    public ResponseEntity resetStack() {
        ussdStack.clear();

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private void handleReply(OutboundUSSDMessageRequest outboundUSSDMessageRequest, InboundMessage inboundMsgRequest) {
        if (isAutoReply) {
            logger.info("Auto reply enabled. Replying back to notify url");
            post(inboundMsgRequest);
        } else {
            ussdStack.push(outboundUSSDMessageRequest);
        }
    }

    private void setSystemProprties() {
        System.setProperty("javax.net.ssl.trustStore", truststorePath);
        System.setProperty("javax.net.ssl.trustStorePassword", truststorePassword);
    }

    private void post(InboundMessage inboundMsgRequest) {
        String url = inboundMsgRequest.getInboundUSSDMessageRequest().getResponseRequest().getNotifyURL();
        logger.info("Posting data : " + new Gson().toJson(inboundMsgRequest) + " to url :"
                + url);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, inboundMsgRequest, String.class);
    }

    private OutboundUSSDMessageRequest getOutboundUSSDMessageRequest(OutboundUSSDMessageRequest outboundUSSDMessageRequest) {
        OutboundUSSDMessageRequest outboundReq = new OutboundUSSDMessageRequest();
        ResponseRequest responseRequest = outboundUSSDMessageRequest.getResponseRequest();

        outboundReq.setAddress(outboundUSSDMessageRequest.getAddress());
        outboundReq.setShortCode(outboundUSSDMessageRequest.getShortCode());
        outboundReq.setKeyword(outboundUSSDMessageRequest.getKeyword());
        outboundReq.setOutboundUSSDMessage(outboundUSSDMessageRequest.getOutboundUSSDMessage());
        outboundReq.setUssdAction(outboundUSSDMessageRequest.getUssdAction());
        outboundReq.setClientCorrelator(outboundUSSDMessageRequest.getClientCorrelator());
        outboundReq.setDeliveryStatus("SENT");
        outboundReq.setResponseRequest(responseRequest);
        return outboundReq;
    }

    private InboundMessage getInboundMessage(OutboundUSSDMessageRequest outboundUSSDMessageRequest) {
        InboundUSSDMessageRequest inboundReq = new InboundUSSDMessageRequest();

        ResponseRequest responseRequest = outboundUSSDMessageRequest.getResponseRequest();
        ResponseRequest inboundResReq = new ResponseRequest();

        inboundResReq.setCallbackData(responseRequest.getCallbackData());
        inboundResReq.setNotifyURL(responseRequest.getNotifyURL());

        InboundMessage inboundMsg = new InboundMessage();

        inboundReq.setAddress("tel:+" + outboundUSSDMessageRequest.getAddress());
        inboundReq.setShortCode(outboundUSSDMessageRequest.getShortCode());
        inboundReq.setKeyword(outboundUSSDMessageRequest.getKeyword());
        inboundReq.setInboundUSSDMessage("1");
        inboundReq.setUssdAction(USSDAction.mtcont);
        inboundReq.setClientCorrelator(outboundUSSDMessageRequest.getClientCorrelator());
        inboundReq.setResponseRequest(inboundResReq);
        inboundMsg.setInboundUSSDMessageRequest(inboundReq);
        return inboundMsg;
    }
}
