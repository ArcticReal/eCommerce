package com.companyname.skytalaPlugin.services;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ofbiz.webapp.control.ControlServlet;
import org.apache.ofbiz.webapp.control.RequestHandler;

public class TestServlet extends ControlServlet {
	
	 @Override
	    public void init(ServletConfig config) throws ServletException {
	        super.init(config);
	        System.out.println("XXXXXXXXXXXXXXX");
	        System.out.println("XXXXXXXXXXXXXXX");
	        System.out.println("XXXXXXXXXXXXXXX");
	        System.out.println("XXXXXXXXXXXXXXX");
	        System.out.println("XXXXXXXXXXXXXXX");
	        System.out.println("XXXXXXXXXXXXXXX");
	        System.out.println("XXXXXXXXXXXXXXX");
	        System.out.println("XXXXXXXXXXXXXXX");
	        System.out.println("XXXXXXXXXXXXXXX");
	        System.out.println("XXXXXXXXXXXXXXX");
	        System.out.println("XXXXXXXXXXXXXXX");
	        System.out.println("XXXXXXXXXXXXXXX");
	        System.out.println("XXXXXXXXXXXXXXX");
	    }

	    @Override
	    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	super.doPost(request, response);
	    }

	    @Override
	    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	super.doGet(request, response);
	    }


	    protected RequestHandler getRequestHandler() {
	    	return super.getRequestHandler();
	    }

	    protected void logRequestInfo(HttpServletRequest request) {
	    	super.logRequestInfo(request);
	    }
	
}
