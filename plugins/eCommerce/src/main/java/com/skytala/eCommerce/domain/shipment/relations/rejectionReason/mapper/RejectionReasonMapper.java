package com.skytala.eCommerce.domain.shipment.relations.rejectionReason.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.rejectionReason.model.RejectionReason;

public class RejectionReasonMapper  {


	public static Map<String, Object> map(RejectionReason rejectionreason) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(rejectionreason.getRejectionId() != null ){
			returnVal.put("rejectionId",rejectionreason.getRejectionId());
}

		if(rejectionreason.getDescription() != null ){
			returnVal.put("description",rejectionreason.getDescription());
}

		return returnVal;
}


	public static RejectionReason map(Map<String, Object> fields) {

		RejectionReason returnVal = new RejectionReason();

		if(fields.get("rejectionId") != null) {
			returnVal.setRejectionId((String) fields.get("rejectionId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static RejectionReason mapstrstr(Map<String, String> fields) throws Exception {

		RejectionReason returnVal = new RejectionReason();

		if(fields.get("rejectionId") != null) {
			returnVal.setRejectionId((String) fields.get("rejectionId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static RejectionReason map(GenericValue val) {

RejectionReason returnVal = new RejectionReason();
		returnVal.setRejectionId(val.getString("rejectionId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static RejectionReason map(HttpServletRequest request) throws Exception {

		RejectionReason returnVal = new RejectionReason();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("rejectionId")) {
returnVal.setRejectionId(request.getParameter("rejectionId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
