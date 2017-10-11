package com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStoreFinActSetting.model.ProductStoreFinActSetting;

public class ProductStoreFinActSettingMapper  {


	public static Map<String, Object> map(ProductStoreFinActSetting productstorefinactsetting) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstorefinactsetting.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstorefinactsetting.getProductStoreId());
}

		if(productstorefinactsetting.getFinAccountTypeId() != null ){
			returnVal.put("finAccountTypeId",productstorefinactsetting.getFinAccountTypeId());
}

		if(productstorefinactsetting.getRequirePinCode() != null ){
			returnVal.put("requirePinCode",productstorefinactsetting.getRequirePinCode());
}

		if(productstorefinactsetting.getValidateGCFinAcct() != null ){
			returnVal.put("validateGCFinAcct",productstorefinactsetting.getValidateGCFinAcct());
}

		if(productstorefinactsetting.getAccountCodeLength() != null ){
			returnVal.put("accountCodeLength",productstorefinactsetting.getAccountCodeLength());
}

		if(productstorefinactsetting.getPinCodeLength() != null ){
			returnVal.put("pinCodeLength",productstorefinactsetting.getPinCodeLength());
}

		if(productstorefinactsetting.getAccountValidDays() != null ){
			returnVal.put("accountValidDays",productstorefinactsetting.getAccountValidDays());
}

		if(productstorefinactsetting.getAuthValidDays() != null ){
			returnVal.put("authValidDays",productstorefinactsetting.getAuthValidDays());
}

		if(productstorefinactsetting.getPurchaseSurveyId() != null ){
			returnVal.put("purchaseSurveyId",productstorefinactsetting.getPurchaseSurveyId());
}

		if(productstorefinactsetting.getPurchSurveySendTo() != null ){
			returnVal.put("purchSurveySendTo",productstorefinactsetting.getPurchSurveySendTo());
}

		if(productstorefinactsetting.getPurchSurveyCopyMe() != null ){
			returnVal.put("purchSurveyCopyMe",productstorefinactsetting.getPurchSurveyCopyMe());
}

		if(productstorefinactsetting.getAllowAuthToNegative() != null ){
			returnVal.put("allowAuthToNegative",productstorefinactsetting.getAllowAuthToNegative());
}

		if(productstorefinactsetting.getMinBalance() != null ){
			returnVal.put("minBalance",productstorefinactsetting.getMinBalance());
}

		if(productstorefinactsetting.getReplenishThreshold() != null ){
			returnVal.put("replenishThreshold",productstorefinactsetting.getReplenishThreshold());
}

		if(productstorefinactsetting.getReplenishMethodEnumId() != null ){
			returnVal.put("replenishMethodEnumId",productstorefinactsetting.getReplenishMethodEnumId());
}

		return returnVal;
}


	public static ProductStoreFinActSetting map(Map<String, Object> fields) {

		ProductStoreFinActSetting returnVal = new ProductStoreFinActSetting();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("finAccountTypeId") != null) {
			returnVal.setFinAccountTypeId((String) fields.get("finAccountTypeId"));
}

		if(fields.get("requirePinCode") != null) {
			returnVal.setRequirePinCode((boolean) fields.get("requirePinCode"));
}

		if(fields.get("validateGCFinAcct") != null) {
			returnVal.setValidateGCFinAcct((boolean) fields.get("validateGCFinAcct"));
}

		if(fields.get("accountCodeLength") != null) {
			returnVal.setAccountCodeLength((long) fields.get("accountCodeLength"));
}

		if(fields.get("pinCodeLength") != null) {
			returnVal.setPinCodeLength((long) fields.get("pinCodeLength"));
}

		if(fields.get("accountValidDays") != null) {
			returnVal.setAccountValidDays((long) fields.get("accountValidDays"));
}

		if(fields.get("authValidDays") != null) {
			returnVal.setAuthValidDays((long) fields.get("authValidDays"));
}

		if(fields.get("purchaseSurveyId") != null) {
			returnVal.setPurchaseSurveyId((String) fields.get("purchaseSurveyId"));
}

		if(fields.get("purchSurveySendTo") != null) {
			returnVal.setPurchSurveySendTo((String) fields.get("purchSurveySendTo"));
}

		if(fields.get("purchSurveyCopyMe") != null) {
			returnVal.setPurchSurveyCopyMe((String) fields.get("purchSurveyCopyMe"));
}

		if(fields.get("allowAuthToNegative") != null) {
			returnVal.setAllowAuthToNegative((boolean) fields.get("allowAuthToNegative"));
}

		if(fields.get("minBalance") != null) {
			returnVal.setMinBalance((BigDecimal) fields.get("minBalance"));
}

		if(fields.get("replenishThreshold") != null) {
			returnVal.setReplenishThreshold((BigDecimal) fields.get("replenishThreshold"));
}

		if(fields.get("replenishMethodEnumId") != null) {
			returnVal.setReplenishMethodEnumId((String) fields.get("replenishMethodEnumId"));
}


		return returnVal;
 } 
	public static ProductStoreFinActSetting mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreFinActSetting returnVal = new ProductStoreFinActSetting();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("finAccountTypeId") != null) {
			returnVal.setFinAccountTypeId((String) fields.get("finAccountTypeId"));
}

		if(fields.get("requirePinCode") != null) {
String buf;
buf = fields.get("requirePinCode");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequirePinCode(ibuf);
}

		if(fields.get("validateGCFinAcct") != null) {
String buf;
buf = fields.get("validateGCFinAcct");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setValidateGCFinAcct(ibuf);
}

		if(fields.get("accountCodeLength") != null) {
String buf;
buf = fields.get("accountCodeLength");
long ibuf = Long.parseLong(buf);
			returnVal.setAccountCodeLength(ibuf);
}

		if(fields.get("pinCodeLength") != null) {
String buf;
buf = fields.get("pinCodeLength");
long ibuf = Long.parseLong(buf);
			returnVal.setPinCodeLength(ibuf);
}

		if(fields.get("accountValidDays") != null) {
String buf;
buf = fields.get("accountValidDays");
long ibuf = Long.parseLong(buf);
			returnVal.setAccountValidDays(ibuf);
}

		if(fields.get("authValidDays") != null) {
String buf;
buf = fields.get("authValidDays");
long ibuf = Long.parseLong(buf);
			returnVal.setAuthValidDays(ibuf);
}

		if(fields.get("purchaseSurveyId") != null) {
			returnVal.setPurchaseSurveyId((String) fields.get("purchaseSurveyId"));
}

		if(fields.get("purchSurveySendTo") != null) {
			returnVal.setPurchSurveySendTo((String) fields.get("purchSurveySendTo"));
}

		if(fields.get("purchSurveyCopyMe") != null) {
			returnVal.setPurchSurveyCopyMe((String) fields.get("purchSurveyCopyMe"));
}

		if(fields.get("allowAuthToNegative") != null) {
String buf;
buf = fields.get("allowAuthToNegative");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAllowAuthToNegative(ibuf);
}

		if(fields.get("minBalance") != null) {
String buf;
buf = fields.get("minBalance");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinBalance(bd);
}

		if(fields.get("replenishThreshold") != null) {
String buf;
buf = fields.get("replenishThreshold");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReplenishThreshold(bd);
}

		if(fields.get("replenishMethodEnumId") != null) {
			returnVal.setReplenishMethodEnumId((String) fields.get("replenishMethodEnumId"));
}


		return returnVal;
 } 
	public static ProductStoreFinActSetting map(GenericValue val) {

ProductStoreFinActSetting returnVal = new ProductStoreFinActSetting();
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setFinAccountTypeId(val.getString("finAccountTypeId"));
		returnVal.setRequirePinCode(val.getBoolean("requirePinCode"));
		returnVal.setValidateGCFinAcct(val.getBoolean("validateGCFinAcct"));
		returnVal.setAccountCodeLength(val.getLong("accountCodeLength"));
		returnVal.setPinCodeLength(val.getLong("pinCodeLength"));
		returnVal.setAccountValidDays(val.getLong("accountValidDays"));
		returnVal.setAuthValidDays(val.getLong("authValidDays"));
		returnVal.setPurchaseSurveyId(val.getString("purchaseSurveyId"));
		returnVal.setPurchSurveySendTo(val.getString("purchSurveySendTo"));
		returnVal.setPurchSurveyCopyMe(val.getString("purchSurveyCopyMe"));
		returnVal.setAllowAuthToNegative(val.getBoolean("allowAuthToNegative"));
		returnVal.setMinBalance(val.getBigDecimal("minBalance"));
		returnVal.setReplenishThreshold(val.getBigDecimal("replenishThreshold"));
		returnVal.setReplenishMethodEnumId(val.getString("replenishMethodEnumId"));


return returnVal;

}

