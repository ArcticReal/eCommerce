package com.skytala.eCommerce.domain.party.relations.needType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.needType.model.NeedType;

public class NeedTypeMapper  {


	public static Map<String, Object> map(NeedType needtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(needtype.getNeedTypeId() != null ){
			returnVal.put("needTypeId",needtype.getNeedTypeId());
}

		if(needtype.getDescription() != null ){
			returnVal.put("description",needtype.getDescription());
}

		return returnVal;
}


	public static NeedType map(Map<String, Object> fields) {

		NeedType returnVal = new NeedType();

		if(fields.get("needTypeId") != null) {
			returnVal.setNeedTypeId((String) fields.get("needTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static NeedType mapstrstr(Map<String, String> fields) throws Exception {

		NeedType returnVal = new NeedType();

		if(fields.get("needTypeId") != null) {
			returnVal.setNeedTypeId((String) fields.get("needTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static NeedType map(GenericValue val) {

NeedType returnVal = new NeedType();
		returnVal.setNeedTypeId(val.getString("needTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static NeedType map(HttpServletRequest request) throws Exception {

		NeedType returnVal = new NeedType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("needTypeId")) {
returnVal.setNeedTypeId(request.getParameter("needTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
