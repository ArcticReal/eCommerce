package com.skytala.eCommerce.domain.content.relations.metaDataPredicate.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.model.MetaDataPredicate;

public class MetaDataPredicateMapper  {


	public static Map<String, Object> map(MetaDataPredicate metadatapredicate) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(metadatapredicate.getMetaDataPredicateId() != null ){
			returnVal.put("metaDataPredicateId",metadatapredicate.getMetaDataPredicateId());
}

		if(metadatapredicate.getDescription() != null ){
			returnVal.put("description",metadatapredicate.getDescription());
}

		return returnVal;
}


	public static MetaDataPredicate map(Map<String, Object> fields) {

		MetaDataPredicate returnVal = new MetaDataPredicate();

		if(fields.get("metaDataPredicateId") != null) {
			returnVal.setMetaDataPredicateId((String) fields.get("metaDataPredicateId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static MetaDataPredicate mapstrstr(Map<String, String> fields) throws Exception {

		MetaDataPredicate returnVal = new MetaDataPredicate();

		if(fields.get("metaDataPredicateId") != null) {
			returnVal.setMetaDataPredicateId((String) fields.get("metaDataPredicateId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static MetaDataPredicate map(GenericValue val) {

MetaDataPredicate returnVal = new MetaDataPredicate();
		returnVal.setMetaDataPredicateId(val.getString("metaDataPredicateId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static MetaDataPredicate map(HttpServletRequest request) throws Exception {

		MetaDataPredicate returnVal = new MetaDataPredicate();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("metaDataPredicateId")) {
returnVal.setMetaDataPredicateId(request.getParameter("metaDataPredicateId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
