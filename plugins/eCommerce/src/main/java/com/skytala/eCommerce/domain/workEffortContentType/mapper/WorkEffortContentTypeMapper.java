package com.skytala.eCommerce.domain.workEffortContentType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workEffortContentType.model.WorkEffortContentType;

public class WorkEffortContentTypeMapper  {


	public static Map<String, Object> map(WorkEffortContentType workeffortcontenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortcontenttype.getWorkEffortContentTypeId() != null ){
			returnVal.put("workEffortContentTypeId",workeffortcontenttype.getWorkEffortContentTypeId());
}

		if(workeffortcontenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",workeffortcontenttype.getParentTypeId());
}

		if(workeffortcontenttype.getDescription() != null ){
			returnVal.put("description",workeffortcontenttype.getDescription());
}

		return returnVal;
}


	public static WorkEffortContentType map(Map<String, Object> fields) {

		WorkEffortContentType returnVal = new WorkEffortContentType();

		if(fields.get("workEffortContentTypeId") != null) {
			returnVal.setWorkEffortContentTypeId((String) fields.get("workEffortContentTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WorkEffortContentType mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortContentType returnVal = new WorkEffortContentType();

		if(fields.get("workEffortContentTypeId") != null) {
			returnVal.setWorkEffortContentTypeId((String) fields.get("workEffortContentTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static WorkEffortContentType map(GenericValue val) {

WorkEffortContentType returnVal = new WorkEffortContentType();
		returnVal.setWorkEffortContentTypeId(val.getString("workEffortContentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static WorkEffortContentType map(HttpServletRequest request) throws Exception {

		WorkEffortContentType returnVal = new WorkEffortContentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortContentTypeId")) {
returnVal.setWorkEffortContentTypeId(request.getParameter("workEffortContentTypeId"));
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
