package com.skytala.eCommerce.domain.product.relations.product.mapper.promoRule;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.promoRule.ProductPromoRule;

public class ProductPromoRuleMapper  {


	public static Map<String, Object> map(ProductPromoRule productpromorule) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpromorule.getProductPromoId() != null ){
			returnVal.put("productPromoId",productpromorule.getProductPromoId());
}

		if(productpromorule.getProductPromoRuleId() != null ){
			returnVal.put("productPromoRuleId",productpromorule.getProductPromoRuleId());
}

		if(productpromorule.getRuleName() != null ){
			returnVal.put("ruleName",productpromorule.getRuleName());
}

		return returnVal;
}


	public static ProductPromoRule map(Map<String, Object> fields) {

		ProductPromoRule returnVal = new ProductPromoRule();

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("productPromoRuleId") != null) {
			returnVal.setProductPromoRuleId((String) fields.get("productPromoRuleId"));
}

		if(fields.get("ruleName") != null) {
			returnVal.setRuleName((String) fields.get("ruleName"));
}


		return returnVal;
 } 
	public static ProductPromoRule mapstrstr(Map<String, String> fields) throws Exception {

		ProductPromoRule returnVal = new ProductPromoRule();

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("productPromoRuleId") != null) {
			returnVal.setProductPromoRuleId((String) fields.get("productPromoRuleId"));
}

		if(fields.get("ruleName") != null) {
			returnVal.setRuleName((String) fields.get("ruleName"));
}


		return returnVal;
 } 
	public static ProductPromoRule map(GenericValue val) {

ProductPromoRule returnVal = new ProductPromoRule();
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setProductPromoRuleId(val.getString("productPromoRuleId"));
		returnVal.setRuleName(val.getString("ruleName"));


return returnVal;

}

public static ProductPromoRule map(HttpServletRequest request) throws Exception {

		ProductPromoRule returnVal = new ProductPromoRule();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPromoId")) {
returnVal.setProductPromoId(request.getParameter("productPromoId"));
}

		if(paramMap.containsKey("productPromoRuleId"))  {
returnVal.setProductPromoRuleId(request.getParameter("productPromoRuleId"));
}
		if(paramMap.containsKey("ruleName"))  {
returnVal.setRuleName(request.getParameter("ruleName"));
}
return returnVal;

}
}
