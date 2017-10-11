package com.skytala.eCommerce.domain.product.relations.productAssoc.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productAssoc.model.ProductAssoc;

public class ProductAssocMapper  {


	public static Map<String, Object> map(ProductAssoc productassoc) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productassoc.getProductId() != null ){
			returnVal.put("productId",productassoc.getProductId());
}

		if(productassoc.getProductIdTo() != null ){
			returnVal.put("productIdTo",productassoc.getProductIdTo());
}

		if(productassoc.getProductAssocTypeId() != null ){
			returnVal.put("productAssocTypeId",productassoc.getProductAssocTypeId());
}

		if(productassoc.getFromDate() != null ){
			returnVal.put("fromDate",productassoc.getFromDate());
}

		if(productassoc.getThruDate() != null ){
			returnVal.put("thruDate",productassoc.getThruDate());
}

		if(productassoc.getSequenceNum() != null ){
			returnVal.put("sequenceNum",productassoc.getSequenceNum());
}

		if(productassoc.getReason() != null ){
			returnVal.put("reason",productassoc.getReason());
}

		if(productassoc.getQuantity() != null ){
			returnVal.put("quantity",productassoc.getQuantity());
}

		if(productassoc.getScrapFactor() != null ){
			returnVal.put("scrapFactor",productassoc.getScrapFactor());
}

		if(productassoc.getInstruction() != null ){
			returnVal.put("instruction",productassoc.getInstruction());
}

		if(productassoc.getRoutingWorkEffortId() != null ){
			returnVal.put("routingWorkEffortId",productassoc.getRoutingWorkEffortId());
}

		if(productassoc.getEstimateCalcMethod() != null ){
			returnVal.put("estimateCalcMethod",productassoc.getEstimateCalcMethod());
}

		if(productassoc.getRecurrenceInfoId() != null ){
			returnVal.put("recurrenceInfoId",productassoc.getRecurrenceInfoId());
}

		return returnVal;
}


	public static ProductAssoc map(Map<String, Object> fields) {

		ProductAssoc returnVal = new ProductAssoc();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productIdTo") != null) {
			returnVal.setProductIdTo((String) fields.get("productIdTo"));
}

		if(fields.get("productAssocTypeId") != null) {
			returnVal.setProductAssocTypeId((String) fields.get("productAssocTypeId"));
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

		if(fields.get("reason") != null) {
			returnVal.setReason((String) fields.get("reason"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("scrapFactor") != null) {
			returnVal.setScrapFactor((BigDecimal) fields.get("scrapFactor"));
}

		if(fields.get("instruction") != null) {
			returnVal.setInstruction((String) fields.get("instruction"));
}

		if(fields.get("routingWorkEffortId") != null) {
			returnVal.setRoutingWorkEffortId((String) fields.get("routingWorkEffortId"));
}

		if(fields.get("estimateCalcMethod") != null) {
			returnVal.setEstimateCalcMethod((String) fields.get("estimateCalcMethod"));
}

		if(fields.get("recurrenceInfoId") != null) {
			returnVal.setRecurrenceInfoId((String) fields.get("recurrenceInfoId"));
}


		return returnVal;
 } 
	public static ProductAssoc mapstrstr(Map<String, String> fields) throws Exception {

		ProductAssoc returnVal = new ProductAssoc();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productIdTo") != null) {
			returnVal.setProductIdTo((String) fields.get("productIdTo"));
}

		if(fields.get("productAssocTypeId") != null) {
			returnVal.setProductAssocTypeId((String) fields.get("productAssocTypeId"));
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

		if(fields.get("reason") != null) {
			returnVal.setReason((String) fields.get("reason"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("scrapFactor") != null) {
String buf;
buf = fields.get("scrapFactor");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setScrapFactor(bd);
}

		if(fields.get("instruction") != null) {
			returnVal.setInstruction((String) fields.get("instruction"));
}

		if(fields.get("routingWorkEffortId") != null) {
			returnVal.setRoutingWorkEffortId((String) fields.get("routingWorkEffortId"));
}

		if(fields.get("estimateCalcMethod") != null) {
			returnVal.setEstimateCalcMethod((String) fields.get("estimateCalcMethod"));
}

		if(fields.get("recurrenceInfoId") != null) {
			returnVal.setRecurrenceInfoId((String) fields.get("recurrenceInfoId"));
}


		return returnVal;
 } 
	public static ProductAssoc map(GenericValue val) {

ProductAssoc returnVal = new ProductAssoc();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductIdTo(val.getString("productIdTo"));
		returnVal.setProductAssocTypeId(val.getString("productAssocTypeId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setReason(val.getString("reason"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setScrapFactor(val.getBigDecimal("scrapFactor"));
		returnVal.setInstruction(val.getString("instruction"));
		returnVal.setRoutingWorkEffortId(val.getString("routingWorkEffortId"));
		returnVal.setEstimateCalcMethod(val.getString("estimateCalcMethod"));
		returnVal.setRecurrenceInfoId(val.getString("recurrenceInfoId"));


return returnVal;

}

public static ProductAssoc map(HttpServletRequest request) throws Exception {

		ProductAssoc returnVal = new ProductAssoc();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("productIdTo"))  {
returnVal.setProductIdTo(request.getParameter("productIdTo"));
}
		if(paramMap.containsKey("productAssocTypeId"))  {
returnVal.setProductAssocTypeId(request.getParameter("productAssocTypeId"));
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
		if(paramMap.containsKey("reason"))  {
returnVal.setReason(request.getParameter("reason"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("scrapFactor"))  {
String buf = request.getParameter("scrapFactor");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setScrapFactor(bd);
}
		if(paramMap.containsKey("instruction"))  {
returnVal.setInstruction(request.getParameter("instruction"));
}
		if(paramMap.containsKey("routingWorkEffortId"))  {
returnVal.setRoutingWorkEffortId(request.getParameter("routingWorkEffortId"));
}
		if(paramMap.containsKey("estimateCalcMethod"))  {
returnVal.setEstimateCalcMethod(request.getParameter("estimateCalcMethod"));
}
		if(paramMap.containsKey("recurrenceInfoId"))  {
returnVal.setRecurrenceInfoId(request.getParameter("recurrenceInfoId"));
}
return returnVal;

}
}
