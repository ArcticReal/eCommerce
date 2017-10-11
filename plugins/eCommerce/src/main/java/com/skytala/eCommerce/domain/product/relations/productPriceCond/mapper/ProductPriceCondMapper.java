package com.skytala.eCommerce.domain.product.relations.productPriceCond.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productPriceCond.model.ProductPriceCond;

public class ProductPriceCondMapper  {


	public static Map<String, Object> map(ProductPriceCond productpricecond) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpricecond.getProductPriceRuleId() != null ){
			returnVal.put("productPriceRuleId",productpricecond.getProductPriceRuleId());
}

		if(productpricecond.getProductPriceCondSeqId() != null ){
			returnVal.put("productPriceCondSeqId",productpricecond.getProductPriceCondSeqId());
}

		if(productpricecond.getInputParamEnumId() != null ){
			returnVal.put("inputParamEnumId",productpricecond.getInputParamEnumId());
}

		if(productpricecond.getOperatorEnumId() != null ){
			returnVal.put("operatorEnumId",productpricecond.getOperatorEnumId());
}

		if(productpricecond.getCondValue() != null ){
			returnVal.put("condValue",productpricecond.getCondValue());
}

		return returnVal;
}


	public static ProductPriceCond map(Map<String, Object> fields) {

		ProductPriceCond returnVal = new ProductPriceCond();

		if(fields.get("productPriceRuleId") != null) {
			returnVal.setProductPriceRuleId((String) fields.get("productPriceRuleId"));
}

		if(fields.get("productPriceCondSeqId") != null) {
			returnVal.setProductPriceCondSeqId((String) fields.get("productPriceCondSeqId"));
}

		if(fields.get("inputParamEnumId") != null) {
			returnVal.setInputParamEnumId((String) fields.get("inputParamEnumId"));
}

		if(fields.get("operatorEnumId") != null) {
			returnVal.setOperatorEnumId((String) fields.get("operatorEnumId"));
}

		if(fields.get("condValue") != null) {
			returnVal.setCondValue((String) fields.get("condValue"));
}


		return returnVal;
 } 
	public static ProductPriceCond mapstrstr(Map<String, String> fields) throws Exception {

		ProductPriceCond returnVal = new ProductPriceCond();

		if(fields.get("productPriceRuleId") != null) {
			returnVal.setProductPriceRuleId((String) fields.get("productPriceRuleId"));
}

		if(fields.get("productPriceCondSeqId") != null) {
			returnVal.setProductPriceCondSeqId((String) fields.get("productPriceCondSeqId"));
}

		if(fields.get("inputParamEnumId") != null) {
			returnVal.setInputParamEnumId((String) fields.get("inputParamEnumId"));
}

		if(fields.get("operatorEnumId") != null) {
			returnVal.setOperatorEnumId((String) fields.get("operatorEnumId"));
}

		if(fields.get("condValue") != null) {
			returnVal.setCondValue((String) fields.get("condValue"));
}


		return returnVal;
 } 
	public static ProductPriceCond map(GenericValue val) {

ProductPriceCond returnVal = new ProductPriceCond();
		returnVal.setProductPriceRuleId(val.getString("productPriceRuleId"));
		returnVal.setProductPriceCondSeqId(val.getString("productPriceCondSeqId"));
		returnVal.setInputParamEnumId(val.getString("inputParamEnumId"));
		returnVal.setOperatorEnumId(val.getString("operatorEnumId"));
		returnVal.setCondValue(val.getString("condValue"));


return returnVal;

}

public static ProductPriceCond map(HttpServletRequest request) throws Exception {

		ProductPriceCond returnVal = new ProductPriceCond();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPriceRuleId")) {
returnVal.setProductPriceRuleId(request.getParameter("productPriceRuleId"));
}

		if(paramMap.containsKey("productPriceCondSeqId"))  {
returnVal.setProductPriceCondSeqId(request.getParameter("productPriceCondSeqId"));
}
		if(paramMap.containsKey("inputParamEnumId"))  {
returnVal.setInputParamEnumId(request.getParameter("inputParamEnumId"));
}
		if(paramMap.containsKey("operatorEnumId"))  {
returnVal.setOperatorEnumId(request.getParameter("operatorEnumId"));
}
		if(paramMap.containsKey("condValue"))  {
returnVal.setCondValue(request.getParameter("condValue"));
}
return returnVal;

}
}
