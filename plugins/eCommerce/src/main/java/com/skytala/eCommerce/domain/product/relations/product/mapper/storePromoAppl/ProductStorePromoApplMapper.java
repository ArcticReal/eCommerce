package com.skytala.eCommerce.domain.product.relations.product.mapper.storePromoAppl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.storePromoAppl.ProductStorePromoAppl;

public class ProductStorePromoApplMapper  {


	public static Map<String, Object> map(ProductStorePromoAppl productstorepromoappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstorepromoappl.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstorepromoappl.getProductStoreId());
}

		if(productstorepromoappl.getProductPromoId() != null ){
			returnVal.put("productPromoId",productstorepromoappl.getProductPromoId());
}

		if(productstorepromoappl.getFromDate() != null ){
			returnVal.put("fromDate",productstorepromoappl.getFromDate());
}

		if(productstorepromoappl.getThruDate() != null ){
			returnVal.put("thruDate",productstorepromoappl.getThruDate());
}

		if(productstorepromoappl.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productstorepromoappl.getSequenceNum());
}

		if(productstorepromoappl.getManualOnly() != null ){
			returnVal.put("manualOnly",productstorepromoappl.getManualOnly());
}

		return returnVal;
}


	public static ProductStorePromoAppl map(Map<String, Object> fields) {

		ProductStorePromoAppl returnVal = new ProductStorePromoAppl();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
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

		if(fields.get("manualOnly") != null) {
			returnVal.setManualOnly((boolean) fields.get("manualOnly"));
}


		return returnVal;
 } 
	public static ProductStorePromoAppl mapstrstr(Map<String, String> fields) throws Exception {

		ProductStorePromoAppl returnVal = new ProductStorePromoAppl();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
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

		if(fields.get("manualOnly") != null) {
String buf;
buf = fields.get("manualOnly");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setManualOnly(ibuf);
}


		return returnVal;
 } 
	public static ProductStorePromoAppl map(GenericValue val) {

ProductStorePromoAppl returnVal = new ProductStorePromoAppl();
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setManualOnly(val.getBoolean("manualOnly"));


return returnVal;

}

public static ProductStorePromoAppl map(HttpServletRequest request) throws Exception {

		ProductStorePromoAppl returnVal = new ProductStorePromoAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreId")) {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}

		if(paramMap.containsKey("productPromoId"))  {
returnVal.setProductPromoId(request.getParameter("productPromoId"));
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
		if(paramMap.containsKey("manualOnly"))  {
String buf = request.getParameter("manualOnly");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setManualOnly(ibuf);
}
return returnVal;

}
}
