package com.skytala.eCommerce.domain.order.relations.returnReason.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.returnReason.model.ReturnReason;

public class ReturnReasonMapper  {


	public static Map<String, Object> map(ReturnReason returnreason) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(returnreason.getReturnReasonId() != null ){
			returnVal.put("returnReasonId",returnreason.getReturnReasonId());
}

		if(returnreason.getDescription() != null ){
			returnVal.put("description",returnreason.getDescription());
}

		if(returnreason.getSequenceId() != null ){
			returnVal.put("sequenceId",returnreason.getSequenceId());
}

		return returnVal;
}


	public static ReturnReason map(Map<String, Object> fields) {

		ReturnReason returnVal = new ReturnReason();

		if(fields.get("returnReasonId") != null) {
			returnVal.setReturnReasonId((String) fields.get("returnReasonId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("sequenceId") != null) {
			returnVal.setSequenceId((String) fields.get("sequenceId"));
}


		return returnVal;
 } 
	public static ReturnReason mapstrstr(Map<String, String> fields) throws Exception {

		ReturnReason returnVal = new ReturnReason();

		if(fields.get("returnReasonId") != null) {
			returnVal.setReturnReasonId((String) fields.get("returnReasonId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("sequenceId") != null) {
			returnVal.setSequenceId((String) fields.get("sequenceId"));
}


		return returnVal;
 } 
	public static ReturnReason map(GenericValue val) {

ReturnReason returnVal = new ReturnReason();
		returnVal.setReturnReasonId(val.getString("returnReasonId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setSequenceId(val.getString("sequenceId"));


return returnVal;

}

public static ReturnReason map(HttpServletRequest request) throws Exception {

		ReturnReason returnVal = new ReturnReason();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("returnReasonId")) {
returnVal.setReturnReasonId(request.getParameter("returnReasonId"));
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
