package com.skytala.eCommerce.domain.product.relations.product.mapper.content;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.content.ProductContent;

public class ProductContentMapper  {


	public static Map<String, Object> map(ProductContent productcontent) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productcontent.getProductId() != null ){
			returnVal.put("productId",productcontent.getProductId());
}

		if(productcontent.getContentId() != null ){
			returnVal.put("contentId",productcontent.getContentId());
}

		if(productcontent.getProductContentTypeId() != null ){
			returnVal.put("productContentTypeId",productcontent.getProductContentTypeId());
}

		if(productcontent.getFromDate() != null ){
			returnVal.put("fromDate",productcontent.getFromDate());
}

		if(productcontent.getThruDate() != null ){
			returnVal.put("thruDate",productcontent.getThruDate());
}

		if(productcontent.getPurchaseFromDate() != null ){
			returnVal.put("purchaseFromDate",productcontent.getPurchaseFromDate());
}

		if(productcontent.getPurchaseThruDate() != null ){
			returnVal.put("purchaseThruDate",productcontent.getPurchaseThruDate());
}

		if(productcontent.getUseCountLimit() != null ){
			returnVal.put("useCountLimit",productcontent.getUseCountLimit());
}

		if(productcontent.getUseTime() != null ){
			returnVal.put("useTime",productcontent.getUseTime());
}

		if(productcontent.getUseTimeUomId() != null ){
			returnVal.put("useTimeUomId",productcontent.getUseTimeUomId());
}

		if(productcontent.getUseRoleTypeId() != null ){
			returnVal.put("useRoleTypeId",productcontent.getUseRoleTypeId());
}

		if(productcontent.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productcontent.getSequenceNum());
}

		return returnVal;
}


	public static ProductContent map(Map<String, Object> fields) {

		ProductContent returnVal = new ProductContent();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("productContentTypeId") != null) {
			returnVal.setProductContentTypeId((String) fields.get("productContentTypeId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("purchaseFromDate") != null) {
			returnVal.setPurchaseFromDate((Timestamp) fields.get("purchaseFromDate"));
}

		if(fields.get("purchaseThruDate") != null) {
			returnVal.setPurchaseThruDate((Timestamp) fields.get("purchaseThruDate"));
}

		if(fields.get("useCountLimit") != null) {
			returnVal.setUseCountLimit((long) fields.get("useCountLimit"));
}

		if(fields.get("useTime") != null) {
			returnVal.setUseTime((long) fields.get("useTime"));
}

		if(fields.get("useTimeUomId") != null) {
			returnVal.setUseTimeUomId((String) fields.get("useTimeUomId"));
}

		if(fields.get("useRoleTypeId") != null) {
			returnVal.setUseRoleTypeId((String) fields.get("useRoleTypeId"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}


		return returnVal;
 } 
	public static ProductContent mapstrstr(Map<String, String> fields) throws Exception {

		ProductContent returnVal = new ProductContent();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("contentId") != null) {
			returnVal.setContentId((String) fields.get("contentId"));
}

		if(fields.get("productContentTypeId") != null) {
			returnVal.setProductContentTypeId((String) fields.get("productContentTypeId"));
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

		if(fields.get("purchaseFromDate") != null) {
String buf = fields.get("purchaseFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPurchaseFromDate(ibuf);
}

		if(fields.get("purchaseThruDate") != null) {
String buf = fields.get("purchaseThruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setPurchaseThruDate(ibuf);
}

		if(fields.get("useCountLimit") != null) {
String buf;
buf = fields.get("useCountLimit");
long ibuf = Long.parseLong(buf);
			returnVal.setUseCountLimit(ibuf);
}

		if(fields.get("useTime") != null) {
String buf;
buf = fields.get("useTime");
long ibuf = Long.parseLong(buf);
			returnVal.setUseTime(ibuf);
}

		if(fields.get("useTimeUomId") != null) {
			returnVal.setUseTimeUomId((String) fields.get("useTimeUomId"));
}

		if(fields.get("useRoleTypeId") != null) {
			returnVal.setUseRoleTypeId((String) fields.get("useRoleTypeId"));
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}


		return returnVal;
 } 
	public static ProductContent map(GenericValue val) {

ProductContent returnVal = new ProductContent();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setContentId(val.getString("contentId"));
		returnVal.setProductContentTypeId(val.getString("productContentTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPurchaseFromDate(val.getTimestamp("purchaseFromDate"));
		returnVal.setPurchaseThruDate(val.getTimestamp("purchaseThruDate"));
		returnVal.setUseCountLimit(val.getLong("useCountLimit"));
		returnVal.setUseTime(val.getLong("useTime"));
		returnVal.setUseTimeUomId(val.getString("useTimeUomId"));
		returnVal.setUseRoleTypeId(val.getString("useRoleTypeId"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));


return returnVal;

}

public static ProductContent map(HttpServletRequest request) throws Exception {

		ProductContent returnVal = new ProductContent();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("contentId"))  {
returnVal.setContentId(request.getParameter("contentId"));
}
		if(paramMap.containsKey("productContentTypeId"))  {
returnVal.setProductContentTypeId(request.getParameter("productContentTypeId"));
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
		if(paramMap.containsKey("purchaseFromDate"))  {
String buf = request.getParameter("purchaseFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setPurchaseFromDate(ibuf);
}
		if(paramMap.containsKey("purchaseThruDate"))  {
String buf = request.getParameter("purchaseThruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setPurchaseThruDate(ibuf);
}
		if(paramMap.containsKey("useCountLimit"))  {
String buf = request.getParameter("useCountLimit");
Long ibuf = Long.parseLong(buf);
returnVal.setUseCountLimit(ibuf);
}
		if(paramMap.containsKey("useTime"))  {
String buf = request.getParameter("useTime");
Long ibuf = Long.parseLong(buf);
returnVal.setUseTime(ibuf);
}
		if(paramMap.containsKey("useTimeUomId"))  {
returnVal.setUseTimeUomId(request.getParameter("useTimeUomId"));
}
		if(paramMap.containsKey("useRoleTypeId"))  {
returnVal.setUseRoleTypeId(request.getParameter("useRoleTypeId"));
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
return returnVal;

}
}
