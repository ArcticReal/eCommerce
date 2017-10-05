package com.skytala.eCommerce.domain.contentOperation.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.contentOperation.model.ContentOperation;

public class ContentOperationMapper  {


	public static Map<String, Object> map(ContentOperation contentoperation) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentoperation.getContentOperationId() != null ){
			returnVal.put("contentOperationId",contentoperation.getContentOperationId());
}

		if(contentoperation.getDescription() != null ){
			returnVal.put("description",contentoperation.getDescription());
}

		return returnVal;
}


	public static ContentOperation map(Map<String, Object> fields) {

		ContentOperation returnVal = new ContentOperation();

		if(fields.get("contentOperationId") != null) {
			returnVal.setContentOperationId((String) fields.get("contentOperationId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContentOperation mapstrstr(Map<String, String> fields) throws Exception {

		ContentOperation returnVal = new ContentOperation();

		if(fields.get("contentOperationId") != null) {
			returnVal.setContentOperationId((String) fields.get("contentOperationId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContentOperation map(GenericValue val) {

ContentOperation returnVal = new ContentOperation();
		returnVal.setContentOperationId(val.getString("contentOperationId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ContentOperation map(HttpServletRequest request) throws Exception {

		ContentOperation returnVal = new ContentOperation();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentOperationId")) {
returnVal.setContentOperationId(request.getParameter("contentOperationId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
