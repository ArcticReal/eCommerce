package com.skytala.eCommerce.domain.workeffort.relations.deliverable.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.type.DeliverableType;

public class DeliverableTypeMapper  {


	public static Map<String, Object> map(DeliverableType deliverabletype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(deliverabletype.getDeliverableTypeId() != null ){
			returnVal.put("deliverableTypeId",deliverabletype.getDeliverableTypeId());
}

		if(deliverabletype.getDescription() != null ){
			returnVal.put("description",deliverabletype.getDescription());
}

		return returnVal;
}


	public static DeliverableType map(Map<String, Object> fields) {

		DeliverableType returnVal = new DeliverableType();

		if(fields.get("deliverableTypeId") != null) {
			returnVal.setDeliverableTypeId((String) fields.get("deliverableTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static DeliverableType mapstrstr(Map<String, String> fields) throws Exception {

		DeliverableType returnVal = new DeliverableType();

		if(fields.get("deliverableTypeId") != null) {
			returnVal.setDeliverableTypeId((String) fields.get("deliverableTypeId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static DeliverableType map(GenericValue val) {

DeliverableType returnVal = new DeliverableType();
		returnVal.setDeliverableTypeId(val.getString("deliverableTypeId"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static DeliverableType map(HttpServletRequest request) throws Exception {

		DeliverableType returnVal = new DeliverableType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("deliverableTypeId")) {
returnVal.setDeliverableTypeId(request.getParameter("deliverableTypeId"));
}

		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
