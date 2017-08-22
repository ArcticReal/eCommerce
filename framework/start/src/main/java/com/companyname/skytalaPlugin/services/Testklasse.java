package com.companyname.skytalaPlugin.services;
import java.util.*;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

public class Testklasse {
	
	public static final String module = Testklasse.class.getName();
	
	
	public static Map<String, Object> testit(DispatchContext dctx, Map<String, Object> context){
		
		Map<String,Object> result = ServiceUtil.returnSuccess();
		Delegator delegator=dctx.getDelegator();
		
		
		try {
			List<GenericValue> buf = delegator.findAll("SkytalaPlugin", false);
			
			for(int i = 0; i<buf.size();i++){
				
				
				
				if(!result.containsKey(i)){
					//result.put("Id", buf.get(i).getString("skytalaPluginId"));	
					System.out.println(""+buf.get(i).getString("skytalaPluginId"));
				}
			}
			 
		} catch (GenericEntityException e) {
			// TODO Auto-generated catch block
			Debug.logError(e, module);
			e.printStackTrace();
		}
		
		return result;
	}
}
