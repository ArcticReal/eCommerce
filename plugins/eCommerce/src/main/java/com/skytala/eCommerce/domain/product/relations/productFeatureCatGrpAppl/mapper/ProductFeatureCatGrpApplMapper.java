package com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureCatGrpAppl.model.ProductFeatureCatGrpAppl;

public class ProductFeatureCatGrpApplMapper  {


	public static Map<String, Object> map(ProductFeatureCatGrpAppl productfeaturecatgrpappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeaturecatgrpappl.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",productfeaturecatgrpappl.getProductCategoryId());
}

		if(productfeaturecatgrpappl.getProductFeatureGroupId() != null ){
			returnVal.put("productFeatureGroupId",productfeaturecatgrpappl.getProductFeatureGroupId());
}

		if(productfeaturecatgrpappl.getFromDate() != null ){
			returnVal.put("fromDate",productfeaturecatgrpappl.getFromDate());
}

		if(productfeaturecatgrpappl.getThruDate() != null ){
			returnVal.put("thruDate",productfeaturecatgrpappl.getThruDate());
}

		return returnVal;
}


	public static ProductFeatureCatGrpAppl map(Map<String, Object> fields) {

		ProductFeatureCatGrpAppl returnVal = new ProductFeatureCatGrpAppl();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("productFeatureGroupId") != null) {
			returnVal.setProductFeatureGroupId((String) fields.get("productFeatureGroupId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static ProductFeatureCatGrpAppl mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeatureCatGrpAppl returnVal = new ProductFeatureCatGrpAppl();

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("productFeatureGroupId") != null) {
			returnVal.setProductFeatureGroupId((String) fields.get("productFeatureGroupId"));
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
	public static ProductFeatureCatGrpAppl map(GenericValue val) {

ProductFeatureCatGrpAppl returnVal = new ProductFeatureCatGrpAppl();
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setProductFeatureGroupId(val.getString("productFeatureGroupId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static ProductFeatureCatGrpAppl map(HttpServletRequest request) throws Exception {

		ProductFeatureCatGrpAppl returnVal = new ProductFeatureCatGrpAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productCategoryId")) {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}

		if(paramMap.containsKey("productFeatureGroupId"))  {
returnVal.setProductFeatureGroupId(request.getParameter("productFeatureGroupId"));
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
