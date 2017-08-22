package com.companyname.skytalaPlugin.services;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InjectionServlet extends org.apache.ofbiz.webapp.control.ControlServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * @see javax.servlet.Servlet#init(javax.servlet.ServletConfig)
     */
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

    /**
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }

    /**
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    /**
     * @see javax.servlet.Servlet#destroy()
     */
    @Override
    public void destroy() {
        super.destroy();
    }



	
	
}
