package com.skytala.eCommerce.domain.workeffort.relations.deliverable.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.deliverable.model.Deliverable;

public class DeliverableMapper  {


	public static Map<String, Object> map(Deliverable deliverable) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(deliverable.getDeliverableId() != null ){
			returnVal.put("deliverableId",deliverable.getDeliverableId());
}

		if(deliverable.getDeliverableTypeId() != null ){
			returnVal.put("deliverableTypeId",deliverable.getDeliverableTypeId());
}

		if(deliverable.getDeliverableName() != null ){
			returnVal.put("deliverableName",deliverable.getDeliverableName());
}

		if(deliverable.getDescription() != null ){
			returnVal.put("description",deliverable.getDescription());
}

		return returnVal;
}


	public static Deliverable map(Map<String, Object> fields) {

		Deliverable returnVal = new Deliverable();

		if(fields.get("deliverableId") != null) {
			returnVal.setDeliverableId((String) fields.get("deliverableId"));
}

		if(fields.get("deliverableTypeId") != null) {
			returnVal.setDeliverableTypeId((String) fields.get("deliverableTypeId"));
}

		if(fields.get("deliverableName") != null) {
			returnVal.setDeliverableName((String) fields.get("deliverableName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static Deliverable mapstrstr(Map<String, String> fields) throws Exception {

		Deliverable returnVal = new Deliverable();

		if(fields.get("deliverableId") != null) {
			returnVal.setDeliverableId((String) fields.get("deliverableId"));
}

		if(fields.get("deliverableTypeId") != null) {
			returnVal.setDeliverableTypeId((String) fields.get("deliverableTypeId"));
}

		if(fields.get("deliverableName") != null) {
			returnVal.setDeliverableName((String) fields.get("deliverableName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static Deliverable map(GenericValue val) {

Deliverable returnVal = new Deliverable();
		returnVal.setDeliverableId(val.getString("deliverableId"));
		returnVal.setDeliverableTypeId(val.getString("deliverableTypeId"));
		returnVal.setDeliverableName(val.getString("deliverableName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static Deliverable map(HttpServletRequest request) throws Exception {

		Deliverable returnVal = new Deliverable();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("deliverableId")) {
returnVal.setDeliverableId(request.getParameter("deliverableId"));
}

		if(paramMap.containsKey("deliverableTypeId"))  {
returnVal.setDeliverableTypeId(request.getParameter("deliverableTypeId"));
}
		if(paramMap.containsKey("deliverableName"))  {
returnVal.setDeliverableName(request.getParameter("deliverableName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
