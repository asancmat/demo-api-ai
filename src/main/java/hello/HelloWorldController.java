package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restfb.DefaultJsonMapper;
import com.restfb.JsonMapper;
import com.restfb.types.webhook.WebhookObject;

import ai.api.model.AIRequest;

@Controller
@RequestMapping("/webhook")
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhook(@RequestBody String obj){

    	String response = "";
    	System.out.println("Entramos al metodo webhook");
        System.out.println(obj);
        
        System.out.println("---------------------");
        System.out.println("Parseamos el request");
        AIRequest request = new AIRequest(obj);
        System.out.println(request);
        System.out.println("---------------------");
        
        if(obj.contains("facebook")){
        	System.out.println("Peticion de facebook");
        	response = "hola usuario de facebook";
        	JsonMapper mapper = new DefaultJsonMapper();
        	WebhookObject webhookObject = mapper.toJavaObject(obj.toString(), WebhookObject.class);
        	System.out.println(webhookObject.toString());
        }
        else{
        	response = "webhook Hello!";
        }

        return new WebhookResponse(response, "webhook Text");
    }
}
