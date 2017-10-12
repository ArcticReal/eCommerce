package com.skytala.eCommerce.domain.content.relations.contentAssocPredicate.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentAssocPredicate.model.ContentAssocPredicate;

public class ContentAssocPredicateMapper  {


	public static Map<String, Object> map(ContentAssocPredicate contentassocpredicate) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(contentassocpredicate.getContentAssocPredicateId() != null ){
			returnVal.put("contentAssocPredicateId",contentassocpredicate.getContentAssocPredicateId());
}

		if(contentassocpredicate.getDescription() != null ){
			returnVal.put("description",contentassocpredicate.getDescription());
}

		return returnVal;
}


	public static ContentAssocPredicate map(Map<String, Object> fields) {

		ContentAssocPredicate returnVal = new ContentAssocPredicate();

		if(fields.get("contentAssocPredicateId") != null) {
			returnVal.setContentAssocPredicateId((String) fields.get("contentAssocPredicateId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContentAssocPredicate mapstrstr(Map<String, String> fields) throws Exception {

		ContentAssocPredicate returnVal = new ContentAssocPredicate();

		if(fields.get("contentAssocPredicateId") != null) {
			returnVal.setContentAssocPredicateId((String) fields.get("contentAssocPredicateId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ContentAssocPredicate map(GenericValue val) {

ContentAssocPredicate returnVal = new ContentAssocPredicate();
		returnVal.setContentAssocPredicateId(val.getString("contentAssocPredicateId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ContentAssocPredicate map(HttpServletRequest request) throws Exception {

		ContentAssocPredicate returnVal = new ContentAssocPredicate();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("contentAssocPredicateId")) {
returnVal.setContentAssocPredicateId(request.getParameter("contentAssocPredicateId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
