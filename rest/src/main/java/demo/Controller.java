package demo;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String handle() throws Exception {

		return "ok";

	}
	
	@RequestMapping(value = "/",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> helloUser(Principal principal) {
		Map<String, String> result = new HashMap<>();
		result.put("usernamlllllllllle", principal.getName());
		return result;
	}
	@RequestMapping(value = "/2", produces = MediaType.APPLICATION_JSON_VALUE)
	String helloUser2() {
		return "hello";
	}
//
//	@RequestMapping("/logout")
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	void logout(HttpSession session) {
//		session.invalidate();
//	}





}