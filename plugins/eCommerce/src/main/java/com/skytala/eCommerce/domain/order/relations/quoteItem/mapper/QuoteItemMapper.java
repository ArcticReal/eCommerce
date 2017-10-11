package com.skytala.eCommerce.domain.order.relations.quoteItem.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quoteItem.model.QuoteItem;

public class QuoteItemMapper  {


	public static Map<String, Object> map(QuoteItem quoteitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(quoteitem.getQuoteId() != null ){
			returnVal.put("quoteId",quoteitem.getQuoteId());
}

		if(quoteitem.getQuoteItemSeqId() != null ){
			returnVal.put("quoteItemSeqId",quoteitem.getQuoteItemSeqId());
}

		if(quoteitem.getProductId() != null ){
			returnVal.put("productId",quoteitem.getProductId());
}

		if(quoteitem.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",quoteitem.getProductFeatureId());
}

		if(quoteitem.getDeliverableTypeId() != null ){
			returnVal.put("deliverableTypeId",quoteitem.getDeliverableTypeId());
}

		if(quoteitem.getSkillTypeId() != null ){
			returnVal.put("skillTypeId",quoteitem.getSkillTypeId());
}

		if(quoteitem.getUomId() != null ){
			returnVal.put("uomId",quoteitem.getUomId());
}

		if(quoteitem.getWorkEffortId() != null ){
			returnVal.put("workEffortId",quoteitem.getWorkEffortId());
}

		if(quoteitem.getCustRequestId() != null ){
			returnVal.put("custRequestId",quoteitem.getCustRequestId());
}

		if(quoteitem.getCustRequestItemSeqId() != null ){
			returnVal.put("custRequestItemSeqId",quoteitem.getCustRequestItemSeqId());
}

		if(quoteitem.getQuantity() != null ){
			returnVal.put("quantity",quoteitem.getQuantity());
}

		if(quoteitem.getSelectedAmount() != null ){
			returnVal.put("selectedAmount",quoteitem.getSelectedAmount());
}

		if(quoteitem.getQuoteUnitPrice() != null ){
			returnVal.put("quoteUnitPrice",quoteitem.getQuoteUnitPrice());
}

		if(quoteitem.getReservStart() != null ){
			returnVal.put("reservStart",quoteitem.getReservStart());
}

		if(quoteitem.getReservLength() != null ){
			returnVal.put("reservLength",quoteitem.getReservLength());
}

		if(quoteitem.getReservPersons() != null ){
			returnVal.put("reservPersons",quoteitem.getReservPersons());
}

		if(quoteitem.getConfigId() != null ){
			returnVal.put("configId",quoteitem.getConfigId());
}

		if(quoteitem.getEstimatedDeliveryDate() != null ){
			returnVal.put("estimatedDeliveryDate",quoteitem.getEstimatedDeliveryDate());
}

		if(quoteitem.getComments() != null ){
			returnVal.put("comments",quoteitem.getComments());
}

		if(quoteitem.getIsPromo() != null ){
			returnVal.put("isPromo",quoteitem.getIsPromo());
}

		if(quoteitem.getLeadTimeDays() != null ){
			returnVal.put("leadTimeDays",quoteitem.getLeadTimeDays());
}

		return returnVal;
}


