package com.skytala.eCommerce.domain.order.relations.returnItemTypeMap.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnItemTypeMap.model.ReturnItemTypeMap;

public class ReturnItemTypeMapMapper  {


	public static Map<String, Object> map(ReturnItemTypeMap returnitemtypemap) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returnitemtypemap.getReturnItemMapKey() != null ){
			returnVal.put("returnItemMapKey",returnitemtypemap.getReturnItemMapKey());
}

		if(returnitemtypemap.getReturnHeaderTypeId() != null ){
			returnVal.put("returnHeaderTypeId",returnitemtypemap.getReturnHeaderTypeId());
}

		if(returnitemtypemap.getReturnItemTypeId() != null ){
			returnVal.put("returnItemTypeId",returnitemtypemap.getReturnItemTypeId());
}

		return returnVal;
}


	public static ReturnItemTypeMap map(Map<String, Object> fields) {

		ReturnItemTypeMap returnVal = new ReturnItemTypeMap();

		if(fields.get("returnItemMapKey") != null) {
			returnVal.setReturnItemMapKey((String) fields.get("returnItemMapKey"));
}

		if(fields.get("returnHeaderTypeId") != null) {
			returnVal.setReturnHeaderTypeId((String) fields.get("returnHeaderTypeId"));
}

		if(fields.get("returnItemTypeId") != null) {
			returnVal.setReturnItemTypeId((String) fields.get("returnItemTypeId"));
}


		return returnVal;
 } 
	public static ReturnItemTypeMap mapstrstr(Map<String, String> fields) throws Exception {

		ReturnItemTypeMap returnVal = new ReturnItemTypeMap();

		if(fields.get("returnItemMapKey") != null) {
			returnVal.setReturnItemMapKey((String) fields.get("returnItemMapKey"));
}

		if(fields.get("returnHeaderTypeId") != null) {
			returnVal.setReturnHeaderTypeId((String) fields.get("returnHeaderTypeId"));
}

		if(fields.get("returnItemTypeId") != null) {
			returnVal.setReturnItemTypeId((String) fields.get("returnItemTypeId"));
}


		return returnVal;
 } 
	public static ReturnItemTypeMap map(GenericValue val) {

ReturnItemTypeMap returnVal = new ReturnItemTypeMap();
		returnVal.setReturnItemMapKey(val.getString("returnItemMapKey"));
		returnVal.setReturnHeaderTypeId(val.getString("returnHeaderTypeId"));
		returnVal.setReturnItemTypeId(val.getString("returnItemTypeId"));


return returnVal;

}

public static ReturnItemTypeMap map(HttpServletRequest request) throws Exception {

		ReturnItemTypeMap returnVal = new ReturnItemTypeMap();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnItemMapKey")) {
returnVal.setReturnItemMapKey(request.getParameter("returnItemMapKey"));
}

		if(paramMap.containsKey("returnHeaderTypeId"))  {
returnVal.setReturnHeaderTypeId(request.getParameter("returnHeaderTypeId"));
}
		if(paramMap.containsKey("returnItemTypeId"))  {
returnVal.setReturnItemTypeId(request.getParameter("returnItemTypeId"));
}
return returnVal;

}
}
