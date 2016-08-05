package demo;

import com.drf.betsservice.model.LoginResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Controller {

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String handle() throws Exception {

		return "ok";

	}


	@RequestMapping(value = "/ttt",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> ttt(Principal principal) {
		Map<String, String> result = new HashMap<>();
		result.put("usernamlllllllllle", principal.getName());
		return result;
	}
	
	@RequestMapping(value = "/ttt",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, String> helloUser(Authentication principal) {
		Map<String, String> result = new HashMap<>();
		result.put("usernamlllllllllle", principal.getName());
		return result;
	}

	@RequestMapping(value = "/principal",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	LoginResponse principal(Authentication authentication ) {

		LoginResponse loginResponse=(LoginResponse)authentication.getDetails();

		return loginResponse;
	}


	@RequestMapping(value = "/2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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