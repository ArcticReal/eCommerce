package com.skytala.eCommerce.domain.accounting.relations.taxAuthority.mapper.rateProduct;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.taxAuthority.model.rateProduct.TaxAuthorityRateProduct;

public class TaxAuthorityRateProductMapper  {


	public static Map<String, Object> map(TaxAuthorityRateProduct taxauthorityrateproduct) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(taxauthorityrateproduct.getTaxAuthorityRateSeqId() != null ){
			returnVal.put("taxAuthorityRateSeqId",taxauthorityrateproduct.getTaxAuthorityRateSeqId());
}

		if(taxauthorityrateproduct.getTaxAuthGeoId() != null ){
			returnVal.put("taxAuthGeoId",taxauthorityrateproduct.getTaxAuthGeoId());
}

		if(taxauthorityrateproduct.getTaxAuthPartyId() != null ){
			returnVal.put("taxAuthPartyId",taxauthorityrateproduct.getTaxAuthPartyId());
}

		if(taxauthorityrateproduct.getTaxAuthorityRateTypeId() != null ){
			returnVal.put("taxAuthorityRateTypeId",taxauthorityrateproduct.getTaxAuthorityRateTypeId());
}

		if(taxauthorityrateproduct.getProductStoreId() != null ){
			returnVal.put("productStoreId",taxauthorityrateproduct.getProductStoreId());
}

		if(taxauthorityrateproduct.getProductCategoryId() != null ){
			returnVal.put("productCategoryId",taxauthorityrateproduct.getProductCategoryId());
}

		if(taxauthorityrateproduct.getTitleTransferEnumId() != null ){
			returnVal.put("titleTransferEnumId",taxauthorityrateproduct.getTitleTransferEnumId());
}

		if(taxauthorityrateproduct.getMinItemPrice() != null ){
			returnVal.put("minItemPrice",taxauthorityrateproduct.getMinItemPrice());
}

		if(taxauthorityrateproduct.getMinPurchase() != null ){
			returnVal.put("minPurchase",taxauthorityrateproduct.getMinPurchase());
}

		if(taxauthorityrateproduct.getTaxShipping() != null ){
			returnVal.put("taxShipping",taxauthorityrateproduct.getTaxShipping());
}

		if(taxauthorityrateproduct.getTaxPercentage() != null ){
			returnVal.put("taxPercentage",taxauthorityrateproduct.getTaxPercentage());
}

		if(taxauthorityrateproduct.getTaxPromotions() != null ){
			returnVal.put("taxPromotions",taxauthorityrateproduct.getTaxPromotions());
}

		if(taxauthorityrateproduct.getFromDate() != null ){
			returnVal.put("fromDate",taxauthorityrateproduct.getFromDate());
}

		if(taxauthorityrateproduct.getThruDate() != null ){
			returnVal.put("thruDate",taxauthorityrateproduct.getThruDate());
}

		if(taxauthorityrateproduct.getDescription() != null ){
			returnVal.put("description",taxauthorityrateproduct.getDescription());
}

