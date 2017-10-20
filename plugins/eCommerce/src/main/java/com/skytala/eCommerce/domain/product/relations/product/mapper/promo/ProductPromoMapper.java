package com.skytala.eCommerce.domain.product.relations.product.mapper.promo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.promo.ProductPromo;

public class ProductPromoMapper  {


	public static Map<String, Object> map(ProductPromo productpromo) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productpromo.getProductPromoId() != null ){
			returnVal.put("productPromoId",productpromo.getProductPromoId());
}

		if(productpromo.getPromoName() != null ){
			returnVal.put("promoName",productpromo.getPromoName());
}

		if(productpromo.getPromoText() != null ){
			returnVal.put("promoText",productpromo.getPromoText());
}

		if(productpromo.getUserEntered() != null ){
			returnVal.put("userEntered",productpromo.getUserEntered());
}

		if(productpromo.getShowToCustomer() != null ){
			returnVal.put("showToCustomer",productpromo.getShowToCustomer());
}

		if(productpromo.getRequireCode() != null ){
			returnVal.put("requireCode",productpromo.getRequireCode());
}

		if(productpromo.getUseLimitPerOrder() != null ){
			returnVal.put("useLimitPerOrder",productpromo.getUseLimitPerOrder());
}

		if(productpromo.getUseLimitPerCustomer() != null ){
			returnVal.put("useLimitPerCustomer",productpromo.getUseLimitPerCustomer());
}

		if(productpromo.getUseLimitPerPromotion() != null ){
			returnVal.put("useLimitPerPromotion",productpromo.getUseLimitPerPromotion());
}

		if(productpromo.getBillbackFactor() != null ){
			returnVal.put("billbackFactor",productpromo.getBillbackFactor());
}

		if(productpromo.getOverrideOrgPartyId() != null ){
			returnVal.put("overrideOrgPartyId",productpromo.getOverrideOrgPartyId());
}

		if(productpromo.getCreatedDate() != null ){
			returnVal.put("createdDate",productpromo.getCreatedDate());
}

		if(productpromo.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",productpromo.getCreatedByUserLogin());
}

		if(productpromo.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",productpromo.getLastModifiedDate());
}

		if(productpromo.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",productpromo.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static ProductPromo map(Map<String, Object> fields) {

		ProductPromo returnVal = new ProductPromo();

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("promoName") != null) {
			returnVal.setPromoName((String) fields.get("promoName"));
}

		if(fields.get("promoText") != null) {
			returnVal.setPromoText((String) fields.get("promoText"));
}

		if(fields.get("userEntered") != null) {
			returnVal.setUserEntered((boolean) fields.get("userEntered"));
}

		if(fields.get("showToCustomer") != null) {
			returnVal.setShowToCustomer((boolean) fields.get("showToCustomer"));
}

		if(fields.get("requireCode") != null) {
			returnVal.setRequireCode((boolean) fields.get("requireCode"));
}

		if(fields.get("useLimitPerOrder") != null) {
			returnVal.setUseLimitPerOrder((long) fields.get("useLimitPerOrder"));
}

		if(fields.get("useLimitPerCustomer") != null) {
			returnVal.setUseLimitPerCustomer((long) fields.get("useLimitPerCustomer"));
}

		if(fields.get("useLimitPerPromotion") != null) {
			returnVal.setUseLimitPerPromotion((long) fields.get("useLimitPerPromotion"));
}

		if(fields.get("billbackFactor") != null) {
			returnVal.setBillbackFactor((BigDecimal) fields.get("billbackFactor"));
}

		if(fields.get("overrideOrgPartyId") != null) {
			returnVal.setOverrideOrgPartyId((String) fields.get("overrideOrgPartyId"));
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
	public static ProductPromo mapstrstr(Map<String, String> fields) throws Exception {

		ProductPromo returnVal = new ProductPromo();

		if(fields.get("productPromoId") != null) {
			returnVal.setProductPromoId((String) fields.get("productPromoId"));
}

		if(fields.get("promoName") != null) {
			returnVal.setPromoName((String) fields.get("promoName"));
}

		if(fields.get("promoText") != null) {
			returnVal.setPromoText((String) fields.get("promoText"));
}

		if(fields.get("userEntered") != null) {
String buf;
buf = fields.get("userEntered");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setUserEntered(ibuf);
}

		if(fields.get("showToCustomer") != null) {
String buf;
buf = fields.get("showToCustomer");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setShowToCustomer(ibuf);
}

		if(fields.get("requireCode") != null) {
String buf;
buf = fields.get("requireCode");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequireCode(ibuf);
}

		if(fields.get("useLimitPerOrder") != null) {
String buf;
buf = fields.get("useLimitPerOrder");
long ibuf = Long.parseLong(buf);
			returnVal.setUseLimitPerOrder(ibuf);
}

		if(fields.get("useLimitPerCustomer") != null) {
String buf;
buf = fields.get("useLimitPerCustomer");
long ibuf = Long.parseLong(buf);
			returnVal.setUseLimitPerCustomer(ibuf);
}

		if(fields.get("useLimitPerPromotion") != null) {
String buf;
buf = fields.get("useLimitPerPromotion");
long ibuf = Long.parseLong(buf);
			returnVal.setUseLimitPerPromotion(ibuf);
}

		if(fields.get("billbackFactor") != null) {
String buf;
buf = fields.get("billbackFactor");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBillbackFactor(bd);
}

		if(fields.get("overrideOrgPartyId") != null) {
			returnVal.setOverrideOrgPartyId((String) fields.get("overrideOrgPartyId"));
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
	public static ProductPromo map(GenericValue val) {

ProductPromo returnVal = new ProductPromo();
		returnVal.setProductPromoId(val.getString("productPromoId"));
		returnVal.setPromoName(val.getString("promoName"));
		returnVal.setPromoText(val.getString("promoText"));
		returnVal.setUserEntered(val.getBoolean("userEntered"));
		returnVal.setShowToCustomer(val.getBoolean("showToCustomer"));
		returnVal.setRequireCode(val.getBoolean("requireCode"));
		returnVal.setUseLimitPerOrder(val.getLong("useLimitPerOrder"));
		returnVal.setUseLimitPerCustomer(val.getLong("useLimitPerCustomer"));
		returnVal.setUseLimitPerPromotion(val.getLong("useLimitPerPromotion"));
		returnVal.setBillbackFactor(val.getBigDecimal("billbackFactor"));
		returnVal.setOverrideOrgPartyId(val.getString("overrideOrgPartyId"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static ProductPromo map(HttpServletRequest request) throws Exception {

		ProductPromo returnVal = new ProductPromo();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productPromoId")) {
returnVal.setProductPromoId(request.getParameter("productPromoId"));
}

		if(paramMap.containsKey("promoName"))  {
returnVal.setPromoName(request.getParameter("promoName"));
}
		if(paramMap.containsKey("promoText"))  {
returnVal.setPromoText(request.getParameter("promoText"));
}
		if(paramMap.containsKey("userEntered"))  {
String buf = request.getParameter("userEntered");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setUserEntered(ibuf);
}
		if(paramMap.containsKey("showToCustomer"))  {
String buf = request.getParameter("showToCustomer");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setShowToCustomer(ibuf);
}
		if(paramMap.containsKey("requireCode"))  {
String buf = request.getParameter("requireCode");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setRequireCode(ibuf);
}
		if(paramMap.containsKey("useLimitPerOrder"))  {
String buf = request.getParameter("useLimitPerOrder");
Long ibuf = Long.parseLong(buf);
returnVal.setUseLimitPerOrder(ibuf);
}
		if(paramMap.containsKey("useLimitPerCustomer"))  {
String buf = request.getParameter("useLimitPerCustomer");
Long ibuf = Long.parseLong(buf);
returnVal.setUseLimitPerCustomer(ibuf);
}
		if(paramMap.containsKey("useLimitPerPromotion"))  {
String buf = request.getParameter("useLimitPerPromotion");
Long ibuf = Long.parseLong(buf);
returnVal.setUseLimitPerPromotion(ibuf);
}
		if(paramMap.containsKey("billbackFactor"))  {
String buf = request.getParameter("billbackFactor");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setBillbackFactor(bd);
}
		if(paramMap.containsKey("overrideOrgPartyId"))  {
returnVal.setOverrideOrgPartyId(request.getParameter("overrideOrgPartyId"));
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
