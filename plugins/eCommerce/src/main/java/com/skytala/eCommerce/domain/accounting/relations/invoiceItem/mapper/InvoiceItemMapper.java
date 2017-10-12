package com.skytala.eCommerce.domain.accounting.relations.invoiceItem.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItem.model.InvoiceItem;

public class InvoiceItemMapper  {


	public static Map<String, Object> map(InvoiceItem invoiceitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(invoiceitem.getInvoiceId() != null ){
			returnVal.put("invoiceId",invoiceitem.getInvoiceId());
}

		if(invoiceitem.getInvoiceItemSeqId() != null ){
			returnVal.put("invoiceItemSeqId",invoiceitem.getInvoiceItemSeqId());
}

		if(invoiceitem.getInvoiceItemTypeId() != null ){
			returnVal.put("invoiceItemTypeId",invoiceitem.getInvoiceItemTypeId());
}

		if(invoiceitem.getOverrideGlAccountId() != null ){
			returnVal.put("overrideGlAccountId",invoiceitem.getOverrideGlAccountId());
}

		if(invoiceitem.getOverrideOrgPartyId() != null ){
			returnVal.put("overrideOrgPartyId",invoiceitem.getOverrideOrgPartyId());
}

		if(invoiceitem.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",invoiceitem.getInventoryItemId());
}

		if(invoiceitem.getProductId() != null ){
			returnVal.put("productId",invoiceitem.getProductId());
}

		if(invoiceitem.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",invoiceitem.getProductFeatureId());
}

		if(invoiceitem.getParentInvoiceId() != null ){
			returnVal.put("parentInvoiceId",invoiceitem.getParentInvoiceId());
}

		if(invoiceitem.getParentInvoiceItemSeqId() != null ){
			returnVal.put("parentInvoiceItemSeqId",invoiceitem.getParentInvoiceItemSeqId());
}

		if(invoiceitem.getUomId() != null ){
			returnVal.put("uomId",invoiceitem.getUomId());
}

		if(invoiceitem.getTaxableFlag() != null ){
			returnVal.put("taxableFlag",invoiceitem.getTaxableFlag());
}

		if(invoiceitem.getQuantity() != null ){
			returnVal.put("quantity",invoiceitem.getQuantity());
}

		if(invoiceitem.getAmount() != null ){
			returnVal.put("amount",invoiceitem.getAmount());
}

		if(invoiceitem.getDescription() != null ){
			returnVal.put("description",invoiceitem.getDescription());
}

		if(invoiceitem.getTaxAuthPartyId() != null ){
			returnVal.put("taxAuthPartyId",invoiceitem.getTaxAuthPartyId());
}

		if(invoiceitem.getTaxAuthGeoId() != null ){
			returnVal.put("taxAuthGeoId",invoiceitem.getTaxAuthGeoId());
}

		if(invoiceitem.getTaxAuthorityRateSeqId() != null ){
			returnVal.put("taxAuthorityRateSeqId",invoiceitem.getTaxAuthorityRateSeqId());
}

		if(invoiceitem.getSalesOpportunityId() != null ){
			returnVal.put("salesOpportunityId",invoiceitem.getSalesOpportunityId());
}

		return returnVal;
}


	public static InvoiceItem map(Map<String, Object> fields) {

		InvoiceItem returnVal = new InvoiceItem();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("invoiceItemTypeId") != null) {
			returnVal.setInvoiceItemTypeId((String) fields.get("invoiceItemTypeId"));
}

		if(fields.get("overrideGlAccountId") != null) {
			returnVal.setOverrideGlAccountId((String) fields.get("overrideGlAccountId"));
}

