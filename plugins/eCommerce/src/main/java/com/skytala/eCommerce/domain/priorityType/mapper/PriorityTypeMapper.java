package com.skytala.eCommerce.domain.priorityType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.priorityType.model.PriorityType;

public class PriorityTypeMapper  {


	public static Map<String, Object> map(PriorityType prioritytype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(prioritytype.getPriorityTypeId() != null ){
			returnVal.put("priorityTypeId",prioritytype.getPriorityTypeId());
}

		if(prioritytype.getDescription() != null ){
			returnVal.put("description",prioritytype.getDescription());
}

		return returnVal;
}


	public static PriorityType map(Map<String, Object> fields) {

		PriorityType returnVal = new PriorityType();

		if(fields.get("priorityTypeId") != null) {
			returnVal.setPriorityTypeId((String) fields.get("priorityTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PriorityType mapstrstr(Map<String, String> fields) throws Exception {

		PriorityType returnVal = new PriorityType();

		if(fields.get("priorityTypeId") != null) {
			returnVal.setPriorityTypeId((String) fields.get("priorityTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static PriorityType map(GenericValue val) {

PriorityType returnVal = new PriorityType();
		returnVal.setPriorityTypeId(val.getString("priorityTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static PriorityType map(HttpServletRequest request) throws Exception {

		PriorityType returnVal = new PriorityType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("priorityTypeId")) {
returnVal.setPriorityTypeId(request.getParameter("priorityTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
