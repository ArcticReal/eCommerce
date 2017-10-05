package com.skytala.eCommerce.domain.supplierProduct.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.supplierProduct.model.SupplierProduct;

public class SupplierProductMapper  {


	public static Map<String, Object> map(SupplierProduct supplierproduct) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(supplierproduct.getProductId() != null ){
			returnVal.put("productId",supplierproduct.getProductId());
}

		if(supplierproduct.getPartyId() != null ){
			returnVal.put("partyId",supplierproduct.getPartyId());
}

		if(supplierproduct.getAvailableFromDate() != null ){
			returnVal.put("availableFromDate",supplierproduct.getAvailableFromDate());
}

		if(supplierproduct.getAvailableThruDate() != null ){
			returnVal.put("availableThruDate",supplierproduct.getAvailableThruDate());
}

		if(supplierproduct.getSupplierPrefOrderId() != null ){
			returnVal.put("supplierPrefOrderId",supplierproduct.getSupplierPrefOrderId());
}

		if(supplierproduct.getSupplierRatingTypeId() != null ){
			returnVal.put("supplierRatingTypeId",supplierproduct.getSupplierRatingTypeId());
}

		if(supplierproduct.getStandardLeadTimeDays() != null ){
			returnVal.put("standardLeadTimeDays",supplierproduct.getStandardLeadTimeDays());
}

		if(supplierproduct.getMinimumOrderQuantity() != null ){
			returnVal.put("minimumOrderQuantity",supplierproduct.getMinimumOrderQuantity());
}

		if(supplierproduct.getOrderQtyIncrements() != null ){
			returnVal.put("orderQtyIncrements",supplierproduct.getOrderQtyIncrements());
}

		if(supplierproduct.getUnitsIncluded() != null ){
			returnVal.put("unitsIncluded",supplierproduct.getUnitsIncluded());
}

		if(supplierproduct.getQuantityUomId() != null ){
			returnVal.put("quantityUomId",supplierproduct.getQuantityUomId());
}

		if(supplierproduct.getAgreementId() != null ){
			returnVal.put("agreementId",supplierproduct.getAgreementId());
}

		if(supplierproduct.getAgreementItemSeqId() != null ){
			returnVal.put("agreementItemSeqId",supplierproduct.getAgreementItemSeqId());
}

		if(supplierproduct.getLastPrice() != null ){
			returnVal.put("lastPrice",supplierproduct.getLastPrice());
}

		if(supplierproduct.getShippingPrice() != null ){
			returnVal.put("shippingPrice",supplierproduct.getShippingPrice());
}

		if(supplierproduct.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",supplierproduct.getCurrencyUomId());
}

		if(supplierproduct.getSupplierProductName() != null ){
			returnVal.put("supplierProductName",supplierproduct.getSupplierProductName());
}

		if(supplierproduct.getSupplierProductId() != null ){
			returnVal.put("supplierProductId",supplierproduct.getSupplierProductId());
}

		if(supplierproduct.getCanDropShip() != null ){
			returnVal.put("canDropShip",supplierproduct.getCanDropShip());
}

		if(supplierproduct.getComments() != null ){
			returnVal.put("comments",supplierproduct.getComments());
}

