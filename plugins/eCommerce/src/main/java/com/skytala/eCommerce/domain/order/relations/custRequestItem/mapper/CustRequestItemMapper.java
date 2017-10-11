package com.skytala.eCommerce.domain.order.relations.custRequestItem.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestItem.model.CustRequestItem;

public class CustRequestItemMapper  {


	public static Map<String, Object> map(CustRequestItem custrequestitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequestitem.getCustRequestId() != null ){
			returnVal.put("custRequestId",custrequestitem.getCustRequestId());
}

		if(custrequestitem.getCustRequestItemSeqId() != null ){
			returnVal.put("custRequestItemSeqId",custrequestitem.getCustRequestItemSeqId());
}

		if(custrequestitem.getCustRequestResolutionId() != null ){
			returnVal.put("custRequestResolutionId",custrequestitem.getCustRequestResolutionId());
}

		if(custrequestitem.getStatusId() != null ){
			returnVal.put("statusId",custrequestitem.getStatusId());
}

		if(custrequestitem.getPriority() != null ){
			returnVal.put("priority",custrequestitem.getPriority());
}

		if(custrequestitem.getSequenceNum() != null ){
			returnVal.put("sequenceNum",custrequestitem.getSequenceNum());
}

		if(custrequestitem.getRequiredByDate() != null ){
			returnVal.put("requiredByDate",custrequestitem.getRequiredByDate());
}

		if(custrequestitem.getProductId() != null ){
			returnVal.put("productId",custrequestitem.getProductId());
}

		if(custrequestitem.getQuantity() != null ){
			returnVal.put("quantity",custrequestitem.getQuantity());
}

		if(custrequestitem.getSelectedAmount() != null ){
			returnVal.put("selectedAmount",custrequestitem.getSelectedAmount());
}

		if(custrequestitem.getMaximumAmount() != null ){
			returnVal.put("maximumAmount",custrequestitem.getMaximumAmount());
}

		if(custrequestitem.getReservStart() != null ){
			returnVal.put("reservStart",custrequestitem.getReservStart());
}

		if(custrequestitem.getReservLength() != null ){
			returnVal.put("reservLength",custrequestitem.getReservLength());
}

		if(custrequestitem.getReservPersons() != null ){
			returnVal.put("reservPersons",custrequestitem.getReservPersons());
}

		if(custrequestitem.getConfigId() != null ){
			returnVal.put("configId",custrequestitem.getConfigId());
}

		if(custrequestitem.getDescription() != null ){
			returnVal.put("description",custrequestitem.getDescription());
}

		if(custrequestitem.getStory() != null ){
			returnVal.put("story",custrequestitem.getStory());
}

		return returnVal;
}


	public static CustRequestItem map(Map<String, Object> fields) {

		CustRequestItem returnVal = new CustRequestItem();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestItemSeqId") != null) {
			returnVal.setCustRequestItemSeqId((String) fields.get("custRequestItemSeqId"));
}

		if(fields.get("custRequestResolutionId") != null) {
			returnVal.setCustRequestResolutionId((String) fields.get("custRequestResolutionId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("priority") != null) {
			returnVal.setPriority((long) fields.get("priority"));
}

		if(fields.get("sequenceNum") != null) {
			returnVal.setSequenceNum((long) fields.get("sequenceNum"));
}

		if(fields.get("requiredByDate") != null) {
			returnVal.setRequiredByDate((Timestamp) fields.get("requiredByDate"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("selectedAmount") != null) {
			returnVal.setSelectedAmount((BigDecimal) fields.get("selectedAmount"));
}

		if(fields.get("maximumAmount") != null) {
			returnVal.setMaximumAmount((BigDecimal) fields.get("maximumAmount"));
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

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("story") != null) {
			returnVal.setStory((String) fields.get("story"));
}


		return returnVal;
 } 
	public static CustRequestItem mapstrstr(Map<String, String> fields) throws Exception {

		CustRequestItem returnVal = new CustRequestItem();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestItemSeqId") != null) {
			returnVal.setCustRequestItemSeqId((String) fields.get("custRequestItemSeqId"));
}

		if(fields.get("custRequestResolutionId") != null) {
			returnVal.setCustRequestResolutionId((String) fields.get("custRequestResolutionId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("priority") != null) {
String buf;
buf = fields.get("priority");
long ibuf = Long.parseLong(buf);
			returnVal.setPriority(ibuf);
}

		if(fields.get("sequenceNum") != null) {
String buf;
buf = fields.get("sequenceNum");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNum(ibuf);
}

		if(fields.get("requiredByDate") != null) {
String buf = fields.get("requiredByDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setRequiredByDate(ibuf);
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
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

		if(fields.get("maximumAmount") != null) {
String buf;
buf = fields.get("maximumAmount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaximumAmount(bd);
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

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("story") != null) {
			returnVal.setStory((String) fields.get("story"));
}


		return returnVal;
 } 
	public static CustRequestItem map(GenericValue val) {

CustRequestItem returnVal = new CustRequestItem();
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setCustRequestItemSeqId(val.getString("custRequestItemSeqId"));
		returnVal.setCustRequestResolutionId(val.getString("custRequestResolutionId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setPriority(val.getLong("priority"));
		returnVal.setSequenceNum(val.getLong("sequenceNum"));
		returnVal.setRequiredByDate(val.getTimestamp("requiredByDate"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setSelectedAmount(val.getBigDecimal("selectedAmount"));
		returnVal.setMaximumAmount(val.getBigDecimal("maximumAmount"));
		returnVal.setReservStart(val.getTimestamp("reservStart"));
		returnVal.setReservLength(val.getBigDecimal("reservLength"));
		returnVal.setReservPersons(val.getBigDecimal("reservPersons"));
		returnVal.setConfigId(val.getString("configId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setStory(val.getString("story"));


return returnVal;

}

public static CustRequestItem map(HttpServletRequest request) throws Exception {

		CustRequestItem returnVal = new CustRequestItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestId")) {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
}

		if(paramMap.containsKey("custRequestItemSeqId"))  {
returnVal.setCustRequestItemSeqId(request.getParameter("custRequestItemSeqId"));
}
		if(paramMap.containsKey("custRequestResolutionId"))  {
returnVal.setCustRequestResolutionId(request.getParameter("custRequestResolutionId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("priority"))  {
String buf = request.getParameter("priority");
Long ibuf = Long.parseLong(buf);
returnVal.setPriority(ibuf);
}
		if(paramMap.containsKey("sequenceNum"))  {
String buf = request.getParameter("sequenceNum");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNum(ibuf);
}
		if(paramMap.containsKey("requiredByDate"))  {
String buf = request.getParameter("requiredByDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setRequiredByDate(ibuf);
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
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
		if(paramMap.containsKey("maximumAmount"))  {
String buf = request.getParameter("maximumAmount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaximumAmount(bd);
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
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("story"))  {
returnVal.setStory(request.getParameter("story"));
}
return returnVal;

}
}
