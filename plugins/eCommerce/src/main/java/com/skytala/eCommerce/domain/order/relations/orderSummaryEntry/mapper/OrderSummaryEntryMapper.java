package com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.orderSummaryEntry.model.OrderSummaryEntry;

public class OrderSummaryEntryMapper  {


	public static Map<String, Object> map(OrderSummaryEntry ordersummaryentry) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(ordersummaryentry.getEntryDate() != null ){
			returnVal.put("entryDate",ordersummaryentry.getEntryDate());
}

		if(ordersummaryentry.getProductId() != null ){
			returnVal.put("productId",ordersummaryentry.getProductId());
}

		if(ordersummaryentry.getFacilityId() != null ){
			returnVal.put("facilityId",ordersummaryentry.getFacilityId());
}

		if(ordersummaryentry.getTotalQuantity() != null ){
			returnVal.put("totalQuantity",ordersummaryentry.getTotalQuantity());
}

		if(ordersummaryentry.getGrossSales() != null ){
			returnVal.put("grossSales",ordersummaryentry.getGrossSales());
}

		if(ordersummaryentry.getProductCost() != null ){
			returnVal.put("productCost",ordersummaryentry.getProductCost());
}

		return returnVal;
}


	public static OrderSummaryEntry map(Map<String, Object> fields) {

		OrderSummaryEntry returnVal = new OrderSummaryEntry();

		if(fields.get("entryDate") != null) {
			returnVal.setEntryDate((Timestamp) fields.get("entryDate"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("totalQuantity") != null) {
			returnVal.setTotalQuantity((BigDecimal) fields.get("totalQuantity"));
}

		if(fields.get("grossSales") != null) {
			returnVal.setGrossSales((BigDecimal) fields.get("grossSales"));
}

		if(fields.get("productCost") != null) {
			returnVal.setProductCost((BigDecimal) fields.get("productCost"));
}


		return returnVal;
 } 
	public static OrderSummaryEntry mapstrstr(Map<String, String> fields) throws Exception {

		OrderSummaryEntry returnVal = new OrderSummaryEntry();

		if(fields.get("entryDate") != null) {
String buf = fields.get("entryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEntryDate(ibuf);
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("totalQuantity") != null) {
String buf;
buf = fields.get("totalQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalQuantity(bd);
}

		if(fields.get("grossSales") != null) {
String buf;
buf = fields.get("grossSales");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setGrossSales(bd);
}

		if(fields.get("productCost") != null) {
String buf;
buf = fields.get("productCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductCost(bd);
}


		return returnVal;
 } 
	public static OrderSummaryEntry map(GenericValue val) {

OrderSummaryEntry returnVal = new OrderSummaryEntry();
		returnVal.setEntryDate(val.getTimestamp("entryDate"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setTotalQuantity(val.getBigDecimal("totalQuantity"));
		returnVal.setGrossSales(val.getBigDecimal("grossSales"));
		returnVal.setProductCost(val.getBigDecimal("productCost"));


return returnVal;

}

public static OrderSummaryEntry map(HttpServletRequest request) throws Exception {

		OrderSummaryEntry returnVal = new OrderSummaryEntry();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("entryDate")) {
			String buf = request.getParameter("entryDate");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEntryDate(ibuf);

}

		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("totalQuantity"))  {
String buf = request.getParameter("totalQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalQuantity(bd);
}
		if(paramMap.containsKey("grossSales"))  {
String buf = request.getParameter("grossSales");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setGrossSales(bd);
}
		if(paramMap.containsKey("productCost"))  {
String buf = request.getParameter("productCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductCost(bd);
}
return returnVal;

}
}
