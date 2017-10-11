package com.skytala.eCommerce.domain.product.relations.productPriceAction.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPriceAction.model.ProductPriceAction;

public class ProductPriceActionMapper  {


	public static Map<String, Object> map(ProductPriceAction productpriceaction) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpriceaction.getProductPriceRuleId() != null ){
			returnVal.put("productPriceRuleId",productpriceaction.getProductPriceRuleId());
}

		if(productpriceaction.getProductPriceActionSeqId() != null ){
			returnVal.put("productPriceActionSeqId",productpriceaction.getProductPriceActionSeqId());
}

		if(productpriceaction.getProductPriceActionTypeId() != null ){
			returnVal.put("productPriceActionTypeId",productpriceaction.getProductPriceActionTypeId());
}

		if(productpriceaction.getAmount() != null ){
			returnVal.put("amount",productpriceaction.getAmount());
}

		if(productpriceaction.getRateCode() != null ){
			returnVal.put("rateCode",productpriceaction.getRateCode());
}

		return returnVal;
}


	public static ProductPriceAction map(Map<String, Object> fields) {

		ProductPriceAction returnVal = new ProductPriceAction();

		if(fields.get("productPriceRuleId") != null) {
			returnVal.setProductPriceRuleId((String) fields.get("productPriceRuleId"));
}

		if(fields.get("productPriceActionSeqId") != null) {
			returnVal.setProductPriceActionSeqId((String) fields.get("productPriceActionSeqId"));
}

		if(fields.get("productPriceActionTypeId") != null) {
			returnVal.setProductPriceActionTypeId((String) fields.get("productPriceActionTypeId"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("rateCode") != null) {
			returnVal.setRateCode((String) fields.get("rateCode"));
}


		return returnVal;
 } 
	public static ProductPriceAction mapstrstr(Map<String, String> fields) throws Exception {

		ProductPriceAction returnVal = new ProductPriceAction();

		if(fields.get("productPriceRuleId") != null) {
			returnVal.setProductPriceRuleId((String) fields.get("productPriceRuleId"));
}

		if(fields.get("productPriceActionSeqId") != null) {
			returnVal.setProductPriceActionSeqId((String) fields.get("productPriceActionSeqId"));
}

		if(fields.get("productPriceActionTypeId") != null) {
			returnVal.setProductPriceActionTypeId((String) fields.get("productPriceActionTypeId"));
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("rateCode") != null) {
			returnVal.setRateCode((String) fields.get("rateCode"));
}


		return returnVal;
 } 
	public static ProductPriceAction map(GenericValue val) {

ProductPriceAction returnVal = new ProductPriceAction();
		returnVal.setProductPriceRuleId(val.getString("productPriceRuleId"));
		returnVal.setProductPriceActionSeqId(val.getString("productPriceActionSeqId"));
		returnVal.setProductPriceActionTypeId(val.getString("productPriceActionTypeId"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setRateCode(val.getString("rateCode"));


return returnVal;

}

public static ProductPriceAction map(HttpServletRequest request) throws Exception {

		ProductPriceAction returnVal = new ProductPriceAction();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPriceRuleId")) {
returnVal.setProductPriceRuleId(request.getParameter("productPriceRuleId"));
}

		if(paramMap.containsKey("productPriceActionSeqId"))  {
returnVal.setProductPriceActionSeqId(request.getParameter("productPriceActionSeqId"));
}
		if(paramMap.containsKey("productPriceActionTypeId"))  {
returnVal.setProductPriceActionTypeId(request.getParameter("productPriceActionTypeId"));
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("rateCode"))  {
returnVal.setRateCode(request.getParameter("rateCode"));
}
return returnVal;

}
}
