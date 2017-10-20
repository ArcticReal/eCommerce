package com.skytala.eCommerce.domain.order.relations.orderItem.mapper.workFulfillment;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItem.model.workFulfillment.WorkOrderItemFulfillment;

public class WorkOrderItemFulfillmentMapper  {


	public static Map<String, Object> map(WorkOrderItemFulfillment workorderitemfulfillment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workorderitemfulfillment.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workorderitemfulfillment.getWorkEffortId());
}

		if(workorderitemfulfillment.getOrderId() != null ){
			returnVal.put("orderId",workorderitemfulfillment.getOrderId());
}

		if(workorderitemfulfillment.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",workorderitemfulfillment.getOrderItemSeqId());
}

		if(workorderitemfulfillment.getShipGroupSeqId() != null ){
			returnVal.put("shipGroupSeqId",workorderitemfulfillment.getShipGroupSeqId());
}

		return returnVal;
}


	public static WorkOrderItemFulfillment map(Map<String, Object> fields) {

		WorkOrderItemFulfillment returnVal = new WorkOrderItemFulfillment();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}


		return returnVal;
 } 
	public static WorkOrderItemFulfillment mapstrstr(Map<String, String> fields) throws Exception {

		WorkOrderItemFulfillment returnVal = new WorkOrderItemFulfillment();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("shipGroupSeqId") != null) {
			returnVal.setShipGroupSeqId((String) fields.get("shipGroupSeqId"));
}


		return returnVal;
 } 
	public static WorkOrderItemFulfillment map(GenericValue val) {

WorkOrderItemFulfillment returnVal = new WorkOrderItemFulfillment();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setShipGroupSeqId(val.getString("shipGroupSeqId"));


return returnVal;

}

public static WorkOrderItemFulfillment map(HttpServletRequest request) throws Exception {

		WorkOrderItemFulfillment returnVal = new WorkOrderItemFulfillment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("shipGroupSeqId"))  {
returnVal.setShipGroupSeqId(request.getParameter("shipGroupSeqId"));
}
return returnVal;

}
}
