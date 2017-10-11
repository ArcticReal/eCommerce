package com.skytala.eCommerce.domain.product.relations.supplierProductFeature.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.supplierProductFeature.model.SupplierProductFeature;

public class SupplierProductFeatureMapper  {


	public static Map<String, Object> map(SupplierProductFeature supplierproductfeature) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(supplierproductfeature.getPartyId() != null ){
			returnVal.put("partyId",supplierproductfeature.getPartyId());
}

		if(supplierproductfeature.getProductFeatureId() != null ){
			returnVal.put("productFeatureId",supplierproductfeature.getProductFeatureId());
}

		if(supplierproductfeature.getDescription() != null ){
			returnVal.put("description",supplierproductfeature.getDescription());
}

		if(supplierproductfeature.getUomId() != null ){
			returnVal.put("uomId",supplierproductfeature.getUomId());
}

		if(supplierproductfeature.getIdCode() != null ){
			returnVal.put("idCode",supplierproductfeature.getIdCode());
}

		return returnVal;
}


	public static SupplierProductFeature map(Map<String, Object> fields) {

		SupplierProductFeature returnVal = new SupplierProductFeature();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("idCode") != null) {
			returnVal.setIdCode((String) fields.get("idCode"));
}


		return returnVal;
 } 
	public static SupplierProductFeature mapstrstr(Map<String, String> fields) throws Exception {

		SupplierProductFeature returnVal = new SupplierProductFeature();

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("productFeatureId") != null) {
			returnVal.setProductFeatureId((String) fields.get("productFeatureId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("idCode") != null) {
			returnVal.setIdCode((String) fields.get("idCode"));
}


		return returnVal;
 } 
	public static SupplierProductFeature map(GenericValue val) {

SupplierProductFeature returnVal = new SupplierProductFeature();
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setProductFeatureId(val.getString("productFeatureId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setUomId(val.getString("uomId"));
		returnVal.setIdCode(val.getString("idCode"));


return returnVal;

}

public static SupplierProductFeature map(HttpServletRequest request) throws Exception {

		SupplierProductFeature returnVal = new SupplierProductFeature();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("partyId")) {
returnVal.setPartyId(request.getParameter("partyId"));
}

		if(paramMap.containsKey("productFeatureId"))  {
returnVal.setProductFeatureId(request.getParameter("productFeatureId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("uomId"))  {
returnVal.setUomId(request.getParameter("uomId"));
}
		if(paramMap.containsKey("idCode"))  {
returnVal.setIdCode(request.getParameter("idCode"));
}
return returnVal;

}
}