		if(fields.get("overrideOrgPartyId") != null) {
			returnVal.setOverrideOrgPartyId((String) fields.get("overrideOrgPartyId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("parentInvoiceId") != null) {
			returnVal.setParentInvoiceId((String) fields.get("parentInvoiceId"));
}

		if(fields.get("parentInvoiceItemSeqId") != null) {
			returnVal.setParentInvoiceItemSeqId((String) fields.get("parentInvoiceItemSeqId"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("taxableFlag") != null) {
			returnVal.setTaxableFlag((boolean) fields.get("taxableFlag"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("amount") != null) {
			returnVal.setAmount((BigDecimal) fields.get("amount"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthorityRateSeqId") != null) {
			returnVal.setTaxAuthorityRateSeqId((String) fields.get("taxAuthorityRateSeqId"));
}

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}


		return returnVal;
 } 
	public static InvoiceItem mapstrstr(Map<String, String> fields) throws Exception {

		InvoiceItem returnVal = new InvoiceItem();

		if(fields.get("invoiceId") != null) {
			returnVal.setInvoiceId((String) fields.get("invoiceId"));
}

		if(fields.get("invoiceItemSeqId") != null) {
			returnVal.setInvoiceItemSeqId((String) fields.get("invoiceItemSeqId"));
}

		if(fields.get("invoiceItemTypeId") != null) {
			returnVal.setInvoiceItemTypeId((String) fields.get("invoiceItemTypeId"));
}

		if(fields.get("overrideGlAccountId") != null) {
			returnVal.setOverrideGlAccountId((String) fields.get("overrideGlAccountId"));
}

		if(fields.get("overrideOrgPartyId") != null) {
			returnVal.setOverrideOrgPartyId((String) fields.get("overrideOrgPartyId"));
}

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("parentInvoiceId") != null) {
			returnVal.setParentInvoiceId((String) fields.get("parentInvoiceId"));
}

		if(fields.get("parentInvoiceItemSeqId") != null) {
			returnVal.setParentInvoiceItemSeqId((String) fields.get("parentInvoiceItemSeqId"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("taxableFlag") != null) {
String buf;
buf = fields.get("taxableFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setTaxableFlag(ibuf);
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("amount") != null) {
String buf;
buf = fields.get("amount");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("taxAuthPartyId") != null) {
			returnVal.setTaxAuthPartyId((String) fields.get("taxAuthPartyId"));
}

		if(fields.get("taxAuthGeoId") != null) {
			returnVal.setTaxAuthGeoId((String) fields.get("taxAuthGeoId"));
}

		if(fields.get("taxAuthorityRateSeqId") != null) {
			returnVal.setTaxAuthorityRateSeqId((String) fields.get("taxAuthorityRateSeqId"));
}

		if(fields.get("salesOpportunityId") != null) {
			returnVal.setSalesOpportunityId((String) fields.get("salesOpportunityId"));
}


		return returnVal;
 } 
	public static InvoiceItem map(GenericValue val) {

InvoiceItem returnVal = new InvoiceItem();
		returnVal.setInvoiceId(val.getString("invoiceId"));
		returnVal.setInvoiceItemSeqId(val.getString("invoiceItemSeqId"));
		returnVal.setInvoiceItemTypeId(val.getString("invoiceItemTypeId"));
		returnVal.setOverrideGlAccountId(val.getString("overrideGlAccountId"));
		returnVal.setOverrideOrgPartyId(val.getString("overrideOrgPartyId"));
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setParentInvoiceId(val.getString("parentInvoiceId"));
		returnVal.setParentInvoiceItemSeqId(val.getString("parentInvoiceItemSeqId"));
		returnVal.setUomId(val.getString("uomId"));
		returnVal.setTaxableFlag(val.getBoolean("taxableFlag"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setAmount(val.getBigDecimal("amount"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setTaxAuthPartyId(val.getString("taxAuthPartyId"));
		returnVal.setTaxAuthGeoId(val.getString("taxAuthGeoId"));
		returnVal.setTaxAuthorityRateSeqId(val.getString("taxAuthorityRateSeqId"));
		returnVal.setSalesOpportunityId(val.getString("salesOpportunityId"));


return returnVal;

}

public static InvoiceItem map(HttpServletRequest request) throws Exception {

		InvoiceItem returnVal = new InvoiceItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("invoiceId")) {
returnVal.setInvoiceId(request.getParameter("invoiceId"));
}

		if(paramMap.containsKey("invoiceItemSeqId"))  {
returnVal.setInvoiceItemSeqId(request.getParameter("invoiceItemSeqId"));
}
		if(paramMap.containsKey("invoiceItemTypeId"))  {
returnVal.setInvoiceItemTypeId(request.getParameter("invoiceItemTypeId"));
}
		if(paramMap.containsKey("overrideGlAccountId"))  {
returnVal.setOverrideGlAccountId(request.getParameter("overrideGlAccountId"));
}
		if(paramMap.containsKey("overrideOrgPartyId"))  {
returnVal.setOverrideOrgPartyId(request.getParameter("overrideOrgPartyId"));
}
		if(paramMap.containsKey("inventoryItemId"))  {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("productFeatureId"))  {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}
		if(paramMap.containsKey("parentInvoiceId"))  {
returnVal.setParentInvoiceId(request.getParameter("parentInvoiceId"));
}
		if(paramMap.containsKey("parentInvoiceItemSeqId"))  {
returnVal.setParentInvoiceItemSeqId(request.getParameter("parentInvoiceItemSeqId"));
}
		if(paramMap.containsKey("uomId"))  {
returnVal.setUomId(request.getParameter("uomId"));
}
		if(paramMap.containsKey("taxableFlag"))  {
String buf = request.getParameter("taxableFlag");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setTaxableFlag(ibuf);
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("amount"))  {
String buf = request.getParameter("amount");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAmount(bd);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("taxAuthPartyId"))  {
returnVal.setTaxAuthPartyId(request.getParameter("taxAuthPartyId"));
}
		if(paramMap.containsKey("taxAuthGeoId"))  {
returnVal.setTaxAuthGeoId(request.getParameter("taxAuthGeoId"));
}
		if(paramMap.containsKey("taxAuthorityRateSeqId"))  {
returnVal.setTaxAuthorityRateSeqId(request.getParameter("taxAuthorityRateSeqId"));
}
		if(paramMap.containsKey("salesOpportunityId"))  {
returnVal.setSalesOpportunityId(request.getParameter("salesOpportunityId"));
}
return returnVal;

}
}