		return returnVal;
}


	public static SupplierProduct map(Map<String, Object> fields) {

		SupplierProduct returnVal = new SupplierProduct();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("availableFromDate") != null) {
			returnVal.setAvailableFromDate((Timestamp) fields.get("availableFromDate"));
}

		if(fields.get("availableThruDate") != null) {
			returnVal.setAvailableThruDate((Timestamp) fields.get("availableThruDate"));
}

		if(fields.get("supplierPrefOrderId") != null) {
			returnVal.setSupplierPrefOrderId((String) fields.get("supplierPrefOrderId"));
}

		if(fields.get("supplierRatingTypeId") != null) {
			returnVal.setSupplierRatingTypeId((String) fields.get("supplierRatingTypeId"));
}

		if(fields.get("standardLeadTimeDays") != null) {
			returnVal.setStandardLeadTimeDays((BigDecimal) fields.get("standardLeadTimeDays"));
}

		if(fields.get("minimumOrderQuantity") != null) {
			returnVal.setMinimumOrderQuantity((BigDecimal) fields.get("minimumOrderQuantity"));
}

		if(fields.get("orderQtyIncrements") != null) {
			returnVal.setOrderQtyIncrements((BigDecimal) fields.get("orderQtyIncrements"));
}

		if(fields.get("unitsIncluded") != null) {
			returnVal.setUnitsIncluded((BigDecimal) fields.get("unitsIncluded"));
}

		if(fields.get("quantityUomId") != null) {
			returnVal.setQuantityUomId((String) fields.get("quantityUomId"));
}

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("lastPrice") != null) {
			returnVal.setLastPrice((BigDecimal) fields.get("lastPrice"));
}

		if(fields.get("shippingPrice") != null) {
			returnVal.setShippingPrice((BigDecimal) fields.get("shippingPrice"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("supplierProductName") != null) {
			returnVal.setSupplierProductName((String) fields.get("supplierProductName"));
}

		if(fields.get("supplierProductId") != null) {
			returnVal.setSupplierProductId((String) fields.get("supplierProductId"));
}

		if(fields.get("canDropShip") != null) {
			returnVal.setCanDropShip((boolean) fields.get("canDropShip"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static SupplierProduct mapstrstr(Map<String, String> fields) throws Exception {

		SupplierProduct returnVal = new SupplierProduct();

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("availableFromDate") != null) {
String buf = fields.get("availableFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setAvailableFromDate(ibuf);
}

		if(fields.get("availableThruDate") != null) {
String buf = fields.get("availableThruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setAvailableThruDate(ibuf);
}

		if(fields.get("supplierPrefOrderId") != null) {
			returnVal.setSupplierPrefOrderId((String) fields.get("supplierPrefOrderId"));
}

		if(fields.get("supplierRatingTypeId") != null) {
			returnVal.setSupplierRatingTypeId((String) fields.get("supplierRatingTypeId"));
}

		if(fields.get("standardLeadTimeDays") != null) {
String buf;
buf = fields.get("standardLeadTimeDays");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setStandardLeadTimeDays(bd);
}

		if(fields.get("minimumOrderQuantity") != null) {
String buf;
buf = fields.get("minimumOrderQuantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinimumOrderQuantity(bd);
}

		if(fields.get("orderQtyIncrements") != null) {
String buf;
buf = fields.get("orderQtyIncrements");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOrderQtyIncrements(bd);
}

		if(fields.get("unitsIncluded") != null) {
String buf;
buf = fields.get("unitsIncluded");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitsIncluded(bd);
}

		if(fields.get("quantityUomId") != null) {
			returnVal.setQuantityUomId((String) fields.get("quantityUomId"));
}

		if(fields.get("agreementId") != null) {
			returnVal.setAgreementId((String) fields.get("agreementId"));
}

		if(fields.get("agreementItemSeqId") != null) {
			returnVal.setAgreementItemSeqId((String) fields.get("agreementItemSeqId"));
}

		if(fields.get("lastPrice") != null) {
String buf;
buf = fields.get("lastPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setLastPrice(bd);
}

		if(fields.get("shippingPrice") != null) {
String buf;
buf = fields.get("shippingPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setShippingPrice(bd);
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}

		if(fields.get("supplierProductName") != null) {
			returnVal.setSupplierProductName((String) fields.get("supplierProductName"));
}

		if(fields.get("supplierProductId") != null) {
			returnVal.setSupplierProductId((String) fields.get("supplierProductId"));
}

		if(fields.get("canDropShip") != null) {
String buf;
buf = fields.get("canDropShip");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setCanDropShip(ibuf);
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}


		return returnVal;
 } 
	public static SupplierProduct map(GenericValue val) {

SupplierProduct returnVal = new SupplierProduct();
		returnVal.setProductId(val.getString("productId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setAvailableFromDate(val.getTimestamp("availableFromDate"));
		returnVal.setAvailableThruDate(val.getTimestamp("availableThruDate"));
		returnVal.setSupplierPrefOrderId(val.getString("supplierPrefOrderId"));
		returnVal.setSupplierRatingTypeId(val.getString("supplierRatingTypeId"));
		returnVal.setStandardLeadTimeDays(val.getBigDecimal("standardLeadTimeDays"));
		returnVal.setMinimumOrderQuantity(val.getBigDecimal("minimumOrderQuantity"));
		returnVal.setOrderQtyIncrements(val.getBigDecimal("orderQtyIncrements"));
		returnVal.setUnitsIncluded(val.getBigDecimal("unitsIncluded"));
		returnVal.setQuantityUomId(val.getString("quantityUomId"));
		returnVal.setAgreementId(val.getString("agreementId"));
		returnVal.setAgreementItemSeqId(val.getString("agreementItemSeqId"));
		returnVal.setLastPrice(val.getBigDecimal("lastPrice"));
		returnVal.setShippingPrice(val.getBigDecimal("shippingPrice"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));
		returnVal.setSupplierProductName(val.getString("supplierProductName"));
		returnVal.setSupplierProductId(val.getString("supplierProductId"));
		returnVal.setCanDropShip(val.getBoolean("canDropShip"));
		returnVal.setComments(val.getString("comments"));


return returnVal;

}

public static SupplierProduct map(HttpServletRequest request) throws Exception {

		SupplierProduct returnVal = new SupplierProduct();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productId")) {
returnVal.setProductId(request.getParameter("productId"));
}

		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("availableFromDate"))  {
String buf = request.getParameter("availableFromDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setAvailableFromDate(ibuf);
}
		if(paramMap.containsKey("availableThruDate"))  {
String buf = request.getParameter("availableThruDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setAvailableThruDate(ibuf);
}
		if(paramMap.containsKey("supplierPrefOrderId"))  {
returnVal.setSupplierPrefOrderId(request.getParameter("supplierPrefOrderId"));
}
		if(paramMap.containsKey("supplierRatingTypeId"))  {
returnVal.setSupplierRatingTypeId(request.getParameter("supplierRatingTypeId"));
}
		if(paramMap.containsKey("standardLeadTimeDays"))  {
String buf = request.getParameter("standardLeadTimeDays");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setStandardLeadTimeDays(bd);
}
		if(paramMap.containsKey("minimumOrderQuantity"))  {
String buf = request.getParameter("minimumOrderQuantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinimumOrderQuantity(bd);
}
		if(paramMap.containsKey("orderQtyIncrements"))  {
String buf = request.getParameter("orderQtyIncrements");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOrderQtyIncrements(bd);
}
		if(paramMap.containsKey("unitsIncluded"))  {
String buf = request.getParameter("unitsIncluded");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitsIncluded(bd);
}
		if(paramMap.containsKey("quantityUomId"))  {
returnVal.setQuantityUomId(request.getParameter("quantityUomId"));
}
		if(paramMap.containsKey("agreementId"))  {
returnVal.setAgreementId(request.getParameter("agreementId"));
}
		if(paramMap.containsKey("agreementItemSeqId"))  {
returnVal.setAgreementItemSeqId(request.getParameter("agreementItemSeqId"));
}
		if(paramMap.containsKey("lastPrice"))  {
String buf = request.getParameter("lastPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setLastPrice(bd);
}
		if(paramMap.containsKey("shippingPrice"))  {
String buf = request.getParameter("shippingPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setShippingPrice(bd);
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
		if(paramMap.containsKey("supplierProductName"))  {
returnVal.setSupplierProductName(request.getParameter("supplierProductName"));
}
		if(paramMap.containsKey("supplierProductId"))  {
returnVal.setSupplierProductId(request.getParameter("supplierProductId"));
}
		if(paramMap.containsKey("canDropShip"))  {
String buf = request.getParameter("canDropShip");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setCanDropShip(ibuf);
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
return returnVal;

}
}
