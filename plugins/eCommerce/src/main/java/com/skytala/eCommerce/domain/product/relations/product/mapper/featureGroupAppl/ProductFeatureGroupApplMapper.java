package com.skytala.eCommerce.domain.product.relations.product.mapper.featureGroupAppl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.featureGroupAppl.ProductFeatureGroupAppl;

public class ProductFeatureGroupApplMapper  {


	public static Map<String, Object> map(ProductFeatureGroupAppl productfeaturegroupappl) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productfeaturegroupappl.getProductFeatureGroupId() != null ){
			returnVal.put("productFeatureGroupId",productfeaturegroupappl.getProductFeatureGroupId());
}

		if(productfeaturegroupappl.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",productfeaturegroupappl.getProductFeatureId());
}

		if(productfeaturegroupappl.getFromDate() != null ){
			returnVal.put("fromDate",productfeaturegroupappl.getFromDate());
}

		if(productfeaturegroupappl.getThruDate() != null ){
			returnVal.put("thruDate",productfeaturegroupappl.getThruDate());
}

		if(productfeaturegroupappl.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productfeaturegroupappl.getSequenceNum());
}

		return returnVal;
}


	public static ProductFeatureGroupAppl map(Map<String, Object> fields) {

		ProductFeatureGroupAppl returnVal = new ProductFeatureGroupAppl();

		if(fields.get("productFeatureGroupId") != null) {
			returnVal.setProductFeatureGroupId((String) fields.get("productFeatureGroupId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
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
	public static ProductFeatureGroupAppl mapstrstr(Map<String, String> fields) throws Exception {

		ProductFeatureGroupAppl returnVal = new ProductFeatureGroupAppl();

		if(fields.get("productFeatureGroupId") != null) {
			returnVal.setProductFeatureGroupId((String) fields.get("productFeatureGroupId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
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
	public static ProductFeatureGroupAppl map(GenericValue val) {

ProductFeatureGroupAppl returnVal = new ProductFeatureGroupAppl();
		returnVal.setProductFeatureGroupId(val.getString("productFeatureGroupId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProductFeatureGroupAppl map(HttpServletRequest request) throws Exception {

		ProductFeatureGroupAppl returnVal = new ProductFeatureGroupAppl();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productFeatureGroupId")) {
returnVal.setProductFeatureGroupId(request.getParameter("productFeatureGroupId"));
}

		if(paramMap.containsKey("productFeatureId"))  {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
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
