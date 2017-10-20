package com.skytala.eCommerce.domain.product.relations.product.mapper.storeCatalog;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.storeCatalog.ProductStoreCatalog;

public class ProductStoreCatalogMapper  {


	public static Map<String, Object> map(ProductStoreCatalog productstorecatalog) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstorecatalog.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstorecatalog.getProductStoreId());
}

		if(productstorecatalog.getProdCatalogId() != null ){
			returnVal.put("prodCatalogId",productstorecatalog.getProdCatalogId());
}

		if(productstorecatalog.getFromDate() != null ){
			returnVal.put("fromDate",productstorecatalog.getFromDate());
}

		if(productstorecatalog.getThruDate() != null ){
			returnVal.put("thruDate",productstorecatalog.getThruDate());
}

		if(productstorecatalog.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productstorecatalog.getSequenceNum());
}

		return returnVal;
}


	public static ProductStoreCatalog map(Map<String, Object> fields) {

		ProductStoreCatalog returnVal = new ProductStoreCatalog();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
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
	public static ProductStoreCatalog mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreCatalog returnVal = new ProductStoreCatalog();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
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
	public static ProductStoreCatalog map(GenericValue val) {

ProductStoreCatalog returnVal = new ProductStoreCatalog();
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setProdCatalogId(val.getString("prodCatalogId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProductStoreCatalog map(HttpServletRequest request) throws Exception {

		ProductStoreCatalog returnVal = new ProductStoreCatalog();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreId")) {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}

		if(paramMap.containsKey("prodCatalogId"))  {
returnVal.setProdCatalogId(request.getParameter("prodCatalogId"));
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
