package com.skytala.eCommerce.domain.product.relations.product.mapper.priceAutoNotice;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.priceAutoNotice.ProductPriceAutoNotice;

public class ProductPriceAutoNoticeMapper  {


	public static Map<String, Object> map(ProductPriceAutoNotice productpriceautonotice) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpriceautonotice.getProductPriceNoticeId() != null ){
			returnVal.put("productPriceNoticeId",productpriceautonotice.getProductPriceNoticeId());
}

		if(productpriceautonotice.getFacilityId() != null ){
			returnVal.put("facilityId",productpriceautonotice.getFacilityId());
}

		if(productpriceautonotice.getRunDate() != null ){
			returnVal.put("runDate",productpriceautonotice.getRunDate());
}

		if(productpriceautonotice.getFromDate() != null ){
			returnVal.put("fromDate",productpriceautonotice.getFromDate());
}

		if(productpriceautonotice.getThruDate() != null ){
			returnVal.put("thruDate",productpriceautonotice.getThruDate());
}

		return returnVal;
}


	public static ProductPriceAutoNotice map(Map<String, Object> fields) {

		ProductPriceAutoNotice returnVal = new ProductPriceAutoNotice();

		if(fields.get("productPriceNoticeId") != null) {
			returnVal.setProductPriceNoticeId((String) fields.get("productPriceNoticeId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("runDate") != null) {
			returnVal.setRunDate((Timestamp) fields.get("runDate"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static ProductPriceAutoNotice mapstrstr(Map<String, String> fields) throws Exception {

		ProductPriceAutoNotice returnVal = new ProductPriceAutoNotice();

		if(fields.get("productPriceNoticeId") != null) {
			returnVal.setProductPriceNoticeId((String) fields.get("productPriceNoticeId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("runDate") != null) {
String buf = fields.get("runDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setRunDate(ibuf);
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


		return returnVal;
 } 
	public static ProductPriceAutoNotice map(GenericValue val) {

ProductPriceAutoNotice returnVal = new ProductPriceAutoNotice();
		returnVal.setProductPriceNoticeId(val.getString("productPriceNoticeId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setRunDate(val.getTimestamp("runDate"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static ProductPriceAutoNotice map(HttpServletRequest request) throws Exception {

		ProductPriceAutoNotice returnVal = new ProductPriceAutoNotice();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPriceNoticeId")) {
returnVal.setProductPriceNoticeId(request.getParameter("productPriceNoticeId"));
}

		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("runDate"))  {
String buf = request.getParameter("runDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setRunDate(ibuf);
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
return returnVal;

}
}
