package com.companyname.skytalaPlugin.services;
import java.util.Map;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

public class SkytalaPlugin {
	
	public static final String module = SkytalaPlugin.class.getName();
	
	 public static Map<String, Object> createskytalaplugin(DispatchContext dctx, Map<String, ? extends Object> context) {
		 Map<String, Object> result = ServiceUtil.returnSuccess();
		 
		 
		 Delegator delegator = dctx.getDelegator();
		 try {
			 	GenericValue SkytalaPlugin = delegator.makeValue("SkytalaPlugin");
			 		// Auto generating next sequence of ofbizDemoId primary key
			 		SkytalaPlugin.setNextSeqId();
			 		// Setting up all non primary key field values from context map
			 		SkytalaPlugin.setNonPKFields(context);
			 		// Creating record in database for SkytalaPlugin entity for prepared value
			 		SkytalaPlugin = delegator.create(SkytalaPlugin);
			 		result.put("skytalaPluginId", SkytalaPlugin.getString("skytalaPluginId"));
			 		
			 		Debug.log("==========This is my first Java Service implementation in Apache OFBiz. skytalaPlugin record created successfully with skytalaPluginId: "+SkytalaPlugin.getString("skytalaPluginId"));
		 } 
		catch (GenericEntityException e) {
		Debug.logError(e, module);
		return ServiceUtil.returnError("Error in creating record in SkytalaPlugin entity ........" +module);
	 }
	 
	 return result;
	 
	 }

}
