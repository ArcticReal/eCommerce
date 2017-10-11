package com.skytala.eCommerce.domain.product.relations.productPromoUse.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPromoUse.model.ProductPromoUse;

public class ProductPromoUseMapper  {


	public static Map<String, Object> map(ProductPromoUse productpromouse) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpromouse.getOrderId() != null ){
			returnVal.put("orderId",productpromouse.getOrderId());
}

		if(productpromouse.getPromoSequenceId() != null ){
			returnVal.put("promoSequenceId",productpromouse.getPromoSequenceId());
}

		if(productpromouse.getProductPromoId() != null ){
			returnVal.put("productPromoId",productpromouse.getProductPromoId());
}

		if(productpromouse.getProductPromoCodeId() != null ){
			returnVal.put("productPromoCodeId",productpromouse.getProductPromoCodeId());
}

		if(productpromouse.getPartyId() != null ){
			returnVal.put("partyId",productpromouse.getPartyId());
}

		if(productpromouse.getTotalDiscountAmount() != null ){
			returnVal.put("totalDiscountAmount",productpromouse.getTotalDiscountAmount());
}

		if(productpromouse.getQuantityLeftInActions() != null ){
			returnVal.put("quantityLeftInActions",productpromouse.getQuantityLeftInActions());
}

		return returnVal;
}


	public static ProductPromoUse map(Map<String, Object> fields) {

		ProductPromoUse returnVal = new ProductPromoUse();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("promoSequenceId") != null) {
			returnVal.setPromoSequenceId((String) fields.get("promoSequenceId"));
}

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("productPromoCodeId") != null) {
			returnVal.setProductPromoCodeId((String) fields.get("productPromoCodeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("totalDiscountAmount") != null) {
			returnVal.setTotalDiscountAmount((BigDecimal) fields.get("totalDiscountAmount"));
}

		if(fields.get("quantityLeftInActions") != null) {
			returnVal.setQuantityLeftInActions((BigDecimal) fields.get("quantityLeftInActions"));
}


		return returnVal;
 } 
	public static ProductPromoUse mapstrstr(Map<String, String> fields) throws Exception {

		ProductPromoUse returnVal = new ProductPromoUse();

		if(fields.get("orderId") != null) {
			returnVal.setOrderId((String) fields.get("orderId"));
}

		if(fields.get("promoSequenceId") != null) {
			returnVal.setPromoSequenceId((String) fields.get("promoSequenceId"));
}

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("productPromoCodeId") != null) {
			returnVal.setProductPromoCodeId((String) fields.get("productPromoCodeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("totalDiscountAmount") != null) {
String buf;
buf = fields.get("totalDiscountAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalDiscountAmount(bd);
}

		if(fields.get("quantityLeftInActions") != null) {
String buf;
buf = fields.get("quantityLeftInActions");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityLeftInActions(bd);
}


		return returnVal;
 } 
	public static ProductPromoUse map(GenericValue val) {

ProductPromoUse returnVal = new ProductPromoUse();
		returnVal.setOrderId(val.getString("orderId"));
		returnVal.setPromoSequenceId(val.getString("promoSequenceId"));
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setProductPromoCodeId(val.getString("productPromoCodeId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setTotalDiscountAmount(val.getBigDecimal("totalDiscountAmount"));
		returnVal.setQuantityLeftInActions(val.getBigDecimal("quantityLeftInActions"));


return returnVal;

}

public static ProductPromoUse map(HttpServletRequest request) throws Exception {

		ProductPromoUse returnVal = new ProductPromoUse();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("orderId")) {
returnVal.setOrderId(request.getParameter("orderId"));
}

		if(paramMap.containsKey("promoSequenceId"))  {
returnVal.setPromoSequenceId(request.getParameter("promoSequenceId"));
}
		if(paramMap.containsKey("productPromoId"))  {
returnVal.setProductPromoId(request.getParameter("productPromoId"));
}
		if(paramMap.containsKey("productPromoCodeId"))  {
returnVal.setProductPromoCodeId(request.getParameter("productPromoCodeId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("totalDiscountAmount"))  {
String buf = request.getParameter("totalDiscountAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalDiscountAmount(bd);
}
		if(paramMap.containsKey("quantityLeftInActions"))  {
String buf = request.getParameter("quantityLeftInActions");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityLeftInActions(bd);
}
return returnVal;

}
}
