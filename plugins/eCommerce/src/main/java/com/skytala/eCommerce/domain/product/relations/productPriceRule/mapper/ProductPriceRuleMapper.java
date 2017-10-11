package com.skytala.eCommerce.domain.product.relations.productPriceRule.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPriceRule.model.ProductPriceRule;

public class ProductPriceRuleMapper  {


	public static Map<String, Object> map(ProductPriceRule productpricerule) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpricerule.getProductPriceRuleId() != null ){
			returnVal.put("productPriceRuleId",productpricerule.getProductPriceRuleId());
}

		if(productpricerule.getRuleName() != null ){
			returnVal.put("ruleName",productpricerule.getRuleName());
}

		if(productpricerule.getDescription() != null ){
			returnVal.put("description",productpricerule.getDescription());
}

		if(productpricerule.getIsSale() != null ){
			returnVal.put("isSale",productpricerule.getIsSale());
}

		if(productpricerule.getFromDate() != null ){
			returnVal.put("fromDate",productpricerule.getFromDate());
}

		if(productpricerule.getThruDate() != null ){
			returnVal.put("thruDate",productpricerule.getThruDate());
}

		return returnVal;
}


	public static ProductPriceRule map(Map<String, Object> fields) {

		ProductPriceRule returnVal = new ProductPriceRule();

		if(fields.get("productPriceRuleId") != null) {
			returnVal.setProductPriceRuleId((String) fields.get("productPriceRuleId"));
}

		if(fields.get("ruleName") != null) {
			returnVal.setRuleName((String) fields.get("ruleName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("isSale") != null) {
			returnVal.setIsSale((boolean) fields.get("isSale"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static ProductPriceRule mapstrstr(Map<String, String> fields) throws Exception {

		ProductPriceRule returnVal = new ProductPriceRule();

		if(fields.get("productPriceRuleId") != null) {
			returnVal.setProductPriceRuleId((String) fields.get("productPriceRuleId"));
}

		if(fields.get("ruleName") != null) {
			returnVal.setRuleName((String) fields.get("ruleName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("isSale") != null) {
String buf;
buf = fields.get("isSale");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsSale(ibuf);
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static ProductPriceRule map(GenericValue val) {

ProductPriceRule returnVal = new ProductPriceRule();
		returnVal.setProductPriceRuleId(val.getString("productPriceRuleId"));
		returnVal.setRuleName(val.getString("ruleName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setIsSale(val.getBoolean("isSale"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static ProductPriceRule map(HttpServletRequest request) throws Exception {

		ProductPriceRule returnVal = new ProductPriceRule();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPriceRuleId")) {
returnVal.setProductPriceRuleId(request.getParameter("productPriceRuleId"));
}

		if(paramMap.containsKey("ruleName"))  {
returnVal.setRuleName(request.getParameter("ruleName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("isSale"))  {
String buf = request.getParameter("isSale");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsSale(ibuf);
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
