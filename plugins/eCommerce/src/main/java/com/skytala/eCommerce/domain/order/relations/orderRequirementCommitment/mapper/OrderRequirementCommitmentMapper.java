package com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderRequirementCommitment.model.OrderRequirementCommitment;

public class OrderRequirementCommitmentMapper  {


	public static Map<String, Object> map(OrderRequirementCommitment orderrequirementcommitment) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderrequirementcommitment.getOrderId() != null ){
			returnVal.put("orderId",orderrequirementcommitment.getOrderId());
}

		if(orderrequirementcommitment.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderrequirementcommitment.getOrderItemSeqId());
}

		if(orderrequirementcommitment.getRequirementId() != null ){
			returnVal.put("requirementId",orderrequirementcommitment.getRequirementId());
}

		if(orderrequirementcommitment.getQuantity() != null ){
			returnVal.put("quantity",orderrequirementcommitment.getQuantity());
}

		return returnVal;
}


	public static OrderRequirementCommitment map(Map<String, Object> fields) {

		OrderRequirementCommitment returnVal = new OrderRequirementCommitment();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}


		return returnVal;
 } 
	public static OrderRequirementCommitment mapstrstr(Map<String, String> fields) throws Exception {

		OrderRequirementCommitment returnVal = new OrderRequirementCommitment();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
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
	public static OrderRequirementCommitment map(GenericValue val) {

OrderRequirementCommitment returnVal = new OrderRequirementCommitment();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setRequirementId(val.getString("requirementId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));


return returnVal;

}

public static OrderRequirementCommitment map(HttpServletRequest request) throws Exception {

		OrderRequirementCommitment returnVal = new OrderRequirementCommitment();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("requirementId"))  {
returnVal.setRequirementId(request.getParameter("requirementId"));
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
