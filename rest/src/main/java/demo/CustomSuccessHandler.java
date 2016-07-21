package demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Authentication success handler for REST services
 *
 */
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		if ("application/json".equals(request.getHeader("Content-Type"))) {
			/*
			 * USED if you want to AVOID redirect to LoginSuccessful.htm in JSON
			 * authentication
			 */
			response.getWriter().print("{\"responseCode\":\"SUCCESS\"}");
			response.getWriter().flush();
		} else {
			super.onAuthenticationSuccess(request, response, auth);
		}
	}
}

//	@Override
//	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//										Authentication authentication) throws IOException, ServletException {
//		response.setStatus(HttpServletResponse.SC_OK);
//
//		NuvolaUserDetails userDetails = (NuvolaUserDetails) authentication.getPrincipal();
//		User user = userDetails.getUser();
//		userDetails.setUser(user);
//
//		LOGGER.info(userDetails.getUsername() + " got is connected ");
//
//		PrintWriter writer = response.getWriter();
//		mapper.writeValue(writer, user);
//		writer.flush();
//	}
