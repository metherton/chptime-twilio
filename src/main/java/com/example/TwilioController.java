package com.example;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by martin on 27/09/16.
 */
@RestController
public class TwilioController {

    public static final String ACCOUNT_SID = "Ac..";
    public static final String AUTH_TOKEN = "0c..";

    @RequestMapping(value="/call/{telephoneNumber}", method= RequestMethod.GET)
    public String call(@PathVariable("telephoneNumber") String telephoneNumber) {

        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account mainAccount = client.getAccount();
        CallFactory callFactory = mainAccount.getCallFactory();
        Map<String, String> callParams = new HashMap<String, String>();
        callParams.put("To", telephoneNumber); // Replace with your phone number
        callParams.put("From", "+23424"); // Replace with a Twilio number
        callParams.put("Method", "GET");
        callParams.put("Url", "http://8b43b8b6.ngrok.io/voice.xml");

        try {
            Call call = callFactory.create(callParams);
        } catch (TwilioRestException e) {
            e.printStackTrace();
        }
        return "ok";
    }

}
