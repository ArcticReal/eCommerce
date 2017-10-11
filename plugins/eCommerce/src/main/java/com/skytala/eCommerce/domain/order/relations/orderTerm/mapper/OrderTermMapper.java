package com.skytala.eCommerce.domain.order.relations.orderTerm.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderTerm.model.OrderTerm;

public class OrderTermMapper  {


	public static Map<String, Object> map(OrderTerm orderterm) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(orderterm.getTermTypeId() != null ){
			returnVal.put("termTypeId",orderterm.getTermTypeId());
}

		if(orderterm.getOrderId() != null ){
			returnVal.put("orderId",orderterm.getOrderId());
}

		if(orderterm.getOrderItemSeqId() != null ){
			returnVal.put("orderItemSeqId",orderterm.getOrderItemSeqId());
}

		if(orderterm.getTermValue() != null ){
			returnVal.put("termValue",orderterm.getTermValue());
}

		if(orderterm.getTermDays() != null ){
			returnVal.put("termDays",orderterm.getTermDays());
}

		if(orderterm.getTextValue() != null ){
			returnVal.put("textValue",orderterm.getTextValue());
}

		if(orderterm.getDescription() != null ){
			returnVal.put("description",orderterm.getDescription());
}

		if(orderterm.getUomId() != null ){
			returnVal.put("uomId",orderterm.getUomId());
}

		return returnVal;
}


	public static OrderTerm map(Map<String, Object> fields) {

		OrderTerm returnVal = new OrderTerm();

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("termValue") != null) {
			returnVal.setTermValue((BigDecimal) fields.get("termValue"));
}

		if(fields.get("termDays") != null) {
			returnVal.setTermDays((long) fields.get("termDays"));
}

		if(fields.get("textValue") != null) {
			returnVal.setTextValue((String) fields.get("textValue"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}


		return returnVal;
 } 
	public static OrderTerm mapstrstr(Map<String, String> fields) throws Exception {

		OrderTerm returnVal = new OrderTerm();

		if(fields.get("termTypeId") != null) {
			returnVal.setTermTypeId((String) fields.get("termTypeId"));
}

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("orderItemSeqId") != null) {
			returnVal.setOrderItemSeqId((String) fields.get("orderItemSeqId"));
}

		if(fields.get("termValue") != null) {
String buf;
buf = fields.get("termValue");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTermValue(bd);
}

		if(fields.get("termDays") != null) {
String buf;
buf = fields.get("termDays");
long ibuf = Long.parseLong(buf);
			returnVal.setTermDays(ibuf);
}

		if(fields.get("textValue") != null) {
			returnVal.setTextValue((String) fields.get("textValue"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}


		return returnVal;
 } 
	public static OrderTerm map(GenericValue val) {

OrderTerm returnVal = new OrderTerm();
		returnVal.setTermTypeId(val.getString("termTypeId"));
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setOrderItemSeqId(val.getString("orderItemSeqId"));
		returnVal.setTermValue(val.getBigDecimal("termValue"));
		returnVal.setTermDays(val.getLong("termDays"));
		returnVal.setTextValue(val.getString("textValue"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setUomId(val.getString("uomId"));


return returnVal;

}

public static OrderTerm map(HttpServletRequest request) throws Exception {

		OrderTerm returnVal = new OrderTerm();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("termTypeId")) {
returnVal.setTermTypeId(request.getParameter("termTypeId"));
}

		if(paramMap.containsKey("orderId"))  {
returnVal.setOrderId(request.getParameter("orderId"));
}
		if(paramMap.containsKey("orderItemSeqId"))  {
returnVal.setOrderItemSeqId(request.getParameter("orderItemSeqId"));
}
		if(paramMap.containsKey("termValue"))  {
String buf = request.getParameter("termValue");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTermValue(bd);
}
		if(paramMap.containsKey("termDays"))  {
String buf = request.getParameter("termDays");
Long ibuf = Long.parseLong(buf);
returnVal.setTermDays(ibuf);
}
		if(paramMap.containsKey("textValue"))  {
returnVal.setTextValue(request.getParameter("textValue"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("uomId"))  {
returnVal.setUomId(request.getParameter("uomId"));
}
return returnVal;

}
}
