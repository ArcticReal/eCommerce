package com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productFeatureIactnType.model.ProductFeatureIactnType;

public class ProductFeatureIactnTypeMapper  {


	public static Map<String, Object> map(ProductFeatureIactnType productfeatureiactntype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeatureiactntype.getProductFeatureIactnTypeId() != null ){
			returnVal.put("productFeatureIactnTypeId",productfeatureiactntype.getProductFeatureIactnTypeId());
}

		if(productfeatureiactntype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",productfeatureiactntype.getParentTypeId());
}

		if(productfeatureiactntype.getHasTable() != null ){
			returnVal.put("hasTable",productfeatureiactntype.getHasTable());
}

		if(productfeatureiactntype.getDescription() != null ){
			returnVal.put("description",productfeatureiactntype.getDescription());
}

		return returnVal;
}


	public static ProductFeatureIactnType map(Map<String, Object> fields) {

		ProductFeatureIactnType returnVal = new ProductFeatureIactnType();

		if(fields.get("productFeatureIactnTypeId") != null) {
			returnVal.setProductFeatureIactnTypeId((String) fields.get("productFeatureIactnTypeId"));
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
	public static ProductFeatureIactnType mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeatureIactnType returnVal = new ProductFeatureIactnType();

		if(fields.get("productFeatureIactnTypeId") != null) {
			returnVal.setProductFeatureIactnTypeId((String) fields.get("productFeatureIactnTypeId"));
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
	public static ProductFeatureIactnType map(GenericValue val) {

ProductFeatureIactnType returnVal = new ProductFeatureIactnType();
		returnVal.setProductFeatureIactnTypeId(val.getString("productFeatureIactnTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductFeatureIactnType map(HttpServletRequest request) throws Exception {

		ProductFeatureIactnType returnVal = new ProductFeatureIactnType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productFeatureIactnTypeId")) {
returnVal.setProductFeatureIactnTypeId(request.getParameter("productFeatureIactnTypeId"));
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
