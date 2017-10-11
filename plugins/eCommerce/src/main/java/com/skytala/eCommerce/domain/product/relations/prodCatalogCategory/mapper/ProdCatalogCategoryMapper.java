package com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.prodCatalogCategory.model.ProdCatalogCategory;

public class ProdCatalogCategoryMapper  {


	public static Map<String, Object> map(ProdCatalogCategory prodcatalogcategory) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(prodcatalogcategory.getProdCatalogId() != null ){
			returnVal.put("prodCatalogId",prodcatalogcategory.getProdCatalogId());
}

		if(prodcatalogcategory.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",prodcatalogcategory.getProductCategoryId());
}

		if(prodcatalogcategory.getProdCatalogCategoryTypeId() != null ){
			returnVal.put("prodCatalogCategoryTypeId",prodcatalogcategory.getProdCatalogCategoryTypeId());
}

		if(prodcatalogcategory.getFromDate() != null ){
			returnVal.put("fromDate",prodcatalogcategory.getFromDate());
}

		if(prodcatalogcategory.getThruDate() != null ){
			returnVal.put("thruDate",prodcatalogcategory.getThruDate());
}

		if(prodcatalogcategory.getSequenceNum() != null ){
			returnVal.put("sequenceNum",prodcatalogcategory.getSequenceNum());
}

		return returnVal;
}


	public static ProdCatalogCategory map(Map<String, Object> fields) {

		ProdCatalogCategory returnVal = new ProdCatalogCategory();

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
}

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("prodCatalogCategoryTypeId") != null) {
			returnVal.setProdCatalogCategoryTypeId((String) fields.get("prodCatalogCategoryTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static ProdCatalogCategory mapstrstr(Map<String, String> fields) throws Exception {

		ProdCatalogCategory returnVal = new ProdCatalogCategory();

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
}

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("prodCatalogCategoryTypeId") != null) {
			returnVal.setProdCatalogCategoryTypeId((String) fields.get("prodCatalogCategoryTypeId"));
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

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static ProdCatalogCategory map(GenericValue val) {

ProdCatalogCategory returnVal = new ProdCatalogCategory();
		returnVal.setProdCatalogId(val.getString("prodCatalogId"));
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setProdCatalogCategoryTypeId(val.getString("prodCatalogCategoryTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProdCatalogCategory map(HttpServletRequest request) throws Exception {

		ProdCatalogCategory returnVal = new ProdCatalogCategory();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("prodCatalogId")) {
returnVal.setProdCatalogId(request.getParameter("prodCatalogId"));
}

		if(paramMap.containsKey("productCategoryId"))  {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}
		if(paramMap.containsKey("prodCatalogCategoryTypeId"))  {
returnVal.setProdCatalogCategoryTypeId(request.getParameter("prodCatalogCategoryTypeId"));
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
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
