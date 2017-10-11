package com.skytala.eCommerce.domain.party.relations.commContentAssocType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.model.CommContentAssocType;

public class CommContentAssocTypeMapper  {


	public static Map<String, Object> map(CommContentAssocType commcontentassoctype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(commcontentassoctype.getCommContentAssocTypeId() != null ){
			returnVal.put("commContentAssocTypeId",commcontentassoctype.getCommContentAssocTypeId());
}

		if(commcontentassoctype.getDescription() != null ){
			returnVal.put("description",commcontentassoctype.getDescription());
}

		return returnVal;
}


	public static CommContentAssocType map(Map<String, Object> fields) {

		CommContentAssocType returnVal = new CommContentAssocType();

		if(fields.get("commContentAssocTypeId") != null) {
			returnVal.setCommContentAssocTypeId((String) fields.get("commContentAssocTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CommContentAssocType mapstrstr(Map<String, String> fields) throws Exception {

		CommContentAssocType returnVal = new CommContentAssocType();

		if(fields.get("commContentAssocTypeId") != null) {
			returnVal.setCommContentAssocTypeId((String) fields.get("commContentAssocTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static CommContentAssocType map(GenericValue val) {

CommContentAssocType returnVal = new CommContentAssocType();
		returnVal.setCommContentAssocTypeId(val.getString("commContentAssocTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static CommContentAssocType map(HttpServletRequest request) throws Exception {

		CommContentAssocType returnVal = new CommContentAssocType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("commContentAssocTypeId")) {
returnVal.setCommContentAssocTypeId(request.getParameter("commContentAssocTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
