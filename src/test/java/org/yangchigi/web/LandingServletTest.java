//package org.yangchigi.web;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//
//import quicktime.app.spaces.Protocol;
//
//
//@RunWith(JUnit4.class)
//public class LandingServletTest {
//	@Test
//	public void thisAlwaysPasses() {
//		String url ="http://localhost:8080/seize";
//	    Client client = new Client();
//	    ChallengeResponse challengeResponse = new ChallengeResponse(ChallengeScheme.HTTP_BASIC,"user", "f399b0a660f684b2c5a6b4c054f22d89");
//	    Request request = new Request(Method.GET, url);
//	    request.setChallengeResponse(challengeResponse);
//	    Response response = client.handle(request);
//	    System.out.println("request"+response.getStatus().getCode());
//	    System.out.println("request test::"+response.getEntityAsText());
//	}
//}
