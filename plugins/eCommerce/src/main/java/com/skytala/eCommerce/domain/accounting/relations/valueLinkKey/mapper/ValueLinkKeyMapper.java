package com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.model.ValueLinkKey;

public class ValueLinkKeyMapper  {


	public static Map<String, Object> map(ValueLinkKey valuelinkkey) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(valuelinkkey.getMerchantId() != null ){
			returnVal.put("merchantId",valuelinkkey.getMerchantId());
}

		if(valuelinkkey.getPublicKey() != null ){
			returnVal.put("publicKey",valuelinkkey.getPublicKey());
}

		if(valuelinkkey.getPrivateKey() != null ){
			returnVal.put("privateKey",valuelinkkey.getPrivateKey());
}

		if(valuelinkkey.getExchangeKey() != null ){
			returnVal.put("exchangeKey",valuelinkkey.getExchangeKey());
}

		if(valuelinkkey.getWorkingKey() != null ){
			returnVal.put("workingKey",valuelinkkey.getWorkingKey());
}

		if(valuelinkkey.getWorkingKeyIndex() != null ){
			returnVal.put("workingKeyIndex",valuelinkkey.getWorkingKeyIndex());
}

		if(valuelinkkey.getLastWorkingKey() != null ){
			returnVal.put("lastWorkingKey",valuelinkkey.getLastWorkingKey());
}

		if(valuelinkkey.getCreatedDate() != null ){
			returnVal.put("createdDate",valuelinkkey.getCreatedDate());
}

		if(valuelinkkey.getCreatedByTerminal() != null ){
			returnVal.put("createdByTerminal",valuelinkkey.getCreatedByTerminal());
}

		if(valuelinkkey.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",valuelinkkey.getCreatedByUserLogin());
}

		if(valuelinkkey.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",valuelinkkey.getLastModifiedDate());
}

		if(valuelinkkey.getLastModifiedByTerminal() != null ){
			returnVal.put("lastModifiedByTerminal",valuelinkkey.getLastModifiedByTerminal());
}

		if(valuelinkkey.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",valuelinkkey.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static ValueLinkKey map(Map<String, Object> fields) {

		ValueLinkKey returnVal = new ValueLinkKey();

		if(fields.get("merchantId") != null) {
			returnVal.setMerchantId((String) fields.get("merchantId"));
}

		if(fields.get("publicKey") != null) {
			returnVal.setPublicKey((String) fields.get("publicKey"));
}

		if(fields.get("privateKey") != null) {
			returnVal.setPrivateKey((String) fields.get("privateKey"));
}

		if(fields.get("exchangeKey") != null) {
			returnVal.setExchangeKey((String) fields.get("exchangeKey"));
}

		if(fields.get("workingKey") != null) {
			returnVal.setWorkingKey((String) fields.get("workingKey"));
}

		if(fields.get("workingKeyIndex") != null) {
			returnVal.setWorkingKeyIndex((long) fields.get("workingKeyIndex"));
}

		if(fields.get("lastWorkingKey") != null) {
			returnVal.setLastWorkingKey((String) fields.get("lastWorkingKey"));
}

		if(fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
}

		if(fields.get("createdByTerminal") != null) {
			returnVal.setCreatedByTerminal((String) fields.get("createdByTerminal"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("lastModifiedByTerminal") != null) {
			returnVal.setLastModifiedByTerminal((String) fields.get("lastModifiedByTerminal"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static ValueLinkKey mapstrstr(Map<String, String> fields) throws Exception {

		ValueLinkKey returnVal = new ValueLinkKey();

		if(fields.get("merchantId") != null) {
			returnVal.setMerchantId((String) fields.get("merchantId"));
}

		if(fields.get("publicKey") != null) {
			returnVal.setPublicKey((String) fields.get("publicKey"));
}

		if(fields.get("privateKey") != null) {
			returnVal.setPrivateKey((String) fields.get("privateKey"));
}

		if(fields.get("exchangeKey") != null) {
			returnVal.setExchangeKey((String) fields.get("exchangeKey"));
}

		if(fields.get("workingKey") != null) {
			returnVal.setWorkingKey((String) fields.get("workingKey"));
}

		if(fields.get("workingKeyIndex") != null) {
String buf;
buf = fields.get("workingKeyIndex");
long ibuf = Long.parseLong(buf);
			returnVal.setWorkingKeyIndex(ibuf);
}

		if(fields.get("lastWorkingKey") != null) {
			returnVal.setLastWorkingKey((String) fields.get("lastWorkingKey"));
}

		if(fields.get("createdDate") != null) {
String buf = fields.get("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
}

		if(fields.get("createdByTerminal") != null) {
			returnVal.setCreatedByTerminal((String) fields.get("createdByTerminal"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("lastModifiedByTerminal") != null) {
			returnVal.setLastModifiedByTerminal((String) fields.get("lastModifiedByTerminal"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static ValueLinkKey map(GenericValue val) {

ValueLinkKey returnVal = new ValueLinkKey();
		returnVal.setMerchantId(val.getString("merchantId"));
		returnVal.setPublicKey(val.getString("publicKey"));
		returnVal.setPrivateKey(val.getString("privateKey"));
		returnVal.setExchangeKey(val.getString("exchangeKey"));
		returnVal.setWorkingKey(val.getString("workingKey"));
		returnVal.setWorkingKeyIndex(val.getLong("workingKeyIndex"));
		returnVal.setLastWorkingKey(val.getString("lastWorkingKey"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByTerminal(val.getString("createdByTerminal"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByTerminal(val.getString("lastModifiedByTerminal"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static ValueLinkKey map(HttpServletRequest request) throws Exception {

		ValueLinkKey returnVal = new ValueLinkKey();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("merchantId")) {
returnVal.setMerchantId(request.getParameter("merchantId"));
}

		if(paramMap.containsKey("publicKey"))  {
returnVal.setPublicKey(request.getParameter("publicKey"));
}
		if(paramMap.containsKey("privateKey"))  {
returnVal.setPrivateKey(request.getParameter("privateKey"));
}
		if(paramMap.containsKey("exchangeKey"))  {
returnVal.setExchangeKey(request.getParameter("exchangeKey"));
}
		if(paramMap.containsKey("workingKey"))  {
returnVal.setWorkingKey(request.getParameter("workingKey"));
}
		if(paramMap.containsKey("workingKeyIndex"))  {
String buf = request.getParameter("workingKeyIndex");
Long ibuf = Long.parseLong(buf);
returnVal.setWorkingKeyIndex(ibuf);
}
		if(paramMap.containsKey("lastWorkingKey"))  {
returnVal.setLastWorkingKey(request.getParameter("lastWorkingKey"));
}
		if(paramMap.containsKey("createdDate"))  {
String buf = request.getParameter("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDate(ibuf);
}
		if(paramMap.containsKey("createdByTerminal"))  {
returnVal.setCreatedByTerminal(request.getParameter("createdByTerminal"));
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedByTerminal"))  {
returnVal.setLastModifiedByTerminal(request.getParameter("lastModifiedByTerminal"));
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
