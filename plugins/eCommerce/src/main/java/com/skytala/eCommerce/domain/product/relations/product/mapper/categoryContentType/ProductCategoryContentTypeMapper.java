package com.skytala.eCommerce.domain.product.relations.product.mapper.categoryContentType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.categoryContentType.ProductCategoryContentType;

public class ProductCategoryContentTypeMapper  {


	public static Map<String, Object> map(ProductCategoryContentType productcategorycontenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcategorycontenttype.getProdCatContentTypeId() != null ){
			returnVal.put("prodCatContentTypeId",productcategorycontenttype.getProdCatContentTypeId());
}

		if(productcategorycontenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",productcategorycontenttype.getParentTypeId());
}

		if(productcategorycontenttype.getHasTable() != null ){
			returnVal.put("hasTable",productcategorycontenttype.getHasTable());
}

		if(productcategorycontenttype.getDescription() != null ){
			returnVal.put("description",productcategorycontenttype.getDescription());
}

		return returnVal;
}


	public static ProductCategoryContentType map(Map<String, Object> fields) {

		ProductCategoryContentType returnVal = new ProductCategoryContentType();

		if(fields.get("prodCatContentTypeId") != null) {
			returnVal.setProdCatContentTypeId((String) fields.get("prodCatContentTypeId"));
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
	public static ProductCategoryContentType mapstrstr(Map<String, String> fields) throws Exception {

		ProductCategoryContentType returnVal = new ProductCategoryContentType();

		if(fields.get("prodCatContentTypeId") != null) {
			returnVal.setProdCatContentTypeId((String) fields.get("prodCatContentTypeId"));
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
	public static ProductCategoryContentType map(GenericValue val) {

ProductCategoryContentType returnVal = new ProductCategoryContentType();
		returnVal.setProdCatContentTypeId(val.getString("prodCatContentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductCategoryContentType map(HttpServletRequest request) throws Exception {

		ProductCategoryContentType returnVal = new ProductCategoryContentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("prodCatContentTypeId")) {
returnVal.setProdCatContentTypeId(request.getParameter("prodCatContentTypeId"));
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
