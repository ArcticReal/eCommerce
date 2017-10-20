package com.skytala.eCommerce.domain.product.relations.product.mapper.promoCond;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.promoCond.ProductPromoCond;

public class ProductPromoCondMapper  {


	public static Map<String, Object> map(ProductPromoCond productpromocond) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpromocond.getProductPromoId() != null ){
			returnVal.put("productPromoId",productpromocond.getProductPromoId());
}

		if(productpromocond.getProductPromoRuleId() != null ){
			returnVal.put("productPromoRuleId",productpromocond.getProductPromoRuleId());
}

		if(productpromocond.getProductPromoCondSeqId() != null ){
			returnVal.put("productPromoCondSeqId",productpromocond.getProductPromoCondSeqId());
}

		if(productpromocond.getInputParamEnumId() != null ){
			returnVal.put("inputParamEnumId",productpromocond.getInputParamEnumId());
}

		if(productpromocond.getOperatorEnumId() != null ){
			returnVal.put("operatorEnumId",productpromocond.getOperatorEnumId());
}

		if(productpromocond.getCondValue() != null ){
			returnVal.put("condValue",productpromocond.getCondValue());
}

		if(productpromocond.getOtherValue() != null ){
			returnVal.put("otherValue",productpromocond.getOtherValue());
}

		return returnVal;
}


	public static ProductPromoCond map(Map<String, Object> fields) {

		ProductPromoCond returnVal = new ProductPromoCond();

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("productPromoRuleId") != null) {
			returnVal.setProductPromoRuleId((String) fields.get("productPromoRuleId"));
}

		if(fields.get("productPromoCondSeqId") != null) {
			returnVal.setProductPromoCondSeqId((String) fields.get("productPromoCondSeqId"));
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

		if(fields.get("otherValue") != null) {
			returnVal.setOtherValue((String) fields.get("otherValue"));
}


		return returnVal;
 } 
	public static ProductPromoCond mapstrstr(Map<String, String> fields) throws Exception {

		ProductPromoCond returnVal = new ProductPromoCond();

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("productPromoRuleId") != null) {
			returnVal.setProductPromoRuleId((String) fields.get("productPromoRuleId"));
}

		if(fields.get("productPromoCondSeqId") != null) {
			returnVal.setProductPromoCondSeqId((String) fields.get("productPromoCondSeqId"));
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

		if(fields.get("otherValue") != null) {
			returnVal.setOtherValue((String) fields.get("otherValue"));
}


		return returnVal;
 } 
	public static ProductPromoCond map(GenericValue val) {

ProductPromoCond returnVal = new ProductPromoCond();
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setProductPromoRuleId(val.getString("productPromoRuleId"));
		returnVal.setProductPromoCondSeqId(val.getString("productPromoCondSeqId"));
		returnVal.setInputParamEnumId(val.getString("inputParamEnumId"));
		returnVal.setOperatorEnumId(val.getString("operatorEnumId"));
		returnVal.setCondValue(val.getString("condValue"));
		returnVal.setOtherValue(val.getString("otherValue"));


return returnVal;

}

public static ProductPromoCond map(HttpServletRequest request) throws Exception {

		ProductPromoCond returnVal = new ProductPromoCond();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPromoId")) {
returnVal.setProductPromoId(request.getParameter("productPromoId"));
}

		if(paramMap.containsKey("productPromoRuleId"))  {
returnVal.setProductPromoRuleId(request.getParameter("productPromoRuleId"));
}
		if(paramMap.containsKey("productPromoCondSeqId"))  {
returnVal.setProductPromoCondSeqId(request.getParameter("productPromoCondSeqId"));
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
		if(paramMap.containsKey("otherValue"))  {
returnVal.setOtherValue(request.getParameter("otherValue"));
}
return returnVal;

}
}
