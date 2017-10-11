package com.skytala.eCommerce.domain.order.relations.workReqFulfType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.workReqFulfType.model.WorkReqFulfType;

public class WorkReqFulfTypeMapper  {


	public static Map<String, Object> map(WorkReqFulfType workreqfulftype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workreqfulftype.getWorkReqFulfTypeId() != null ){
			returnVal.put("workReqFulfTypeId",workreqfulftype.getWorkReqFulfTypeId());
}

		if(workreqfulftype.getDescription() != null ){
			returnVal.put("description",workreqfulftype.getDescription());
}

		return returnVal;
}


	public static WorkReqFulfType map(Map<String, Object> fields) {

		WorkReqFulfType returnVal = new WorkReqFulfType();

		if(fields.get("workReqFulfTypeId") != null) {
			returnVal.setWorkReqFulfTypeId((String) fields.get("workReqFulfTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WorkReqFulfType mapstrstr(Map<String, String> fields) throws Exception {

		WorkReqFulfType returnVal = new WorkReqFulfType();

		if(fields.get("workReqFulfTypeId") != null) {
			returnVal.setWorkReqFulfTypeId((String) fields.get("workReqFulfTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WorkReqFulfType map(GenericValue val) {

WorkReqFulfType returnVal = new WorkReqFulfType();
		returnVal.setWorkReqFulfTypeId(val.getString("workReqFulfTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static WorkReqFulfType map(HttpServletRequest request) throws Exception {

		WorkReqFulfType returnVal = new WorkReqFulfType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workReqFulfTypeId")) {
returnVal.setWorkReqFulfTypeId(request.getParameter("workReqFulfTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
