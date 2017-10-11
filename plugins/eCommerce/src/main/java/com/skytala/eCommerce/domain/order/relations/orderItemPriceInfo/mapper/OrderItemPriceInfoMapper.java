package com.skytala.eCommerce.domain.order.relations.orderItemPriceInfo.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderItemPriceInfo.model.OrderItemPriceInfo;

public class OrderItemPriceInfoMapper  {


	public static Map<String, Object> map(OrderItemPriceInfo orderitempriceinfo) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderitempriceinfo.getOrderItemPriceInfoId() != null ){
			returnVal.put("orderItemPriceInfoId",orderitempriceinfo.getOrderItemPriceInfoId());
}

		if(orderitempriceinfo.getOrderId() != null ){
			returnVal.put("orderId",orderitempriceinfo.getOrderId());
}

		if(orderitempriceinfo.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderitempriceinfo.getOrderItemSeqId());
}

		if(orderitempriceinfo.getProductPriceRuleId() != null ){
			returnVal.put("productPriceRuleId",orderitempriceinfo.getProductPriceRuleId());
}

		if(orderitempriceinfo.getProductPriceActionSeqId() != null ){
			returnVal.put("productPriceActionSeqId",orderitempriceinfo.getProductPriceActionSeqId());
}

		if(orderitempriceinfo.getModifyAmount() != null ){
			returnVal.put("modifyAmount",orderitempriceinfo.getModifyAmount());
}

		if(orderitempriceinfo.getDescription() != null ){
			returnVal.put("description",orderitempriceinfo.getDescription());
}

		if(orderitempriceinfo.getRateCode() != null ){
			returnVal.put("rateCode",orderitempriceinfo.getRateCode());
}

		return returnVal;
}


	public static OrderItemPriceInfo map(Map<String, Object> fields) {

		OrderItemPriceInfo returnVal = new OrderItemPriceInfo();

		if(fields.get("orderItemPriceInfoId") != null) {
			returnVal.setOrderItemPriceInfoId((String) fields.get("orderItemPriceInfoId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("productPriceRuleId") != null) {
			returnVal.setProductPriceRuleId((String) fields.get("productPriceRuleId"));
}

		if(fields.get("productPriceActionSeqId") != null) {
			returnVal.setProductPriceActionSeqId((String) fields.get("productPriceActionSeqId"));
}

		if(fields.get("modifyAmount") != null) {
			returnVal.setModifyAmount((BigDecimal) fields.get("modifyAmount"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("rateCode") != null) {
			returnVal.setRateCode((String) fields.get("rateCode"));
}


		return returnVal;
 } 
	public static OrderItemPriceInfo mapstrstr(Map<String, String> fields) throws Exception {

		OrderItemPriceInfo returnVal = new OrderItemPriceInfo();

		if(fields.get("orderItemPriceInfoId") != null) {
			returnVal.setOrderItemPriceInfoId((String) fields.get("orderItemPriceInfoId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("productPriceRuleId") != null) {
			returnVal.setProductPriceRuleId((String) fields.get("productPriceRuleId"));
}

		if(fields.get("productPriceActionSeqId") != null) {
			returnVal.setProductPriceActionSeqId((String) fields.get("productPriceActionSeqId"));
}

		if(fields.get("modifyAmount") != null) {
String buf;
buf = fields.get("modifyAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setModifyAmount(bd);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("rateCode") != null) {
			returnVal.setRateCode((String) fields.get("rateCode"));
}


		return returnVal;
 } 
	public static OrderItemPriceInfo map(GenericValue val) {

OrderItemPriceInfo returnVal = new OrderItemPriceInfo();
		returnVal.setOrderItemPriceInfoId(val.getString("orderItemPriceInfoId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setProductPriceRuleId(val.getString("productPriceRuleId"));
		returnVal.setProductPriceActionSeqId(val.getString("productPriceActionSeqId"));
		returnVal.setModifyAmount(val.getBigDecimal("modifyAmount"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setRateCode(val.getString("rateCode"));


return returnVal;

}

public static OrderItemPriceInfo map(HttpServletRequest request) throws Exception {

		OrderItemPriceInfo returnVal = new OrderItemPriceInfo();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderItemPriceInfoId")) {
returnVal.setOrderItemPriceInfoId(request.getParameter("orderItemPriceInfoId"));
}

		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("productPriceRuleId"))  {
returnVal.setProductPriceRuleId(request.getParameter("productPriceRuleId"));
}
		if(paramMap.containsKey("productPriceActionSeqId"))  {
returnVal.setProductPriceActionSeqId(request.getParameter("productPriceActionSeqId"));
}
		if(paramMap.containsKey("modifyAmount"))  {
String buf = request.getParameter("modifyAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setModifyAmount(bd);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("rateCode"))  {
returnVal.setRateCode(request.getParameter("rateCode"));
}
return returnVal;

}
}
