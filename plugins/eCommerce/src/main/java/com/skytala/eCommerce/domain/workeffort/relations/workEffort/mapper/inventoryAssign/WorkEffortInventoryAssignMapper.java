package com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.inventoryAssign;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.inventoryAssign.WorkEffortInventoryAssign;

public class WorkEffortInventoryAssignMapper  {


	public static Map<String, Object> map(WorkEffortInventoryAssign workeffortinventoryassign) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffortinventoryassign.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffortinventoryassign.getWorkEffortId());
}

		if(workeffortinventoryassign.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",workeffortinventoryassign.getInventoryItemId());
}

		if(workeffortinventoryassign.getStatusId() != null ){
			returnVal.put("statusId",workeffortinventoryassign.getStatusId());
}

		if(workeffortinventoryassign.getQuantity() != null ){
			returnVal.put("quantity",workeffortinventoryassign.getQuantity());
}

		return returnVal;
}


	public static WorkEffortInventoryAssign map(Map<String, Object> fields) {

		WorkEffortInventoryAssign returnVal = new WorkEffortInventoryAssign();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}


		return returnVal;
 } 
	public static WorkEffortInventoryAssign mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffortInventoryAssign returnVal = new WorkEffortInventoryAssign();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}


		return returnVal;
 } 
	public static WorkEffortInventoryAssign map(GenericValue val) {

WorkEffortInventoryAssign returnVal = new WorkEffortInventoryAssign();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));


return returnVal;

}

public static WorkEffortInventoryAssign map(HttpServletRequest request) throws Exception {

		WorkEffortInventoryAssign returnVal = new WorkEffortInventoryAssign();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("inventoryItemId"))  {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
return returnVal;

}
}
