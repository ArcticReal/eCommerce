package com.skytala.eCommerce.domain.product.relations.product.mapper.promoContent;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.promoContent.ProductPromoContent;

public class ProductPromoContentMapper  {


	public static Map<String, Object> map(ProductPromoContent productpromocontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpromocontent.getProductPromoId() != null ){
			returnVal.put("productPromoId",productpromocontent.getProductPromoId());
}

		if(productpromocontent.getContentId() != null ){
			returnVal.put("contentId",productpromocontent.getContentId());
}

		if(productpromocontent.getProductPromoContentTypeId() != null ){
			returnVal.put("productPromoContentTypeId",productpromocontent.getProductPromoContentTypeId());
}

		if(productpromocontent.getFromDate() != null ){
			returnVal.put("fromDate",productpromocontent.getFromDate());
}

		if(productpromocontent.getThruDate() != null ){
			returnVal.put("thruDate",productpromocontent.getThruDate());
}

		return returnVal;
}


	public static ProductPromoContent map(Map<String, Object> fields) {

		ProductPromoContent returnVal = new ProductPromoContent();

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("productPromoContentTypeId") != null) {
			returnVal.setProductPromoContentTypeId((String) fields.get("productPromoContentTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}


		return returnVal;
 } 
	public static ProductPromoContent mapstrstr(Map<String, String> fields) throws Exception {

		ProductPromoContent returnVal = new ProductPromoContent();

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("productPromoContentTypeId") != null) {
			returnVal.setProductPromoContentTypeId((String) fields.get("productPromoContentTypeId"));
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
	public static ProductPromoContent map(GenericValue val) {

ProductPromoContent returnVal = new ProductPromoContent();
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setProductPromoContentTypeId(val.getString("productPromoContentTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));


return returnVal;

}

public static ProductPromoContent map(HttpServletRequest request) throws Exception {

		ProductPromoContent returnVal = new ProductPromoContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPromoId")) {
returnVal.setProductPromoId(request.getParameter("productPromoId"));
}

		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("productPromoContentTypeId"))  {
returnVal.setProductPromoContentTypeId(request.getParameter("productPromoContentTypeId"));
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
