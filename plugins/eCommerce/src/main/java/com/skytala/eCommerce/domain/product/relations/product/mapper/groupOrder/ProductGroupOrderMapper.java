package com.skytala.eCommerce.domain.product.relations.product.mapper.groupOrder;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.groupOrder.ProductGroupOrder;

public class ProductGroupOrderMapper  {


	public static Map<String, Object> map(ProductGroupOrder productgrouporder) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productgrouporder.getGroupOrderId() != null ){
			returnVal.put("groupOrderId",productgrouporder.getGroupOrderId());
}

		if(productgrouporder.getProductId() != null ){
			returnVal.put("productId",productgrouporder.getProductId());
}

		if(productgrouporder.getFromDate() != null ){
			returnVal.put("fromDate",productgrouporder.getFromDate());
}

		if(productgrouporder.getThruDate() != null ){
			returnVal.put("thruDate",productgrouporder.getThruDate());
}

		if(productgrouporder.getStatusId() != null ){
			returnVal.put("statusId",productgrouporder.getStatusId());
}

		if(productgrouporder.getReqOrderQty() != null ){
			returnVal.put("reqOrderQty",productgrouporder.getReqOrderQty());
}

		if(productgrouporder.getSoldOrderQty() != null ){
			returnVal.put("soldOrderQty",productgrouporder.getSoldOrderQty());
}

		if(productgrouporder.getJobId() != null ){
			returnVal.put("jobId",productgrouporder.getJobId());
}

		return returnVal;
}


	public static ProductGroupOrder map(Map<String, Object> fields) {

		ProductGroupOrder returnVal = new ProductGroupOrder();

		if(fields.get("groupOrderId") != null) {
			returnVal.setGroupOrderId((String) fields.get("groupOrderId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("reqOrderQty") != null) {
			returnVal.setReqOrderQty((BigDecimal) fields.get("reqOrderQty"));
}

		if(fields.get("soldOrderQty") != null) {
			returnVal.setSoldOrderQty((BigDecimal) fields.get("soldOrderQty"));
}

		if(fields.get("jobId") != null) {
			returnVal.setJobId((String) fields.get("jobId"));
}


		return returnVal;
 } 
	public static ProductGroupOrder mapstrstr(Map<String, String> fields) throws Exception {

		ProductGroupOrder returnVal = new ProductGroupOrder();

		if(fields.get("groupOrderId") != null) {
			returnVal.setGroupOrderId((String) fields.get("groupOrderId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
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

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("reqOrderQty") != null) {
String buf;
buf = fields.get("reqOrderQty");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReqOrderQty(bd);
}

		if(fields.get("soldOrderQty") != null) {
String buf;
buf = fields.get("soldOrderQty");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSoldOrderQty(bd);
}

		if(fields.get("jobId") != null) {
			returnVal.setJobId((String) fields.get("jobId"));
}


		return returnVal;
 } 
	public static ProductGroupOrder map(GenericValue val) {

ProductGroupOrder returnVal = new ProductGroupOrder();
		returnVal.setGroupOrderId(val.getString("groupOrderId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setReqOrderQty(val.getBigDecimal("reqOrderQty"));
		returnVal.setSoldOrderQty(val.getBigDecimal("soldOrderQty"));
		returnVal.setJobId(val.getString("jobId"));


return returnVal;

}

public static ProductGroupOrder map(HttpServletRequest request) throws Exception {

		ProductGroupOrder returnVal = new ProductGroupOrder();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("groupOrderId")) {
returnVal.setGroupOrderId(request.getParameter("groupOrderId"));
}

		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
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
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("reqOrderQty"))  {
String buf = request.getParameter("reqOrderQty");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReqOrderQty(bd);
}
		if(paramMap.containsKey("soldOrderQty"))  {
String buf = request.getParameter("soldOrderQty");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSoldOrderQty(bd);
}
		if(paramMap.containsKey("jobId"))  {
returnVal.setJobId(request.getParameter("jobId"));
}
return returnVal;

}
}
