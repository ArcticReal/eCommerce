package com.skytala.eCommerce.domain.product.relations.productContentType.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productContentType.model.ProductContentType;

public class ProductContentTypeMapper  {


	public static Map<String, Object> map(ProductContentType productcontenttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcontenttype.getProductContentTypeId() != null ){
			returnVal.put("productContentTypeId",productcontenttype.getProductContentTypeId());
}

		if(productcontenttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",productcontenttype.getParentTypeId());
}

		if(productcontenttype.getHasTable() != null ){
			returnVal.put("hasTable",productcontenttype.getHasTable());
}

		if(productcontenttype.getDescription() != null ){
			returnVal.put("description",productcontenttype.getDescription());
}

		return returnVal;
}


	public static ProductContentType map(Map<String, Object> fields) {

		ProductContentType returnVal = new ProductContentType();

		if(fields.get("productContentTypeId") != null) {
			returnVal.setProductContentTypeId((String) fields.get("productContentTypeId"));
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
	public static ProductContentType mapstrstr(Map<String, String> fields) throws Exception {

		ProductContentType returnVal = new ProductContentType();

		if(fields.get("productContentTypeId") != null) {
			returnVal.setProductContentTypeId((String) fields.get("productContentTypeId"));
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
	public static ProductContentType map(GenericValue val) {

ProductContentType returnVal = new ProductContentType();
		returnVal.setProductContentTypeId(val.getString("productContentTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductContentType map(HttpServletRequest request) throws Exception {

		ProductContentType returnVal = new ProductContentType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productContentTypeId")) {
returnVal.setProductContentTypeId(request.getParameter("productContentTypeId"));
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
