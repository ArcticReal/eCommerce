package com.skytala.eCommerce.domain.product.relations.productFeatureCategoryAppl.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureCategoryAppl.model.ProductFeatureCategoryAppl;

public class ProductFeatureCategoryApplMapper  {


	public static Map<String, Object> map(ProductFeatureCategoryAppl productfeaturecategoryappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeaturecategoryappl.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",productfeaturecategoryappl.getProductCategoryId());
}

		if(productfeaturecategoryappl.getProductFeatureCategoryId() != null ){
			returnVal.put("productFeatureCategoryId",productfeaturecategoryappl.getProductFeatureCategoryId());
}

		if(productfeaturecategoryappl.getFromDate() != null ){
			returnVal.put("fromDate",productfeaturecategoryappl.getFromDate());
}

		if(productfeaturecategoryappl.getThruDate() != null ){
			returnVal.put("thruDate",productfeaturecategoryappl.getThruDate());
}

		return returnVal;
}


	public static ProductFeatureCategoryAppl map(Map<String, Object> fields) {

		ProductFeatureCategoryAppl returnVal = new ProductFeatureCategoryAppl();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("productFeatureCategoryId") != null) {
			returnVal.setProductFeatureCategoryId((String) fields.get("productFeatureCategoryId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static ProductFeatureCategoryAppl mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeatureCategoryAppl returnVal = new ProductFeatureCategoryAppl();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("productFeatureCategoryId") != null) {
			returnVal.setProductFeatureCategoryId((String) fields.get("productFeatureCategoryId"));
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
	public static ProductFeatureCategoryAppl map(GenericValue val) {

ProductFeatureCategoryAppl returnVal = new ProductFeatureCategoryAppl();
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setProductFeatureCategoryId(val.getString("productFeatureCategoryId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static ProductFeatureCategoryAppl map(HttpServletRequest request) throws Exception {

		ProductFeatureCategoryAppl returnVal = new ProductFeatureCategoryAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productCategoryId")) {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}

		if(paramMap.containsKey("productFeatureCategoryId"))  {
returnVal.setProductFeatureCategoryId(request.getParameter("productFeatureCategoryId"));
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
