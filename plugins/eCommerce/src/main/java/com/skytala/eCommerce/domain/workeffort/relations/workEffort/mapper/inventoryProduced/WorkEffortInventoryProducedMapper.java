package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.inventoryProduced;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryProduced.WorkEffortInventoryProduced;

public class WorkEffortInventoryProducedMapper  {


	public static Map<String, Object> map(WorkEffortInventoryProduced workeffortinventoryproduced) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortinventoryproduced.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortinventoryproduced.getWorkEffortId());
}

		if(workeffortinventoryproduced.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",workeffortinventoryproduced.getInventoryItemId());
}

		return returnVal;
}


	public static WorkEffortInventoryProduced map(Map<String, Object> fields) {

		WorkEffortInventoryProduced returnVal = new WorkEffortInventoryProduced();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}


		return returnVal;
 } 
	public static WorkEffortInventoryProduced mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortInventoryProduced returnVal = new WorkEffortInventoryProduced();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}


		return returnVal;
 } 
	public static WorkEffortInventoryProduced map(GenericValue val) {

WorkEffortInventoryProduced returnVal = new WorkEffortInventoryProduced();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));


return returnVal;

}

public static WorkEffortInventoryProduced map(HttpServletRequest request) throws Exception {

		WorkEffortInventoryProduced returnVal = new WorkEffortInventoryProduced();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("inventoryItemId"))  {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}
return returnVal;

}
}
