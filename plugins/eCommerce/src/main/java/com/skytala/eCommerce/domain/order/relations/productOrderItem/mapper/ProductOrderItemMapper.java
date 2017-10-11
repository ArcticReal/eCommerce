package com.skytala.eCommerce.domain.order.relations.productOrderItem.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.productOrderItem.model.ProductOrderItem;

public class ProductOrderItemMapper  {


	public static Map<String, Object> map(ProductOrderItem productorderitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productorderitem.getOrderId() != null ){
			returnVal.put("orderId",productorderitem.getOrderId());
}

		if(productorderitem.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",productorderitem.getOrderItemSeqId());
}

		if(productorderitem.getEngagementId() != null ){
			returnVal.put("engagementId",productorderitem.getEngagementId());
}

		if(productorderitem.getEngagementItemSeqId() != null ){
			returnVal.put("engagementItemSeqId",productorderitem.getEngagementItemSeqId());
}

		if(productorderitem.getProductId() != null ){
			returnVal.put("productId",productorderitem.getProductId());
}

		return returnVal;
}


	public static ProductOrderItem map(Map<String, Object> fields) {

		ProductOrderItem returnVal = new ProductOrderItem();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("engagementId") != null) {
			returnVal.setEngagementId((String) fields.get("engagementId"));
}

		if(fields.get("engagementItemSeqId") != null) {
			returnVal.setEngagementItemSeqId((String) fields.get("engagementItemSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}


		return returnVal;
 } 
	public static ProductOrderItem mapstrstr(Map<String, String> fields) throws Exception {

		ProductOrderItem returnVal = new ProductOrderItem();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("engagementId") != null) {
			returnVal.setEngagementId((String) fields.get("engagementId"));
}

		if(fields.get("engagementItemSeqId") != null) {
			returnVal.setEngagementItemSeqId((String) fields.get("engagementItemSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}


		return returnVal;
 } 
	public static ProductOrderItem map(GenericValue val) {

ProductOrderItem returnVal = new ProductOrderItem();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setEngagementId(val.getString("engagementId"));
		returnVal.setEngagementItemSeqId(val.getString("engagementItemSeqId"));
		returnVal.setProductId(val.getString("productId"));


return returnVal;

}

public static ProductOrderItem map(HttpServletRequest request) throws Exception {

		ProductOrderItem returnVal = new ProductOrderItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("engagementId"))  {
returnVal.setEngagementId(request.getParameter("engagementId"));
}
		if(paramMap.containsKey("engagementItemSeqId"))  {
returnVal.setEngagementItemSeqId(request.getParameter("engagementItemSeqId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
return returnVal;

}
}
