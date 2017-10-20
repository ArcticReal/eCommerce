package com.skytala.eCommerce.domain.product.relations.product.mapper.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.type.ProductType;

public class ProductTypeMapper  {


	public static Map<String, Object> map(ProductType producttype) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(producttype.getProductTypeId() != null ){
			returnVal.put("productTypeId",producttype.getProductTypeId());
}

		if(producttype.getParentTypeId() != null ){
			returnVal.put("parentTypeId",producttype.getParentTypeId());
}

		if(producttype.getIsPhysical() != null ){
			returnVal.put("isPhysical",producttype.getIsPhysical());
}

		if(producttype.getIsDigital() != null ){
			returnVal.put("isDigital",producttype.getIsDigital());
}

		if(producttype.getHasTable() != null ){
			returnVal.put("hasTable",producttype.getHasTable());
}

		if(producttype.getDescription() != null ){
			returnVal.put("description",producttype.getDescription());
}

		return returnVal;
}


	public static ProductType map(Map<String, Object> fields) {

		ProductType returnVal = new ProductType();

		if(fields.get("productTypeId") != null) {
			returnVal.setProductTypeId((String) fields.get("productTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("isPhysical") != null) {
			returnVal.setIsPhysical((boolean) fields.get("isPhysical"));
}

		if(fields.get("isDigital") != null) {
			returnVal.setIsDigital((boolean) fields.get("isDigital"));
}

		if(fields.get("hasTable") != null) {
			returnVal.setHasTable((boolean) fields.get("hasTable"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static ProductType mapstrstr(Map<String, String> fields) throws Exception {

		ProductType returnVal = new ProductType();

		if(fields.get("productTypeId") != null) {
			returnVal.setProductTypeId((String) fields.get("productTypeId"));
}

		if(fields.get("parentTypeId") != null) {
			returnVal.setParentTypeId((String) fields.get("parentTypeId"));
}

		if(fields.get("isPhysical") != null) {
String buf;
buf = fields.get("isPhysical");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsPhysical(ibuf);
}

		if(fields.get("isDigital") != null) {
String buf;
buf = fields.get("isDigital");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsDigital(ibuf);
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
	public static ProductType map(GenericValue val) {

ProductType returnVal = new ProductType();
		returnVal.setProductTypeId(val.getString("productTypeId"));
		returnVal.setParentTypeId(val.getString("parentTypeId"));
		returnVal.setIsPhysical(val.getBoolean("isPhysical"));
		returnVal.setIsDigital(val.getBoolean("isDigital"));
		returnVal.setHasTable(val.getBoolean("hasTable"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static ProductType map(HttpServletRequest request) throws Exception {

		ProductType returnVal = new ProductType();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productTypeId")) {
returnVal.setProductTypeId(request.getParameter("productTypeId"));
}

		if(paramMap.containsKey("parentTypeId"))  {
returnVal.setParentTypeId(request.getParameter("parentTypeId"));
}
		if(paramMap.containsKey("isPhysical"))  {
String buf = request.getParameter("isPhysical");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsPhysical(ibuf);
}
		if(paramMap.containsKey("isDigital"))  {
String buf = request.getParameter("isDigital");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsDigital(ibuf);
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
