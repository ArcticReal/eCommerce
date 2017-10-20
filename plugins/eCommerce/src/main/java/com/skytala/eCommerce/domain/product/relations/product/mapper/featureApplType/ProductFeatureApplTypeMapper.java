package com.skytala.eCommerce.domain.product.relations.product.mapper.featureApplType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.featureApplType.ProductFeatureApplType;

public class ProductFeatureApplTypeMapper  {


	public static Map<String, Object> map(ProductFeatureApplType productfeatureappltype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeatureappltype.getProductFeatureApplTypeId() != null ){
			returnVal.put("productFeatureApplTypeId",productfeatureappltype.getProductFeatureApplTypeId());
}

		if(productfeatureappltype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",productfeatureappltype.getParentTypeId());
}

		if(productfeatureappltype.getHasTable() != null ){
			returnVal.put("hasTable",productfeatureappltype.getHasTable());
}

		if(productfeatureappltype.getDescription() != null ){
			returnVal.put("description",productfeatureappltype.getDescription());
}

		return returnVal;
}


	public static ProductFeatureApplType map(Map<String, Object> fields) {

		ProductFeatureApplType returnVal = new ProductFeatureApplType();

		if(fields.get("productFeatureApplTypeId") != null) {
			returnVal.setProductFeatureApplTypeId((String) fields.get("productFeatureApplTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductFeatureApplType mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeatureApplType returnVal = new ProductFeatureApplType();

		if(fields.get("productFeatureApplTypeId") != null) {
			returnVal.setProductFeatureApplTypeId((String) fields.get("productFeatureApplTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("hasTable") != null) {
String buf;
buf = fields.get("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasTable(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductFeatureApplType map(GenericValue val) {

ProductFeatureApplType returnVal = new ProductFeatureApplType();
		returnVal.setProductFeatureApplTypeId(val.getString("productFeatureApplTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductFeatureApplType map(HttpServletRequest request) throws Exception {

		ProductFeatureApplType returnVal = new ProductFeatureApplType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productFeatureApplTypeId")) {
returnVal.setProductFeatureApplTypeId(request.getParameter("productFeatureApplTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("hasTable"))  {
String buf = request.getParameter("hasTable");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setHasTable(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