	public static QuoteItem map(Map<String, Object> fields) {

		QuoteItem returnVal = new QuoteItem();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("quoteItemSeqId") != null) {
			returnVal.setQuoteItemSeqId((String) fields.get("quoteItemSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("deliverableTypeId") != null) {
			returnVal.setDeliverableTypeId((String) fields.get("deliverableTypeId"));
}

		if(fields.get("skillTypeId") != null) {
			returnVal.setSkillTypeId((String) fields.get("skillTypeId"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestItemSeqId") != null) {
			returnVal.setCustRequestItemSeqId((String) fields.get("custRequestItemSeqId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("selectedAmount") != null) {
			returnVal.setSelectedAmount((BigDecimal) fields.get("selectedAmount"));
}

		if(fields.get("quoteUnitPrice") != null) {
			returnVal.setQuoteUnitPrice((BigDecimal) fields.get("quoteUnitPrice"));
}

		if(fields.get("reservStart") != null) {
			returnVal.setReservStart((Timestamp) fields.get("reservStart"));
}

		if(fields.get("reservLength") != null) {
			returnVal.setReservLength((BigDecimal) fields.get("reservLength"));
}

		if(fields.get("reservPersons") != null) {
			returnVal.setReservPersons((BigDecimal) fields.get("reservPersons"));
}

		if(fields.get("configId") != null) {
			returnVal.setConfigId((String) fields.get("configId"));
}

		if(fields.get("estimatedDeliveryDate") != null) {
			returnVal.setEstimatedDeliveryDate((Timestamp) fields.get("estimatedDeliveryDate"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("isPromo") != null) {
			returnVal.setIsPromo((boolean) fields.get("isPromo"));
}

		if(fields.get("leadTimeDays") != null) {
			returnVal.setLeadTimeDays((long) fields.get("leadTimeDays"));
}


		return returnVal;
 } 
	public static QuoteItem mapstrstr(Map<String, String> fields) throws Exception {

		QuoteItem returnVal = new QuoteItem();

		if(fields.get("quoteId") != null) {
			returnVal.setQuoteId((String) fields.get("quoteId"));
}

		if(fields.get("quoteItemSeqId") != null) {
			returnVal.setQuoteItemSeqId((String) fields.get("quoteItemSeqId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("deliverableTypeId") != null) {
			returnVal.setDeliverableTypeId((String) fields.get("deliverableTypeId"));
}

		if(fields.get("skillTypeId") != null) {
			returnVal.setSkillTypeId((String) fields.get("skillTypeId"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestItemSeqId") != null) {
			returnVal.setCustRequestItemSeqId((String) fields.get("custRequestItemSeqId"));
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("selectedAmount") != null) {
String buf;
buf = fields.get("selectedAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSelectedAmount(bd);
}

		if(fields.get("quoteUnitPrice") != null) {
String buf;
buf = fields.get("quoteUnitPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuoteUnitPrice(bd);
}

		if(fields.get("reservStart") != null) {
String buf = fields.get("reservStart");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReservStart(ibuf);
}

		if(fields.get("reservLength") != null) {
String buf;
buf = fields.get("reservLength");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservLength(bd);
}

		if(fields.get("reservPersons") != null) {
String buf;
buf = fields.get("reservPersons");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservPersons(bd);
}

		if(fields.get("configId") != null) {
			returnVal.setConfigId((String) fields.get("configId"));
}

		if(fields.get("estimatedDeliveryDate") != null) {
String buf = fields.get("estimatedDeliveryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedDeliveryDate(ibuf);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("isPromo") != null) {
String buf;
buf = fields.get("isPromo");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsPromo(ibuf);
}

		if(fields.get("leadTimeDays") != null) {
String buf;
buf = fields.get("leadTimeDays");
long ibuf = Long.parseLong(buf);
			returnVal.setLeadTimeDays(ibuf);
}


		return returnVal;
 } 
	public static QuoteItem map(GenericValue val) {

QuoteItem returnVal = new QuoteItem();
		returnVal.setQuoteId(val.getString("quoteId"));
		returnVal.setQuoteItemSeqId(val.getString("quoteItemSeqId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setDeliverableTypeId(val.getString("deliverableTypeId"));
		returnVal.setSkillTypeId(val.getString("skillTypeId"));
		returnVal.setUomId(val.getString("uomId"));
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setCustRequestItemSeqId(val.getString("custRequestItemSeqId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setSelectedAmount(val.getBigDecimal("selectedAmount"));
		returnVal.setQuoteUnitPrice(val.getBigDecimal("quoteUnitPrice"));
		returnVal.setReservStart(val.getTimestamp("reservStart"));
		returnVal.setReservLength(val.getBigDecimal("reservLength"));
		returnVal.setReservPersons(val.getBigDecimal("reservPersons"));
		returnVal.setConfigId(val.getString("configId"));
		returnVal.setEstimatedDeliveryDate(val.getTimestamp("estimatedDeliveryDate"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setIsPromo(val.getBoolean("isPromo"));
		returnVal.setLeadTimeDays(val.getLong("leadTimeDays"));


return returnVal;

}

public static QuoteItem map(HttpServletRequest request) throws Exception {

		QuoteItem returnVal = new QuoteItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("quoteId")) {
returnVal.setQuoteId(request.getParameter("quoteId"));
}

		if(paramMap.containsKey("quoteItemSeqId"))  {
returnVal.setQuoteItemSeqId(request.getParameter("quoteItemSeqId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("productFeatureId"))  {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}
		if(paramMap.containsKey("deliverableTypeId"))  {
returnVal.setDeliverableTypeId(request.getParameter("deliverableTypeId"));
}
		if(paramMap.containsKey("skillTypeId"))  {
returnVal.setSkillTypeId(request.getParameter("skillTypeId"));
}
		if(paramMap.containsKey("uomId"))  {
returnVal.setUomId(request.getParameter("uomId"));
}
		if(paramMap.containsKey("workEffortId"))  {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}
		if(paramMap.containsKey("custRequestId"))  {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
}
		if(paramMap.containsKey("custRequestItemSeqId"))  {
returnVal.setCustRequestItemSeqId(request.getParameter("custRequestItemSeqId"));
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("selectedAmount"))  {
String buf = request.getParameter("selectedAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSelectedAmount(bd);
}
		if(paramMap.containsKey("quoteUnitPrice"))  {
String buf = request.getParameter("quoteUnitPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuoteUnitPrice(bd);
}
		if(paramMap.containsKey("reservStart"))  {
String buf = request.getParameter("reservStart");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setReservStart(ibuf);
}
		if(paramMap.containsKey("reservLength"))  {
String buf = request.getParameter("reservLength");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservLength(bd);
}
		if(paramMap.containsKey("reservPersons"))  {
String buf = request.getParameter("reservPersons");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservPersons(bd);
}
		if(paramMap.containsKey("configId"))  {
returnVal.setConfigId(request.getParameter("configId"));
}
		if(paramMap.containsKey("estimatedDeliveryDate"))  {
String buf = request.getParameter("estimatedDeliveryDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedDeliveryDate(ibuf);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("isPromo"))  {
String buf = request.getParameter("isPromo");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIsPromo(ibuf);
}
		if(paramMap.containsKey("leadTimeDays"))  {
String buf = request.getParameter("leadTimeDays");
Long ibuf = Long.parseLong(buf);
returnVal.setLeadTimeDays(ibuf);
}
return returnVal;

}
}
