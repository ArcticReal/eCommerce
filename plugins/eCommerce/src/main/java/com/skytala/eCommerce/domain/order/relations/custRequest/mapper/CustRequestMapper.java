package com.skytala.eCommerce.domain.order.relations.custRequest.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.CustRequest;

public class CustRequestMapper  {


	public static Map<String, Object> map(CustRequest custrequest) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(custrequest.getCustRequestId() != null ){
			returnVal.put("custRequestId",custrequest.getCustRequestId());
}

		if(custrequest.getCustRequestTypeId() != null ){
			returnVal.put("custRequestTypeId",custrequest.getCustRequestTypeId());
}

		if(custrequest.getCustRequestCategoryId() != null ){
			returnVal.put("custRequestCategoryId",custrequest.getCustRequestCategoryId());
}

		if(custrequest.getStatusId() != null ){
			returnVal.put("statusId",custrequest.getStatusId());
}

		if(custrequest.getFromPartyId() != null ){
			returnVal.put("fromPartyId",custrequest.getFromPartyId());
}

		if(custrequest.getPriority() != null ){
			returnVal.put("priority",custrequest.getPriority());
}

		if(custrequest.getCustRequestDate() != null ){
			returnVal.put("custRequestDate",custrequest.getCustRequestDate());
}

		if(custrequest.getResponseRequiredDate() != null ){
			returnVal.put("responseRequiredDate",custrequest.getResponseRequiredDate());
}

		if(custrequest.getCustRequestName() != null ){
			returnVal.put("custRequestName",custrequest.getCustRequestName());
}

		if(custrequest.getDescription() != null ){
			returnVal.put("description",custrequest.getDescription());
}

		if(custrequest.getMaximumAmountUomId() != null ){
			returnVal.put("maximumAmountUomId",custrequest.getMaximumAmountUomId());
}

		if(custrequest.getProductStoreId() != null ){
			returnVal.put("productStoreId",custrequest.getProductStoreId());
}

		if(custrequest.getSalesChannelEnumId() != null ){
			returnVal.put("salesChannelEnumId",custrequest.getSalesChannelEnumId());
}

		if(custrequest.getFulfillContactMechId() != null ){
			returnVal.put("fulfillContactMechId",custrequest.getFulfillContactMechId());
}

		if(custrequest.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",custrequest.getCurrencyUomId());
}

		if(custrequest.getOpenDateTime() != null ){
			returnVal.put("openDateTime",custrequest.getOpenDateTime());
}

		if(custrequest.getClosedDateTime() != null ){
			returnVal.put("closedDateTime",custrequest.getClosedDateTime());
}

		if(custrequest.getInternalComment() != null ){
			returnVal.put("internalComment",custrequest.getInternalComment());
}

		if(custrequest.getReason() != null ){
			returnVal.put("reason",custrequest.getReason());
}

		if(custrequest.getCreatedDate() != null ){
			returnVal.put("createdDate",custrequest.getCreatedDate());
}

		if(custrequest.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",custrequest.getCreatedByUserLogin());
}

		if(custrequest.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",custrequest.getLastModifiedDate());
}

		if(custrequest.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",custrequest.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static CustRequest map(Map<String, Object> fields) {

		CustRequest returnVal = new CustRequest();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestTypeId") != null) {
			returnVal.setCustRequestTypeId((String) fields.get("custRequestTypeId"));
}

