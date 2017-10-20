package com.skytala.eCommerce.domain.product.relations.product.mapper.subscriptionResource;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.subscriptionResource.ProductSubscriptionResource;

public class ProductSubscriptionResourceMapper  {


	public static Map<String, Object> map(ProductSubscriptionResource productsubscriptionresource) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productsubscriptionresource.getProductId() != null ){
			returnVal.put("productId",productsubscriptionresource.getProductId());
}

		if(productsubscriptionresource.getSubscriptionResourceId() != null ){
			returnVal.put("subscriptionResourceId",productsubscriptionresource.getSubscriptionResourceId());
}

		if(productsubscriptionresource.getFromDate() != null ){
			returnVal.put("fromDate",productsubscriptionresource.getFromDate());
}

		if(productsubscriptionresource.getThruDate() != null ){
			returnVal.put("thruDate",productsubscriptionresource.getThruDate());
}

		if(productsubscriptionresource.getPurchaseFromDate() != null ){
			returnVal.put("purchaseFromDate",productsubscriptionresource.getPurchaseFromDate());
}

		if(productsubscriptionresource.getPurchaseThruDate() != null ){
			returnVal.put("purchaseThruDate",productsubscriptionresource.getPurchaseThruDate());
}

		if(productsubscriptionresource.getMaxLifeTime() != null ){
			returnVal.put("maxLifeTime",productsubscriptionresource.getMaxLifeTime());
}

		if(productsubscriptionresource.getMaxLifeTimeUomId() != null ){
			returnVal.put("maxLifeTimeUomId",productsubscriptionresource.getMaxLifeTimeUomId());
}

		if(productsubscriptionresource.getAvailableTime() != null ){
			returnVal.put("availableTime",productsubscriptionresource.getAvailableTime());
}

		if(productsubscriptionresource.getAvailableTimeUomId() != null ){
			returnVal.put("availableTimeUomId",productsubscriptionresource.getAvailableTimeUomId());
}

		if(productsubscriptionresource.getUseCountLimit() != null ){
			returnVal.put("useCountLimit",productsubscriptionresource.getUseCountLimit());
}

		if(productsubscriptionresource.getUseTime() != null ){
			returnVal.put("useTime",productsubscriptionresource.getUseTime());
}

		if(productsubscriptionresource.getUseTimeUomId() != null ){
			returnVal.put("useTimeUomId",productsubscriptionresource.getUseTimeUomId());
}

		if(productsubscriptionresource.getUseRoleTypeId() != null ){
			returnVal.put("useRoleTypeId",productsubscriptionresource.getUseRoleTypeId());
}

		if(productsubscriptionresource.getAutomaticExtend() != null ){
			returnVal.put("automaticExtend",productsubscriptionresource.getAutomaticExtend());
}

		if(productsubscriptionresource.getCanclAutmExtTime() != null ){
			returnVal.put("canclAutmExtTime",productsubscriptionresource.getCanclAutmExtTime());
}

		if(productsubscriptionresource.getCanclAutmExtTimeUomId() != null ){
			returnVal.put("canclAutmExtTimeUomId",productsubscriptionresource.getCanclAutmExtTimeUomId());
}

		if(productsubscriptionresource.getGracePeriodOnExpiry() != null ){
			returnVal.put("gracePeriodOnExpiry",productsubscriptionresource.getGracePeriodOnExpiry());
}

		if(productsubscriptionresource.getGracePeriodOnExpiryUomId() != null ){
			returnVal.put("gracePeriodOnExpiryUomId",productsubscriptionresource.getGracePeriodOnExpiryUomId());
}

		return returnVal;
}


	public static ProductSubscriptionResource map(Map<String, Object> fields) {

		ProductSubscriptionResource returnVal = new ProductSubscriptionResource();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("subscriptionResourceId") != null) {
			returnVal.setSubscriptionResourceId((String) fields.get("subscriptionResourceId"));
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

		if(fields.get("maxLifeTime") != null) {
			returnVal.setMaxLifeTime((long) fields.get("maxLifeTime"));
}

		if(fields.get("maxLifeTimeUomId") != null) {
			returnVal.setMaxLifeTimeUomId((String) fields.get("maxLifeTimeUomId"));
}

		if(fields.get("availableTime") != null) {
			returnVal.setAvailableTime((long) fields.get("availableTime"));
}

		if(fields.get("availableTimeUomId") != null) {
			returnVal.setAvailableTimeUomId((String) fields.get("availableTimeUomId"));
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

		if(fields.get("automaticExtend") != null) {
			returnVal.setAutomaticExtend((boolean) fields.get("automaticExtend"));
}

		if(fields.get("canclAutmExtTime") != null) {
			returnVal.setCanclAutmExtTime((long) fields.get("canclAutmExtTime"));
}

		if(fields.get("canclAutmExtTimeUomId") != null) {
			returnVal.setCanclAutmExtTimeUomId((String) fields.get("canclAutmExtTimeUomId"));
}

		if(fields.get("gracePeriodOnExpiry") != null) {
			returnVal.setGracePeriodOnExpiry((long) fields.get("gracePeriodOnExpiry"));
}

		if(fields.get("gracePeriodOnExpiryUomId") != null) {
			returnVal.setGracePeriodOnExpiryUomId((String) fields.get("gracePeriodOnExpiryUomId"));
}


		return returnVal;
 } 
	public static ProductSubscriptionResource mapstrstr(Map<String, String> fields) throws Exception {

		ProductSubscriptionResource returnVal = new ProductSubscriptionResource();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("subscriptionResourceId") != null) {
			returnVal.setSubscriptionResourceId((String) fields.get("subscriptionResourceId"));
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

		if(fields.get("maxLifeTime") != null) {
String buf;
buf = fields.get("maxLifeTime");
long ibuf = Long.parseLong(buf);
			returnVal.setMaxLifeTime(ibuf);
}

		if(fields.get("maxLifeTimeUomId") != null) {
			returnVal.setMaxLifeTimeUomId((String) fields.get("maxLifeTimeUomId"));
}

		if(fields.get("availableTime") != null) {
String buf;
buf = fields.get("availableTime");
long ibuf = Long.parseLong(buf);
			returnVal.setAvailableTime(ibuf);
}

		if(fields.get("availableTimeUomId") != null) {
			returnVal.setAvailableTimeUomId((String) fields.get("availableTimeUomId"));
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

		if(fields.get("automaticExtend") != null) {
String buf;
buf = fields.get("automaticExtend");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAutomaticExtend(ibuf);
}

		if(fields.get("canclAutmExtTime") != null) {
String buf;
buf = fields.get("canclAutmExtTime");
long ibuf = Long.parseLong(buf);
			returnVal.setCanclAutmExtTime(ibuf);
}

		if(fields.get("canclAutmExtTimeUomId") != null) {
			returnVal.setCanclAutmExtTimeUomId((String) fields.get("canclAutmExtTimeUomId"));
}

		if(fields.get("gracePeriodOnExpiry") != null) {
String buf;
buf = fields.get("gracePeriodOnExpiry");
long ibuf = Long.parseLong(buf);
			returnVal.setGracePeriodOnExpiry(ibuf);
}

		if(fields.get("gracePeriodOnExpiryUomId") != null) {
			returnVal.setGracePeriodOnExpiryUomId((String) fields.get("gracePeriodOnExpiryUomId"));
}


		return returnVal;
 } 
	public static ProductSubscriptionResource map(GenericValue val) {

ProductSubscriptionResource returnVal = new ProductSubscriptionResource();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setSubscriptionResourceId(val.getString("subscriptionResourceId"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setPurchaseFromDate(val.getTimestamp("purchaseFromDate"));
		returnVal.setPurchaseThruDate(val.getTimestamp("purchaseThruDate"));
		returnVal.setMaxLifeTime(val.getLong("maxLifeTime"));
		returnVal.setMaxLifeTimeUomId(val.getString("maxLifeTimeUomId"));
		returnVal.setAvailableTime(val.getLong("availableTime"));
		returnVal.setAvailableTimeUomId(val.getString("availableTimeUomId"));
		returnVal.setUseCountLimit(val.getLong("useCountLimit"));
		returnVal.setUseTime(val.getLong("useTime"));
		returnVal.setUseTimeUomId(val.getString("useTimeUomId"));
		returnVal.setUseRoleTypeId(val.getString("useRoleTypeId"));
		returnVal.setAutomaticExtend(val.getBoolean("automaticExtend"));
		returnVal.setCanclAutmExtTime(val.getLong("canclAutmExtTime"));
		returnVal.setCanclAutmExtTimeUomId(val.getString("canclAutmExtTimeUomId"));
		returnVal.setGracePeriodOnExpiry(val.getLong("gracePeriodOnExpiry"));
		returnVal.setGracePeriodOnExpiryUomId(val.getString("gracePeriodOnExpiryUomId"));


return returnVal;

}

public static ProductSubscriptionResource map(HttpServletRequest request) throws Exception {

		ProductSubscriptionResource returnVal = new ProductSubscriptionResource();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("subscriptionResourceId"))  {
returnVal.setSubscriptionResourceId(request.getParameter("subscriptionResourceId"));
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
		if(paramMap.containsKey("maxLifeTime"))  {
String buf = request.getParameter("maxLifeTime");
Long ibuf = Long.parseLong(buf);
returnVal.setMaxLifeTime(ibuf);
}
		if(paramMap.containsKey("maxLifeTimeUomId"))  {
returnVal.setMaxLifeTimeUomId(request.getParameter("maxLifeTimeUomId"));
}
		if(paramMap.containsKey("availableTime"))  {
String buf = request.getParameter("availableTime");
Long ibuf = Long.parseLong(buf);
returnVal.setAvailableTime(ibuf);
}
		if(paramMap.containsKey("availableTimeUomId"))  {
returnVal.setAvailableTimeUomId(request.getParameter("availableTimeUomId"));
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
		if(paramMap.containsKey("automaticExtend"))  {
String buf = request.getParameter("automaticExtend");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAutomaticExtend(ibuf);
}
		if(paramMap.containsKey("canclAutmExtTime"))  {
String buf = request.getParameter("canclAutmExtTime");
Long ibuf = Long.parseLong(buf);
returnVal.setCanclAutmExtTime(ibuf);
}
		if(paramMap.containsKey("canclAutmExtTimeUomId"))  {
returnVal.setCanclAutmExtTimeUomId(request.getParameter("canclAutmExtTimeUomId"));
}
		if(paramMap.containsKey("gracePeriodOnExpiry"))  {
String buf = request.getParameter("gracePeriodOnExpiry");
Long ibuf = Long.parseLong(buf);
returnVal.setGracePeriodOnExpiry(ibuf);
}
		if(paramMap.containsKey("gracePeriodOnExpiryUomId"))  {
returnVal.setGracePeriodOnExpiryUomId(request.getParameter("gracePeriodOnExpiryUomId"));
}
return returnVal;

}
}