		return returnVal;
}


	public static TaxAuthorityRateProduct map(Map<String, Object> fields) {

		TaxAuthorityRateProduct returnVal = new TaxAuthorityRateProduct();

		if(fields.get("taxAuthorityRateSeqId") != null) {
			returnVal.setTaxAuthorityRateSeqId((String) fields.get("taxAuthorityRateSeqId"));
}

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("taxAuthorityRateTypeId") != null) {
			returnVal.setTaxAuthorityRateTypeId((String) fields.get("taxAuthorityRateTypeId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("titleTransferEnumId") != null) {
			returnVal.setTitleTransferEnumId((String) fields.get("titleTransferEnumId"));
}

		if(fields.get("minItemPrice") != null) {
			returnVal.setMinItemPrice((BigDecimal) fields.get("minItemPrice"));
}

		if(fields.get("minPurchase") != null) {
			returnVal.setMinPurchase((BigDecimal) fields.get("minPurchase"));
}

		if(fields.get("taxShipping") != null) {
			returnVal.setTaxShipping((boolean) fields.get("taxShipping"));
}

		if(fields.get("taxPercentage") != null) {
			returnVal.setTaxPercentage((BigDecimal) fields.get("taxPercentage"));
}

		if(fields.get("taxPromotions") != null) {
			returnVal.setTaxPromotions((boolean) fields.get("taxPromotions"));
}

		if(fields.get("fromDate") != null) {
			returnVal.setFromDate((Timestamp) fields.get("fromDate"));
}

		if(fields.get("thruDate") != null) {
			returnVal.setThruDate((Timestamp) fields.get("thruDate"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TaxAuthorityRateProduct mapstrstr(Map<String, String> fields) throws Exception {

		TaxAuthorityRateProduct returnVal = new TaxAuthorityRateProduct();

		if(fields.get("taxAuthorityRateSeqId") != null) {
			returnVal.setTaxAuthorityRateSeqId((String) fields.get("taxAuthorityRateSeqId"));
}

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("taxAuthorityRateTypeId") != null) {
			returnVal.setTaxAuthorityRateTypeId((String) fields.get("taxAuthorityRateTypeId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("productCategoryId") != null) {
			returnVal.setProductCategoryId((String) fields.get("productCategoryId"));
}

		if(fields.get("titleTransferEnumId") != null) {
			returnVal.setTitleTransferEnumId((String) fields.get("titleTransferEnumId"));
}

		if(fields.get("minItemPrice") != null) {
String buf;
buf = fields.get("minItemPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinItemPrice(bd);
}

		if(fields.get("minPurchase") != null) {
String buf;
buf = fields.get("minPurchase");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinPurchase(bd);
}

		if(fields.get("taxShipping") != null) {
String buf;
buf = fields.get("taxShipping");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setTaxShipping(ibuf);
}

		if(fields.get("taxPercentage") != null) {
String buf;
buf = fields.get("taxPercentage");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTaxPercentage(bd);
}

		if(fields.get("taxPromotions") != null) {
String buf;
buf = fields.get("taxPromotions");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setTaxPromotions(ibuf);
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

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static TaxAuthorityRateProduct map(GenericValue val) {

TaxAuthorityRateProduct returnVal = new TaxAuthorityRateProduct();
		returnVal.setTaxAuthorityRateSeqId(val.getString("taxAuthorityRateSeqId"));
		returnVal.setTaxAuthGeoId(val.getString("taxAuthGeoId"));
		returnVal.setTaxAuthPartyId(val.getString("taxAuthPartyId"));
		returnVal.setTaxAuthorityRateTypeId(val.getString("taxAuthorityRateTypeId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setProductCategoryId(val.getString("productCategoryId"));
		returnVal.setTitleTransferEnumId(val.getString("titleTransferEnumId"));
		returnVal.setMinItemPrice(val.getBigDecimal("minItemPrice"));
		returnVal.setMinPurchase(val.getBigDecimal("minPurchase"));
		returnVal.setTaxShipping(val.getBoolean("taxShipping"));
		returnVal.setTaxPercentage(val.getBigDecimal("taxPercentage"));
		returnVal.setTaxPromotions(val.getBoolean("taxPromotions"));
		returnVal.setFromDate(val.getTimestamp("fromDate"));
		returnVal.setThruDate(val.getTimestamp("thruDate"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static TaxAuthorityRateProduct map(HttpServletRequest request) throws Exception {

		TaxAuthorityRateProduct returnVal = new TaxAuthorityRateProduct();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("taxAuthorityRateSeqId")) {
returnVal.setTaxAuthorityRateSeqId(request.getParameter("taxAuthorityRateSeqId"));
}

		if(paramMap.containsKey("taxAuthGeoId"))  {
returnVal.setTaxAuthGeoId(request.getParameter("taxAuthGeoId"));
}
		if(paramMap.containsKey("taxAuthPartyId"))  {
returnVal.setTaxAuthPartyId(request.getParameter("taxAuthPartyId"));
}
		if(paramMap.containsKey("taxAuthorityRateTypeId"))  {
returnVal.setTaxAuthorityRateTypeId(request.getParameter("taxAuthorityRateTypeId"));
}
		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}
		if(paramMap.containsKey("productCategoryId"))  {
returnVal.setProductCategoryId(request.getParameter("productCategoryId"));
}
		if(paramMap.containsKey("titleTransferEnumId"))  {
returnVal.setTitleTransferEnumId(request.getParameter("titleTransferEnumId"));
}
		if(paramMap.containsKey("minItemPrice"))  {
String buf = request.getParameter("minItemPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinItemPrice(bd);
}
		if(paramMap.containsKey("minPurchase"))  {
String buf = request.getParameter("minPurchase");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinPurchase(bd);
}
		if(paramMap.containsKey("taxShipping"))  {
String buf = request.getParameter("taxShipping");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setTaxShipping(ibuf);
}
		if(paramMap.containsKey("taxPercentage"))  {
String buf = request.getParameter("taxPercentage");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTaxPercentage(bd);
}
		if(paramMap.containsKey("taxPromotions"))  {
String buf = request.getParameter("taxPromotions");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setTaxPromotions(ibuf);
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
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
