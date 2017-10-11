package com.skytala.eCommerce.domain.product.relations.productFeatureType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureType.model.ProductFeatureType;

public class ProductFeatureTypeMapper  {


	public static Map<String, Object> map(ProductFeatureType productfeaturetype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeaturetype.getProductFeatureTypeId() != null ){
			returnVal.put("productFeatureTypeId",productfeaturetype.getProductFeatureTypeId());
}

		if(productfeaturetype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",productfeaturetype.getParentTypeId());
}

		if(productfeaturetype.getHasTable() != null ){
			returnVal.put("hasTable",productfeaturetype.getHasTable());
}

		if(productfeaturetype.getDescription() != null ){
			returnVal.put("description",productfeaturetype.getDescription());
}

		return returnVal;
}


	public static ProductFeatureType map(Map<String, Object> fields) {

		ProductFeatureType returnVal = new ProductFeatureType();

		if(fields.get("productFeatureTypeId") != null) {
			returnVal.setProductFeatureTypeId((String) fields.get("productFeatureTypeId"));
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
	public static ProductFeatureType mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeatureType returnVal = new ProductFeatureType();

		if(fields.get("productFeatureTypeId") != null) {
			returnVal.setProductFeatureTypeId((String) fields.get("productFeatureTypeId"));
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
	public static ProductFeatureType map(GenericValue val) {

ProductFeatureType returnVal = new ProductFeatureType();
		returnVal.setProductFeatureTypeId(val.getString("productFeatureTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductFeatureType map(HttpServletRequest request) throws Exception {

		ProductFeatureType returnVal = new ProductFeatureType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productFeatureTypeId")) {
returnVal.setProductFeatureTypeId(request.getParameter("productFeatureTypeId"));
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
