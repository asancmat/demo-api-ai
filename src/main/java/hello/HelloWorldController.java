package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ai.api.model.AIRequest;

@Controller
@RequestMapping("/webhook")
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhook(@RequestBody String obj){

    	System.out.println("Entramos al metodo webhook");
        System.out.println(obj);
        
        System.out.println("---------------------");
        System.out.println("Parseamos el request");
        AIRequest request = new AIRequest(obj);
        System.out.println("---------------------");
        if(obj.contains("facebook")){
        	System.out.println("Peticion de facebook");
        }

        return new WebhookResponse("webhook Hello! ", "webhook Text");
    }
}
