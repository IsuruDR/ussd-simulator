package com.wso2telco.simulator.controller;

import com.google.gson.Gson;
import com.wso2telco.simulator.dto.*;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @RequestMapping(value = "/ussd/v1/outbound/{senderAddress}", method = RequestMethod.POST)
    public USSDRequest ussdRequest(@RequestBody String payload, @PathVariable String senderAddress) {

        System.out.println(truststorePath);

        USSDRequest ussdRequest = new USSDRequest();
        ResponseRequest inboundResReq = new ResponseRequest();
        ResponseRequest repReq = new ResponseRequest();

        JSONObject outboundUSSDMessageJsonRequest = (JSONObject) new JSONObject(payload).get("outboundUSSDMessageRequest");

        OutboundUSSDMessageRequest outboundUSSDMessageRequest = new Gson()
                .fromJson(outboundUSSDMessageJsonRequest.toString(), OutboundUSSDMessageRequest.class);

        ResponseRequest responseRequest = outboundUSSDMessageRequest.getResponseRequest();

        logger.info("Received Request" + outboundUSSDMessageRequest);

        inboundResReq.setCallbackData(responseRequest.getCallbackData());
        inboundResReq.setNotifyURL(responseRequest.getNotifyURL());

        InboundMessage inboundMsgRequest = getInboundMessage(senderAddress, inboundResReq, outboundUSSDMessageRequest);
        OutboundUSSDMessageRequest outboundReq = getOutboundUSSDMessageRequest(senderAddress, repReq, outboundUSSDMessageRequest);


        repReq.setCallbackData(responseRequest.getCallbackData());
        repReq.setNotifyURL(responseRequest.getNotifyURL());

        ussdRequest.setOutboundUSSDMessageRequest(outboundReq);

        System.setProperty("javax.net.ssl.trustStore", truststorePath);
        System.setProperty("javax.net.ssl.trustStorePassword", truststorePassword);

//        post(responseRequest.getNotifyURL(), inboundMsgRequest);

        return ussdRequest;
    }

    private void post(String url, InboundMessage inboundMsgRequest) {
        logger.info("Posting data : " + inboundMsgRequest + " to url :" + url);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(url, inboundMsgRequest, String.class);
    }

    private OutboundUSSDMessageRequest getOutboundUSSDMessageRequest(@PathVariable String senderAddress, ResponseRequest repReq, OutboundUSSDMessageRequest outboundUSSDMessageRequest) {
        OutboundUSSDMessageRequest outboundReq = new OutboundUSSDMessageRequest();
        outboundReq.setAddress(senderAddress);
        outboundReq.setShortCode(outboundUSSDMessageRequest.getShortCode());
        outboundReq.setKeyword(outboundUSSDMessageRequest.getKeyword());
        outboundReq.setOutboundUSSDMessage(outboundUSSDMessageRequest.getOutboundUSSDMessage());
        outboundReq.setUssdAction(outboundUSSDMessageRequest.getUssdAction());
        outboundReq.setClientCorrelator(outboundUSSDMessageRequest.getClientCorrelator());
        outboundReq.setDeliveryStatus("SENT");
        outboundReq.setResponseRequest(repReq);
        return outboundReq;
    }

    private InboundMessage getInboundMessage(@PathVariable String senderAddress, ResponseRequest inboundRepReq, OutboundUSSDMessageRequest outboundUSSDMessageRequest) {
        InboundUSSDMessageRequest inboundReq = new InboundUSSDMessageRequest();
        InboundMessage inboundMsg = new InboundMessage();

        inboundReq.setAddress(senderAddress);
        inboundReq.setShortCode(outboundUSSDMessageRequest.getShortCode());
        inboundReq.setKeyword(outboundUSSDMessageRequest.getKeyword());
        inboundReq.setInboundUSSDMessage("1");
        inboundReq.setUssdAction(USSDAction.mtcont);
        inboundReq.setClientCorrelator(outboundUSSDMessageRequest.getClientCorrelator());
        inboundReq.setResponseRequest(inboundRepReq);

        inboundReq.setResponseRequest(inboundRepReq);
        inboundMsg.setInboundUSSDMessageRequest(inboundReq);
        return inboundMsg;
    }
}
