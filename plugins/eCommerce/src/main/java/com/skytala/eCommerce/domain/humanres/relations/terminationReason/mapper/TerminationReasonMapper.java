package com.skytala.eCommerce.domain.humanres.relations.terminationReason.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.terminationReason.model.TerminationReason;

public class TerminationReasonMapper  {


	public static Map<String, Object> map(TerminationReason terminationreason) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(terminationreason.getTerminationReasonId() != null ){
			returnVal.put("terminationReasonId",terminationreason.getTerminationReasonId());
}

		if(terminationreason.getDescription() != null ){
			returnVal.put("description",terminationreason.getDescription());
}

		return returnVal;
}


	public static TerminationReason map(Map<String, Object> fields) {

		TerminationReason returnVal = new TerminationReason();

		if(fields.get("terminationReasonId") != null) {
			returnVal.setTerminationReasonId((String) fields.get("terminationReasonId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TerminationReason mapstrstr(Map<String, String> fields) throws Exception {

		TerminationReason returnVal = new TerminationReason();

		if(fields.get("terminationReasonId") != null) {
			returnVal.setTerminationReasonId((String) fields.get("terminationReasonId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TerminationReason map(GenericValue val) {

TerminationReason returnVal = new TerminationReason();
		returnVal.setTerminationReasonId(val.getString("terminationReasonId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static TerminationReason map(HttpServletRequest request) throws Exception {

		TerminationReason returnVal = new TerminationReason();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("terminationReasonId")) {
returnVal.setTerminationReasonId(request.getParameter("terminationReasonId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
