package com.skytala.skytalaPlugin.services;

import java.util.Map;
import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.DispatchContext;
import org.apache.ofbiz.service.ServiceUtil;

public class SkytalaPluginServices {
	public static final String module = SkytalaPluginServices.class.getName();

	public static Map<String, Object> createOfbizDemo(DispatchContext dctx, Map<String, ? extends Object> context) {
		Map<String, Object> result = ServiceUtil.returnSuccess();
		Delegator delegator = dctx.getDelegator();
		try {
			GenericValue ofbizDemo = delegator.makeValue("OfbizDemo");
			// Auto generating next sequence of ofbizDemoId primary key
			ofbizDemo.setNextSeqId();
			// Setting up all non primary key field values from context map
			ofbizDemo.setNonPKFields(context);
			// Creating record in database for OfbizDemo entity for prepared value
			ofbizDemo = delegator.create(ofbizDemo);
			result.put("ofbizDemoId", ofbizDemo.getString("ofbizDemoId"));
			Debug.log(
					"==========This is my first Java Service implementation in Apache OFBiz. OfbizDemo record created successfully with ofbizDemoId: "
							+ ofbizDemo.getString("ofbizDemoId"));
		} catch (GenericEntityException e) {
			Debug.logError(e, module);
			return ServiceUtil.returnError("Error in creating record in OfbizDemo entity ........" + module);
		}
		return result;
	}
}