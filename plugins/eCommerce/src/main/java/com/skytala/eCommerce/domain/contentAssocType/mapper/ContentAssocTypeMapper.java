package com.skytala.eCommerce.domain.contentAssocType.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.contentAssocType.model.ContentAssocType;

public class ContentAssocTypeMapper  {


	public static Map<String, Object> map(ContentAssocType contentassoctype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentassoctype.getContentAssocTypeId() != null ){
			returnVal.put("contentAssocTypeId",contentassoctype.getContentAssocTypeId());
}

		if(contentassoctype.getDescription() != null ){
			returnVal.put("description",contentassoctype.getDescription());
}

		return returnVal;
}


	public static ContentAssocType map(Map<String, Object> fields) {

		ContentAssocType returnVal = new ContentAssocType();

		if(fields.get("contentAssocTypeId") != null) {
			returnVal.setContentAssocTypeId((String) fields.get("contentAssocTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContentAssocType mapstrstr(Map<String, String> fields) throws Exception {

		ContentAssocType returnVal = new ContentAssocType();

		if(fields.get("contentAssocTypeId") != null) {
			returnVal.setContentAssocTypeId((String) fields.get("contentAssocTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContentAssocType map(GenericValue val) {

ContentAssocType returnVal = new ContentAssocType();
		returnVal.setContentAssocTypeId(val.getString("contentAssocTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ContentAssocType map(HttpServletRequest request) throws Exception {

		ContentAssocType returnVal = new ContentAssocType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentAssocTypeId")) {
returnVal.setContentAssocTypeId(request.getParameter("contentAssocTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
