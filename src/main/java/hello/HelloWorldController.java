package hello;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restfb.DefaultJsonMapper;
import com.restfb.JsonMapper;
import com.restfb.types.webhook.WebhookObject;

import ai.api.model.AIContext;
import ai.api.model.AIRequest;
import ai.api.model.Entity;

@Controller
@RequestMapping("/webhook")
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody WebhookResponse webhook(@RequestBody String obj) {

		String response = "";
		System.out.println("Entramos al metodo webhook");
		System.out.println(obj);

		System.out.println("---------------------");
		System.out.println("Parseamos el request");
		AIRequest request = new AIRequest(obj);
		System.out.println(request);
		System.out.println("---------------------");

		if (obj.contains("facebook")) {
			System.out.println("Peticion de facebook");
			response = "hola usuario de facebook";
			System.out.println("entidades: ");
			System.out.println("***");
			if (null != request.getEntities()) {
				for (Entity entidad : request.getEntities()) {
					System.out.println(entidad);
					System.out.println("***");
				}
			} else {
				System.out.println("No hay entidades");
			}
			System.out.println("***");
			
			System.out.println("Recuperamos los datos de la query");
			String[] query = new String[]{obj};
			System.out.println(query[3]);
			//List<AIContext> contexts = query[3];

			JsonMapper mapper = new DefaultJsonMapper();
			WebhookObject webhookObject = mapper.toJavaObject(obj.toString(),
					WebhookObject.class);
			System.out.println(webhookObject.toString());
		} else {
			response = "webhook Hello!";
		}

		return new WebhookResponse(response, "webhook Text");
	}
}
