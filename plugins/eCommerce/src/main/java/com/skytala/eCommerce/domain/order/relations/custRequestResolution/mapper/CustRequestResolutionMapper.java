package com.skytala.eCommerce.domain.order.relations.custRequestResolution.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestResolution.model.CustRequestResolution;

public class CustRequestResolutionMapper  {


	public static Map<String, Object> map(CustRequestResolution custrequestresolution) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequestresolution.getCustRequestResolutionId() != null ){
			returnVal.put("custRequestResolutionId",custrequestresolution.getCustRequestResolutionId());
}

		if(custrequestresolution.getCustRequestTypeId() != null ){
			returnVal.put("custRequestTypeId",custrequestresolution.getCustRequestTypeId());
}

		if(custrequestresolution.getDescription() != null ){
			returnVal.put("description",custrequestresolution.getDescription());
}

		return returnVal;
}


	public static CustRequestResolution map(Map<String, Object> fields) {

		CustRequestResolution returnVal = new CustRequestResolution();

		if(fields.get("custRequestResolutionId") != null) {
			returnVal.setCustRequestResolutionId((String) fields.get("custRequestResolutionId"));
}

		if(fields.get("custRequestTypeId") != null) {
			returnVal.setCustRequestTypeId((String) fields.get("custRequestTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CustRequestResolution mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestResolution returnVal = new CustRequestResolution();

		if(fields.get("custRequestResolutionId") != null) {
			returnVal.setCustRequestResolutionId((String) fields.get("custRequestResolutionId"));
}

		if(fields.get("custRequestTypeId") != null) {
			returnVal.setCustRequestTypeId((String) fields.get("custRequestTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CustRequestResolution map(GenericValue val) {

CustRequestResolution returnVal = new CustRequestResolution();
		returnVal.setCustRequestResolutionId(val.getString("custRequestResolutionId"));
		returnVal.setCustRequestTypeId(val.getString("custRequestTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static CustRequestResolution map(HttpServletRequest request) throws Exception {

		CustRequestResolution returnVal = new CustRequestResolution();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestResolutionId")) {
returnVal.setCustRequestResolutionId(request.getParameter("custRequestResolutionId"));
}

		if(paramMap.containsKey("custRequestTypeId"))  {
returnVal.setCustRequestTypeId(request.getParameter("custRequestTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
