package com.skytala.eCommerce.domain.order.relations.custRequest.mapper.requirement;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.requirement.RequirementCustRequest;

public class RequirementCustRequestMapper  {


	public static Map<String, Object> map(RequirementCustRequest requirementcustrequest) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(requirementcustrequest.getCustRequestId() != null ){
			returnVal.put("custRequestId",requirementcustrequest.getCustRequestId());
}

		if(requirementcustrequest.getCustRequestItemSeqId() != null ){
			returnVal.put("custRequestItemSeqId",requirementcustrequest.getCustRequestItemSeqId());
}

		if(requirementcustrequest.getRequirementId() != null ){
			returnVal.put("requirementId",requirementcustrequest.getRequirementId());
}

		return returnVal;
}


	public static RequirementCustRequest map(Map<String, Object> fields) {

		RequirementCustRequest returnVal = new RequirementCustRequest();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestItemSeqId") != null) {
			returnVal.setCustRequestItemSeqId((String) fields.get("custRequestItemSeqId"));
}

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}


		return returnVal;
 } 
	public static RequirementCustRequest mapstrstr(Map<String, String> fields) throws Exception {

		RequirementCustRequest returnVal = new RequirementCustRequest();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestItemSeqId") != null) {
			returnVal.setCustRequestItemSeqId((String) fields.get("custRequestItemSeqId"));
}

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}


		return returnVal;
 } 
	public static RequirementCustRequest map(GenericValue val) {

RequirementCustRequest returnVal = new RequirementCustRequest();
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setCustRequestItemSeqId(val.getString("custRequestItemSeqId"));
		returnVal.setRequirementId(val.getString("requirementId"));


return returnVal;

}

public static RequirementCustRequest map(HttpServletRequest request) throws Exception {

		RequirementCustRequest returnVal = new RequirementCustRequest();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestId")) {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
}

		if(paramMap.containsKey("custRequestItemSeqId"))  {
returnVal.setCustRequestItemSeqId(request.getParameter("custRequestItemSeqId"));
}
		if(paramMap.containsKey("requirementId"))  {
returnVal.setRequirementId(request.getParameter("requirementId"));
}
return returnVal;

}
}
