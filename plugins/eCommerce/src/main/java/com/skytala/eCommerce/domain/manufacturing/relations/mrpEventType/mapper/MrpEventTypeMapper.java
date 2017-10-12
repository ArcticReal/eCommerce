package com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.mrpEventType.model.MrpEventType;

public class MrpEventTypeMapper  {


	public static Map<String, Object> map(MrpEventType mrpeventtype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(mrpeventtype.getMrpEventTypeId() != null ){
			returnVal.put("mrpEventTypeId",mrpeventtype.getMrpEventTypeId());
}

		if(mrpeventtype.getDescription() != null ){
			returnVal.put("description",mrpeventtype.getDescription());
}

		return returnVal;
}


	public static MrpEventType map(Map<String, Object> fields) {

		MrpEventType returnVal = new MrpEventType();

		if(fields.get("mrpEventTypeId") != null) {
			returnVal.setMrpEventTypeId((String) fields.get("mrpEventTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static MrpEventType mapstrstr(Map<String, String> fields) throws Exception {

		MrpEventType returnVal = new MrpEventType();

		if(fields.get("mrpEventTypeId") != null) {
			returnVal.setMrpEventTypeId((String) fields.get("mrpEventTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static MrpEventType map(GenericValue val) {

MrpEventType returnVal = new MrpEventType();
		returnVal.setMrpEventTypeId(val.getString("mrpEventTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static MrpEventType map(HttpServletRequest request) throws Exception {

		MrpEventType returnVal = new MrpEventType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("mrpEventTypeId")) {
returnVal.setMrpEventTypeId(request.getParameter("mrpEventTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
