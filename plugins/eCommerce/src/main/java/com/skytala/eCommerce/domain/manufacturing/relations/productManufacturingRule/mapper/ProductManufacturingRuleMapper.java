package com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.manufacturing.relations.productManufacturingRule.model.ProductManufacturingRule;

public class ProductManufacturingRuleMapper  {


	public static Map<String, Object> map(ProductManufacturingRule productmanufacturingrule) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productmanufacturingrule.getRuleId() != null ){
			returnVal.put("ruleId",productmanufacturingrule.getRuleId());
}

		if(productmanufacturingrule.getProductId() != null ){
			returnVal.put("productId",productmanufacturingrule.getProductId());
}

		if(productmanufacturingrule.getProductIdFor() != null ){
			returnVal.put("productIdFor",productmanufacturingrule.getProductIdFor());
}

		if(productmanufacturingrule.getProductIdIn() != null ){
			returnVal.put("productIdIn",productmanufacturingrule.getProductIdIn());
}

		if(productmanufacturingrule.getRuleSeqId() != null ){
			returnVal.put("ruleSeqId",productmanufacturingrule.getRuleSeqId());
}

		if(productmanufacturingrule.getFromDate() != null ){
			returnVal.put("fromDate",productmanufacturingrule.getFromDate());
}

		if(productmanufacturingrule.getProductIdInSubst() != null ){
			returnVal.put("productIdInSubst",productmanufacturingrule.getProductIdInSubst());
}

		if(productmanufacturingrule.getProductFeature() != null ){
			returnVal.put("productFeature",productmanufacturingrule.getProductFeature());
}

		if(productmanufacturingrule.getRuleOperator() != null ){
			returnVal.put("ruleOperator",productmanufacturingrule.getRuleOperator());
}

		if(productmanufacturingrule.getQuantity() != null ){
			returnVal.put("quantity",productmanufacturingrule.getQuantity());
}

		if(productmanufacturingrule.getDescription() != null ){
			returnVal.put("description",productmanufacturingrule.getDescription());
}

		if(productmanufacturingrule.getThruDate() != null ){
			returnVal.put("thruDate",productmanufacturingrule.getThruDate());
}

		return returnVal;
}


	public static ProductManufacturingRule map(Map<String, Object> fields) {

		ProductManufacturingRule returnVal = new ProductManufacturingRule();

		if(fields.get("ruleId") != null) {
			returnVal.setRuleId((String) fields.get("ruleId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productIdFor") != null) {
			returnVal.setProductIdFor((String) fields.get("productIdFor"));
}

		if(fields.get("productIdIn") != null) {
			returnVal.setProductIdIn((String) fields.get("productIdIn"));
}

		if(fields.get("ruleSeqId") != null) {
			returnVal.setRuleSeqId((String) fields.get("ruleSeqId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("productIdInSubst") != null) {
			returnVal.setProductIdInSubst((String) fields.get("productIdInSubst"));
}

		if(fields.get("productFeature") != null) {
			returnVal.setProductFeature((String) fields.get("productFeature"));
}

		if(fields.get("ruleOperator") != null) {
			returnVal.setRuleOperator((String) fields.get("ruleOperator"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static ProductManufacturingRule mapstrstr(Map<String, String> fields) throws Exception {

		ProductManufacturingRule returnVal = new ProductManufacturingRule();

		if(fields.get("ruleId") != null) {
			returnVal.setRuleId((String) fields.get("ruleId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productIdFor") != null) {
			returnVal.setProductIdFor((String) fields.get("productIdFor"));
}

		if(fields.get("productIdIn") != null) {
			returnVal.setProductIdIn((String) fields.get("productIdIn"));
}

		if(fields.get("ruleSeqId") != null) {
			returnVal.setRuleSeqId((String) fields.get("ruleSeqId"));
}

		if(fields.get("fromDate") != null) {
String buf = fields.get("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setFromDate(ibuf);
}

		if(fields.get("productIdInSubst") != null) {
			returnVal.setProductIdInSubst((String) fields.get("productIdInSubst"));
}

		if(fields.get("productFeature") != null) {
			returnVal.setProductFeature((String) fields.get("productFeature"));
}

		if(fields.get("ruleOperator") != null) {
			returnVal.setRuleOperator((String) fields.get("ruleOperator"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("thruDate") != null) {
String buf = fields.get("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setThruDate(ibuf);
}


		return returnVal;
 } 
	public static ProductManufacturingRule map(GenericValue val) {

ProductManufacturingRule returnVal = new ProductManufacturingRule();
		returnVal.setRuleId(val.getString("ruleId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductIdFor(val.getString("productIdFor"));
		returnVal.setProductIdIn(val.getString("productIdIn"));
		returnVal.setRuleSeqId(val.getString("ruleSeqId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setProductIdInSubst(val.getString("productIdInSubst"));
		returnVal.setProductFeature(val.getString("productFeature"));
		returnVal.setRuleOperator(val.getString("ruleOperator"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static ProductManufacturingRule map(HttpServletRequest request) throws Exception {

		ProductManufacturingRule returnVal = new ProductManufacturingRule();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("ruleId")) {
returnVal.setRuleId(request.getParameter("ruleId"));
}

		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("productIdFor"))  {
returnVal.setProductIdFor(request.getParameter("productIdFor"));
}
		if(paramMap.containsKey("productIdIn"))  {
returnVal.setProductIdIn(request.getParameter("productIdIn"));
}
		if(paramMap.containsKey("ruleSeqId"))  {
returnVal.setRuleSeqId(request.getParameter("ruleSeqId"));
}
		if(paramMap.containsKey("fromDate"))  {
String buf = request.getParameter("fromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setFromDate(ibuf);
}
		if(paramMap.containsKey("productIdInSubst"))  {
returnVal.setProductIdInSubst(request.getParameter("productIdInSubst"));
}
		if(paramMap.containsKey("productFeature"))  {
returnVal.setProductFeature(request.getParameter("productFeature"));
}
		if(paramMap.containsKey("ruleOperator"))  {
returnVal.setRuleOperator(request.getParameter("ruleOperator"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("thruDate"))  {
String buf = request.getParameter("thruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setThruDate(ibuf);
}
return returnVal;

}
}
