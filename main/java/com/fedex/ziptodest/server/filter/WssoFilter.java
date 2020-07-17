
package com.fedex.ziptodest.server.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fedex.ziptodest.model.CurrentUser;
import com.fedex.ziptodest.model.Department;
import com.fedex.ziptodest.utils.server.ZDConstants;

@WebFilter(urlPatterns = "/*")
public class WssoFilter extends OncePerRequestFilter {
	private static final Logger log = LoggerFactory.getLogger(WssoFilter.class);

	@Autowired
	private CurrentUser currentuser;

	@Autowired
	private Department department;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.info("Executing WSSO Authentication Filter");

		String url = request instanceof HttpServletRequest ? request.getRequestURL().toString() : "N/A";
		log.debug("Request Url: {}", url);

		String userid;
		String givenName;
		String mail;
		String deptNum;
		String firstName;
		String lastName;
		String role = "";

		if (url.contains("aliveTest") || url.contains("localhost")
				|| url.contains("ziptodest-ui-development.app.wtcdev1.paas.fedex.com")
				|| url.contains("ziptodest-ui-release.app.wtcdev1.paas.fedex.com")
				|| url.contains("ziptodest-ui-release.app.edcdev1.paas.fedex.com")
				|| url.contains("ziptodest-ui-development.app.edcdev1.paas.fedex.com") || url.contains("getProperty")
				|| url.contains("getPropertyNames") || url.contains("info") || url.contains("health")
				|| url.contains("refresh") || url.contains("getBuildInfo") || url.contains("refreshCache")
				|| url.contains("getLastCacheRefreshTime")) {
			log.debug("Bypassing Filter for local/PCF environment");
			// set dummy roles for local
			userid = "555555";
			givenName = "Nick Fedex";
			mail = "nick.fedex@fedex.com";
			deptNum = "300N14000A";
			role = ZDConstants.SORTATION;

		} else {

			log.info("Retrieving User information from Header");
			userid = request.getHeader("OBLIX_UID");
			firstName = request.getHeader("OBLIX_FIRSTNAME");
			lastName = request.getHeader("OBLIX_LASTNAME");
			givenName = firstName + " " + lastName;
			mail = request.getHeader("OBLIX_MAIL");
			deptNum = request.getHeader("OBLIX_DEPARTMENTNUMBER");

			log.info("Role values from properties file");

			if (deptNum != null && department.getSortationRole().contains(deptNum)) {
				role = ZDConstants.SORTATION;
			} else if (deptNum != null && department.getVisionManagementRole().contains(deptNum)) {
				role = ZDConstants.VISION_MANAGEMENT_ROLE;
			} else {
				role = ZDConstants.DEFAULT_USER_ROLE;
			}
		}

		log.info("Retrieving User information from Header");
		log.info("User Id: {} ", userid);
		log.info("User Name: {}", givenName);
		log.info("Email: {}", mail);
		log.info("Department No: {}", deptNum);
		log.info("Role : {}", role);

		if (userid != null && userid.trim().length() > 0) {

			currentuser.setUserId(userid);
			if (givenName != null && givenName.trim().length() > 0) {
				currentuser.setGivenName(givenName);
			} else {
				currentuser.setGivenName("UNKNOWN");
			}
			if (deptNum != null && deptNum.trim().length() > 0) {
				currentuser.setDeptNum(deptNum);
			}

			if (mail != null && mail.trim().length() > 0) {
				currentuser.setMail(mail);
			}
			currentuser.setRole(role);
		}

		log.info("Exiting WSSO Authentication Filter");
		if (role.equals(ZDConstants.SORTATION) || role.equals(ZDConstants.VISION_MANAGEMENT_ROLE)) {
			log.info("Enter into valid role : {}", role);
			filterChain.doFilter(request, response);
		} else {
			log.info("Redirected to error page");
			request.getRequestDispatcher("error.html").forward(request, response);
		}
	}
}