public static ProductStoreFinActSetting map(HttpServletRequest request) throws Exception {

		ProductStoreFinActSetting returnVal = new ProductStoreFinActSetting();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreId")) {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}

		if(paramMap.containsKey("finAccountTypeId"))  {
returnVal.setFinAccountTypeId(request.getParameter("finAccountTypeId"));
}
		if(paramMap.containsKey("requirePinCode"))  {
String buf = request.getParameter("requirePinCode");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setRequirePinCode(ibuf);
}
		if(paramMap.containsKey("validateGCFinAcct"))  {
String buf = request.getParameter("validateGCFinAcct");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setValidateGCFinAcct(ibuf);
}
		if(paramMap.containsKey("accountCodeLength"))  {
String buf = request.getParameter("accountCodeLength");
Long ibuf = Long.parseLong(buf);
returnVal.setAccountCodeLength(ibuf);
}
		if(paramMap.containsKey("pinCodeLength"))  {
String buf = request.getParameter("pinCodeLength");
Long ibuf = Long.parseLong(buf);
returnVal.setPinCodeLength(ibuf);
}
		if(paramMap.containsKey("accountValidDays"))  {
String buf = request.getParameter("accountValidDays");
Long ibuf = Long.parseLong(buf);
returnVal.setAccountValidDays(ibuf);
}
		if(paramMap.containsKey("authValidDays"))  {
String buf = request.getParameter("authValidDays");
Long ibuf = Long.parseLong(buf);
returnVal.setAuthValidDays(ibuf);
}
		if(paramMap.containsKey("purchaseSurveyId"))  {
returnVal.setPurchaseSurveyId(request.getParameter("purchaseSurveyId"));
}
		if(paramMap.containsKey("purchSurveySendTo"))  {
returnVal.setPurchSurveySendTo(request.getParameter("purchSurveySendTo"));
}
		if(paramMap.containsKey("purchSurveyCopyMe"))  {
returnVal.setPurchSurveyCopyMe(request.getParameter("purchSurveyCopyMe"));
}
		if(paramMap.containsKey("allowAuthToNegative"))  {
String buf = request.getParameter("allowAuthToNegative");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAllowAuthToNegative(ibuf);
}
		if(paramMap.containsKey("minBalance"))  {
String buf = request.getParameter("minBalance");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinBalance(bd);
}
		if(paramMap.containsKey("replenishThreshold"))  {
String buf = request.getParameter("replenishThreshold");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReplenishThreshold(bd);
}
		if(paramMap.containsKey("replenishMethodEnumId"))  {
returnVal.setReplenishMethodEnumId(request.getParameter("replenishMethodEnumId"));
}
return returnVal;

}
}
