package com.companyname.skytalaPlugin.services;

import java.util.HashMap;
import java.util.Map;

import org.apache.ofbiz.service.DispatchContext;

public class Simple {
	
	public static Map<String , Object>  webtest(DispatchContext dctx, Map<String, Object> context){
		Map<String, Object> result;
		result = new HashMap<String, Object>();
		

		
		
	//	result=ServiceUtil.returnSuccess();
		String eing = (""+context.get("eingabe"));
		//String weiter = (""+context.get("weiteresattribut"));
		System.out.println(""+context.get("eingabe"));
		System.out.println(""+context.get("weiteresattribut"));
		
		result.put("Dies ist ", "ein Test");
		result.put("einga", ""+eing);
		
		
		return result;
	}

}
