package com.skytala.eCommerce.domain.product.relations.productPromoAction.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPromoAction.model.ProductPromoAction;

public class ProductPromoActionMapper  {


	public static Map<String, Object> map(ProductPromoAction productpromoaction) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpromoaction.getProductPromoId() != null ){
			returnVal.put("productPromoId",productpromoaction.getProductPromoId());
}

		if(productpromoaction.getProductPromoRuleId() != null ){
			returnVal.put("productPromoRuleId",productpromoaction.getProductPromoRuleId());
}

		if(productpromoaction.getProductPromoActionSeqId() != null ){
			returnVal.put("productPromoActionSeqId",productpromoaction.getProductPromoActionSeqId());
}

		if(productpromoaction.getProductPromoActionEnumId() != null ){
			returnVal.put("productPromoActionEnumId",productpromoaction.getProductPromoActionEnumId());
}

		if(productpromoaction.getOrderAdjustmentTypeId() != null ){
			returnVal.put("orderAdjustmentTypeId",productpromoaction.getOrderAdjustmentTypeId());
}

		if(productpromoaction.getServiceName() != null ){
			returnVal.put("serviceName",productpromoaction.getServiceName());
}

		if(productpromoaction.getQuantity() != null ){
			returnVal.put("quantity",productpromoaction.getQuantity());
}

		if(productpromoaction.getAmount() != null ){
			returnVal.put("amount",productpromoaction.getAmount());
}

		if(productpromoaction.getProductId() != null ){
			returnVal.put("productId",productpromoaction.getProductId());
}

		if(productpromoaction.getPartyId() != null ){
			returnVal.put("partyId",productpromoaction.getPartyId());
}

		if(productpromoaction.getUseCartQuantity() != null ){
			returnVal.put("useCartQuantity",productpromoaction.getUseCartQuantity());
}

		return returnVal;
}


	public static ProductPromoAction map(Map<String, Object> fields) {

		ProductPromoAction returnVal = new ProductPromoAction();

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("productPromoRuleId") != null) {
			returnVal.setProductPromoRuleId((String) fields.get("productPromoRuleId"));
}

		if(fields.get("productPromoActionSeqId") != null) {
			returnVal.setProductPromoActionSeqId((String) fields.get("productPromoActionSeqId"));
}

		if(fields.get("productPromoActionEnumId") != null) {
			returnVal.setProductPromoActionEnumId((String) fields.get("productPromoActionEnumId"));
}

		if(fields.get("orderAdjustmentTypeId") != null) {
			returnVal.setOrderAdjustmentTypeId((String) fields.get("orderAdjustmentTypeId"));
}

		if(fields.get("serviceName") != null) {
			returnVal.setServiceName((String) fields.get("serviceName"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("useCartQuantity") != null) {
			returnVal.setUseCartQuantity((boolean) fields.get("useCartQuantity"));
}


		return returnVal;
 } 
	public static ProductPromoAction mapstrstr(Map<String, String> fields) throws Exception {

		ProductPromoAction returnVal = new ProductPromoAction();

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("productPromoRuleId") != null) {
			returnVal.setProductPromoRuleId((String) fields.get("productPromoRuleId"));
}

		if(fields.get("productPromoActionSeqId") != null) {
			returnVal.setProductPromoActionSeqId((String) fields.get("productPromoActionSeqId"));
}

		if(fields.get("productPromoActionEnumId") != null) {
			returnVal.setProductPromoActionEnumId((String) fields.get("productPromoActionEnumId"));
}

		if(fields.get("orderAdjustmentTypeId") != null) {
			returnVal.setOrderAdjustmentTypeId((String) fields.get("orderAdjustmentTypeId"));
}

		if(fields.get("serviceName") != null) {
			returnVal.setServiceName((String) fields.get("serviceName"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("useCartQuantity") != null) {
String buf;
buf = fields.get("useCartQuantity");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setUseCartQuantity(ibuf);
}


		return returnVal;
 } 
	public static ProductPromoAction map(GenericValue val) {

ProductPromoAction returnVal = new ProductPromoAction();
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setProductPromoRuleId(val.getString("productPromoRuleId"));
		returnVal.setProductPromoActionSeqId(val.getString("productPromoActionSeqId"));
		returnVal.setProductPromoActionEnumId(val.getString("productPromoActionEnumId"));
		returnVal.setOrderAdjustmentTypeId(val.getString("orderAdjustmentTypeId"));
		returnVal.setServiceName(val.getString("serviceName"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setUseCartQuantity(val.getBoolean("useCartQuantity"));


return returnVal;

}

public static ProductPromoAction map(HttpServletRequest request) throws Exception {

		ProductPromoAction returnVal = new ProductPromoAction();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPromoId")) {
returnVal.setProductPromoId(request.getParameter("productPromoId"));
}

		if(paramMap.containsKey("productPromoRuleId"))  {
returnVal.setProductPromoRuleId(request.getParameter("productPromoRuleId"));
}
		if(paramMap.containsKey("productPromoActionSeqId"))  {
returnVal.setProductPromoActionSeqId(request.getParameter("productPromoActionSeqId"));
}
		if(paramMap.containsKey("productPromoActionEnumId"))  {
returnVal.setProductPromoActionEnumId(request.getParameter("productPromoActionEnumId"));
}
		if(paramMap.containsKey("orderAdjustmentTypeId"))  {
returnVal.setOrderAdjustmentTypeId(request.getParameter("orderAdjustmentTypeId"));
}
		if(paramMap.containsKey("serviceName"))  {
returnVal.setServiceName(request.getParameter("serviceName"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("useCartQuantity"))  {
String buf = request.getParameter("useCartQuantity");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setUseCartQuantity(ibuf);
}
return returnVal;

}
}
