package com.skytala.eCommerce.domain.order.relations.custRequestCategory.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestCategory.model.CustRequestCategory;

public class CustRequestCategoryMapper  {


	public static Map<String, Object> map(CustRequestCategory custrequestcategory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequestcategory.getCustRequestCategoryId() != null ){
			returnVal.put("custRequestCategoryId",custrequestcategory.getCustRequestCategoryId());
}

		if(custrequestcategory.getCustRequestTypeId() != null ){
			returnVal.put("custRequestTypeId",custrequestcategory.getCustRequestTypeId());
}

		if(custrequestcategory.getDescription() != null ){
			returnVal.put("description",custrequestcategory.getDescription());
}

		return returnVal;
}


	public static CustRequestCategory map(Map<String, Object> fields) {

		CustRequestCategory returnVal = new CustRequestCategory();

		if(fields.get("custRequestCategoryId") != null) {
			returnVal.setCustRequestCategoryId((String) fields.get("custRequestCategoryId"));
}

		if(fields.get("custRequestTypeId") != null) {
			returnVal.setCustRequestTypeId((String) fields.get("custRequestTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CustRequestCategory mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestCategory returnVal = new CustRequestCategory();

		if(fields.get("custRequestCategoryId") != null) {
			returnVal.setCustRequestCategoryId((String) fields.get("custRequestCategoryId"));
}

		if(fields.get("custRequestTypeId") != null) {
			returnVal.setCustRequestTypeId((String) fields.get("custRequestTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CustRequestCategory map(GenericValue val) {

CustRequestCategory returnVal = new CustRequestCategory();
		returnVal.setCustRequestCategoryId(val.getString("custRequestCategoryId"));
		returnVal.setCustRequestTypeId(val.getString("custRequestTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static CustRequestCategory map(HttpServletRequest request) throws Exception {

		CustRequestCategory returnVal = new CustRequestCategory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestCategoryId")) {
returnVal.setCustRequestCategoryId(request.getParameter("custRequestCategoryId"));
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
