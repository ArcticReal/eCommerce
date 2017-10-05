package com.skytala.eCommerce.domain.returnType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.returnType.model.ReturnType;

public class ReturnTypeMapper  {


	public static Map<String, Object> map(ReturnType returntype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returntype.getReturnTypeId() != null ){
			returnVal.put("returnTypeId",returntype.getReturnTypeId());
}

		if(returntype.getDescription() != null ){
			returnVal.put("description",returntype.getDescription());
}

		if(returntype.getSequenceId() != null ){
			returnVal.put("sequenceId",returntype.getSequenceId());
}

		return returnVal;
}


	public static ReturnType map(Map<String, Object> fields) {

		ReturnType returnVal = new ReturnType();

		if(fields.get("returnTypeId") != null) {
			returnVal.setReturnTypeId((String) fields.get("returnTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("sequenceId") != null) {
			returnVal.setSequenceId((String) fields.get("sequenceId"));
}


		return returnVal;
 } 
	public static ReturnType mapstrstr(Map<String, String> fields) throws Exception {

		ReturnType returnVal = new ReturnType();

		if(fields.get("returnTypeId") != null) {
			returnVal.setReturnTypeId((String) fields.get("returnTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("sequenceId") != null) {
			returnVal.setSequenceId((String) fields.get("sequenceId"));
}


		return returnVal;
 } 
	public static ReturnType map(GenericValue val) {

ReturnType returnVal = new ReturnType();
		returnVal.setReturnTypeId(val.getString("returnTypeId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setSequenceId(val.getString("sequenceId"));


return returnVal;

}

public static ReturnType map(HttpServletRequest request) throws Exception {

		ReturnType returnVal = new ReturnType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnTypeId")) {
returnVal.setReturnTypeId(request.getParameter("returnTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("sequenceId"))  {
returnVal.setSequenceId(request.getParameter("sequenceId"));
}
return returnVal;

}
}