		if(fields.get("custRequestCategoryId") != null) {
			returnVal.setCustRequestCategoryId((String) fields.get("custRequestCategoryId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("fromPartyId") != null) {
			returnVal.setFromPartyId((String) fields.get("fromPartyId"));
}

		if(fields.get("priority") != null) {
			returnVal.setPriority((long) fields.get("priority"));
}

		if(fields.get("custRequestDate") != null) {
			returnVal.setCustRequestDate((Timestamp) fields.get("custRequestDate"));
}

		if(fields.get("responseRequiredDate") != null) {
			returnVal.setResponseRequiredDate((Timestamp) fields.get("responseRequiredDate"));
}

		if(fields.get("custRequestName") != null) {
			returnVal.setCustRequestName((String) fields.get("custRequestName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("maximumAmountUomId") != null) {
			returnVal.setMaximumAmountUomId((String) fields.get("maximumAmountUomId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("salesChannelEnumId") != null) {
			returnVal.setSalesChannelEnumId((String) fields.get("salesChannelEnumId"));
}

		if(fields.get("fulfillContactMechId") != null) {
			returnVal.setFulfillContactMechId((String) fields.get("fulfillContactMechId"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("openDateTime") != null) {
			returnVal.setOpenDateTime((Timestamp) fields.get("openDateTime"));
}

		if(fields.get("closedDateTime") != null) {
			returnVal.setClosedDateTime((Timestamp) fields.get("closedDateTime"));
}

		if(fields.get("internalComment") != null) {
			returnVal.setInternalComment((String) fields.get("internalComment"));
}

		if(fields.get("reason") != null) {
			returnVal.setReason((String) fields.get("reason"));
}

		if(fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static CustRequest mapstrstr(Map<String, String> fields) throws Exception {

		CustRequest returnVal = new CustRequest();

		if(fields.get("custRequestId") != null) {
			returnVal.setCustRequestId((String) fields.get("custRequestId"));
}

		if(fields.get("custRequestTypeId") != null) {
			returnVal.setCustRequestTypeId((String) fields.get("custRequestTypeId"));
}

		if(fields.get("custRequestCategoryId") != null) {
			returnVal.setCustRequestCategoryId((String) fields.get("custRequestCategoryId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("fromPartyId") != null) {
			returnVal.setFromPartyId((String) fields.get("fromPartyId"));
}

		if(fields.get("priority") != null) {
String buf;
buf = fields.get("priority");
long ibuf = Long.parseLong(buf);
			returnVal.setPriority(ibuf);
}

		if(fields.get("custRequestDate") != null) {
String buf = fields.get("custRequestDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCustRequestDate(ibuf);
}

		if(fields.get("responseRequiredDate") != null) {
String buf = fields.get("responseRequiredDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setResponseRequiredDate(ibuf);
}

		if(fields.get("custRequestName") != null) {
			returnVal.setCustRequestName((String) fields.get("custRequestName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("maximumAmountUomId") != null) {
			returnVal.setMaximumAmountUomId((String) fields.get("maximumAmountUomId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("salesChannelEnumId") != null) {
			returnVal.setSalesChannelEnumId((String) fields.get("salesChannelEnumId"));
}

		if(fields.get("fulfillContactMechId") != null) {
			returnVal.setFulfillContactMechId((String) fields.get("fulfillContactMechId"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("openDateTime") != null) {
String buf = fields.get("openDateTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setOpenDateTime(ibuf);
}

		if(fields.get("closedDateTime") != null) {
String buf = fields.get("closedDateTime");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setClosedDateTime(ibuf);
}

		if(fields.get("internalComment") != null) {
			returnVal.setInternalComment((String) fields.get("internalComment"));
}

		if(fields.get("reason") != null) {
			returnVal.setReason((String) fields.get("reason"));
}

		if(fields.get("createdDate") != null) {
String buf = fields.get("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static CustRequest map(GenericValue val) {

CustRequest returnVal = new CustRequest();
		returnVal.setCustRequestId(val.getString("custRequestId"));
		returnVal.setCustRequestTypeId(val.getString("custRequestTypeId"));
		returnVal.setCustRequestCategoryId(val.getString("custRequestCategoryId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setFromPartyId(val.getString("fromPartyId"));
		returnVal.setPriority(val.getLong("priority"));
		returnVal.setCustRequestDate(val.getTimestamp("custRequestDate"));
		returnVal.setResponseRequiredDate(val.getTimestamp("responseRequiredDate"));
		returnVal.setCustRequestName(val.getString("custRequestName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setMaximumAmountUomId(val.getString("maximumAmountUomId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setSalesChannelEnumId(val.getString("salesChannelEnumId"));
		returnVal.setFulfillContactMechId(val.getString("fulfillContactMechId"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setOpenDateTime(val.getTimestamp("openDateTime"));
		returnVal.setClosedDateTime(val.getTimestamp("closedDateTime"));
		returnVal.setInternalComment(val.getString("internalComment"));
		returnVal.setReason(val.getString("reason"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static CustRequest map(HttpServletRequest request) throws Exception {

		CustRequest returnVal = new CustRequest();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("custRequestId")) {
returnVal.setCustRequestId(request.getParameter("custRequestId"));
}

		if(paramMap.containsKey("custRequestTypeId"))  {
returnVal.setCustRequestTypeId(request.getParameter("custRequestTypeId"));
}
		if(paramMap.containsKey("custRequestCategoryId"))  {
returnVal.setCustRequestCategoryId(request.getParameter("custRequestCategoryId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("fromPartyId"))  {
returnVal.setFromPartyId(request.getParameter("fromPartyId"));
}
		if(paramMap.containsKey("priority"))  {
String buf = request.getParameter("priority");
Long ibuf = Long.parseLong(buf);
returnVal.setPriority(ibuf);
}
		if(paramMap.containsKey("custRequestDate"))  {
String buf = request.getParameter("custRequestDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCustRequestDate(ibuf);
}
		if(paramMap.containsKey("responseRequiredDate"))  {
String buf = request.getParameter("responseRequiredDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setResponseRequiredDate(ibuf);
}
		if(paramMap.containsKey("custRequestName"))  {
returnVal.setCustRequestName(request.getParameter("custRequestName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("maximumAmountUomId"))  {
returnVal.setMaximumAmountUomId(request.getParameter("maximumAmountUomId"));
}
		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}
		if(paramMap.containsKey("salesChannelEnumId"))  {
returnVal.setSalesChannelEnumId(request.getParameter("salesChannelEnumId"));
}
		if(paramMap.containsKey("fulfillContactMechId"))  {
returnVal.setFulfillContactMechId(request.getParameter("fulfillContactMechId"));
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("openDateTime"))  {
String buf = request.getParameter("openDateTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setOpenDateTime(ibuf);
}
		if(paramMap.containsKey("closedDateTime"))  {
String buf = request.getParameter("closedDateTime");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setClosedDateTime(ibuf);
}
		if(paramMap.containsKey("internalComment"))  {
returnVal.setInternalComment(request.getParameter("internalComment"));
}
		if(paramMap.containsKey("reason"))  {
returnVal.setReason(request.getParameter("reason"));
}
		if(paramMap.containsKey("createdDate"))  {
String buf = request.getParameter("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
