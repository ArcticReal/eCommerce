package com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetMaintOrder.model.FixedAssetMaintOrder;

public class FixedAssetMaintOrderMapper  {


	public static Map<String, Object> map(FixedAssetMaintOrder fixedassetmaintorder) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassetmaintorder.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedassetmaintorder.getFixedAssetId());
}

		if(fixedassetmaintorder.getMaintHistSeqId() != null ){
			returnVal.put("maintHistSeqId",fixedassetmaintorder.getMaintHistSeqId());
}

		if(fixedassetmaintorder.getOrderId() != null ){
			returnVal.put("orderId",fixedassetmaintorder.getOrderId());
}

		if(fixedassetmaintorder.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",fixedassetmaintorder.getOrderItemSeqId());
}

		return returnVal;
}


	public static FixedAssetMaintOrder map(Map<String, Object> fields) {

		FixedAssetMaintOrder returnVal = new FixedAssetMaintOrder();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("maintHistSeqId") != null) {
			returnVal.setMaintHistSeqId((String) fields.get("maintHistSeqId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}


		return returnVal;
 } 
	public static FixedAssetMaintOrder mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetMaintOrder returnVal = new FixedAssetMaintOrder();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("maintHistSeqId") != null) {
			returnVal.setMaintHistSeqId((String) fields.get("maintHistSeqId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}


		return returnVal;
 } 
	public static FixedAssetMaintOrder map(GenericValue val) {

FixedAssetMaintOrder returnVal = new FixedAssetMaintOrder();
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setMaintHistSeqId(val.getString("maintHistSeqId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));


return returnVal;

}

public static FixedAssetMaintOrder map(HttpServletRequest request) throws Exception {

		FixedAssetMaintOrder returnVal = new FixedAssetMaintOrder();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetId")) {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}

		if(paramMap.containsKey("maintHistSeqId"))  {
returnVal.setMaintHistSeqId(request.getParameter("maintHistSeqId"));
}
		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
return returnVal;

}
}
