package com.skytala.eCommerce.domain.order.relations.returnHeader.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnHeader.model.type.ReturnHeaderType;

public class ReturnHeaderTypeMapper  {


	public static Map<String, Object> map(ReturnHeaderType returnheadertype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returnheadertype.getReturnHeaderTypeId() != null ){
			returnVal.put("returnHeaderTypeId",returnheadertype.getReturnHeaderTypeId());
}

		if(returnheadertype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",returnheadertype.getParentTypeId());
}

		if(returnheadertype.getDescription() != null ){
			returnVal.put("description",returnheadertype.getDescription());
}

		return returnVal;
}


	public static ReturnHeaderType map(Map<String, Object> fields) {

		ReturnHeaderType returnVal = new ReturnHeaderType();

		if(fields.get("returnHeaderTypeId") != null) {
			returnVal.setReturnHeaderTypeId((String) fields.get("returnHeaderTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ReturnHeaderType mapstrstr(Map<String, String> fields) throws Exception {

		ReturnHeaderType returnVal = new ReturnHeaderType();

		if(fields.get("returnHeaderTypeId") != null) {
			returnVal.setReturnHeaderTypeId((String) fields.get("returnHeaderTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ReturnHeaderType map(GenericValue val) {

ReturnHeaderType returnVal = new ReturnHeaderType();
		returnVal.setReturnHeaderTypeId(val.getString("returnHeaderTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ReturnHeaderType map(HttpServletRequest request) throws Exception {

		ReturnHeaderType returnVal = new ReturnHeaderType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnHeaderTypeId")) {
returnVal.setReturnHeaderTypeId(request.getParameter("returnHeaderTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
