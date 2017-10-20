package com.skytala.eCommerce.domain.product.relations.product.mapper.storeShipmentMeth;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.product.model.storeShipmentMeth.ProductStoreShipmentMeth;

public class ProductStoreShipmentMethMapper  {


	public static Map<String, Object> map(ProductStoreShipmentMeth productstoreshipmentmeth) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstoreshipmentmeth.getProductStoreShipMethId() != null ){
			returnVal.put("productStoreShipMethId",productstoreshipmentmeth.getProductStoreShipMethId());
}

		if(productstoreshipmentmeth.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstoreshipmentmeth.getProductStoreId());
}

		if(productstoreshipmentmeth.getShipmentMethodTypeId() != null ){
			returnVal.put("shipmentMethodTypeId",productstoreshipmentmeth.getShipmentMethodTypeId());
}

		if(productstoreshipmentmeth.getPartyId() != null ){
			returnVal.put("partyId",productstoreshipmentmeth.getPartyId());
}

		if(productstoreshipmentmeth.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",productstoreshipmentmeth.getRoleTypeId());
}

		if(productstoreshipmentmeth.getCompanyPartyId() != null ){
			returnVal.put("companyPartyId",productstoreshipmentmeth.getCompanyPartyId());
}

		if(productstoreshipmentmeth.getMinWeight() != null ){
			returnVal.put("minWeight",productstoreshipmentmeth.getMinWeight());
}

		if(productstoreshipmentmeth.getMaxWeight() != null ){
			returnVal.put("maxWeight",productstoreshipmentmeth.getMaxWeight());
}

		if(productstoreshipmentmeth.getMinSize() != null ){
			returnVal.put("minSize",productstoreshipmentmeth.getMinSize());
}

		if(productstoreshipmentmeth.getMaxSize() != null ){
			returnVal.put("maxSize",productstoreshipmentmeth.getMaxSize());
}

		if(productstoreshipmentmeth.getMinTotal() != null ){
			returnVal.put("minTotal",productstoreshipmentmeth.getMinTotal());
}

		if(productstoreshipmentmeth.getMaxTotal() != null ){
			returnVal.put("maxTotal",productstoreshipmentmeth.getMaxTotal());
}

		if(productstoreshipmentmeth.getAllowUspsAddr() != null ){
			returnVal.put("allowUspsAddr",productstoreshipmentmeth.getAllowUspsAddr());
}

		if(productstoreshipmentmeth.getRequireUspsAddr() != null ){
			returnVal.put("requireUspsAddr",productstoreshipmentmeth.getRequireUspsAddr());
}

		if(productstoreshipmentmeth.getAllowCompanyAddr() != null ){
			returnVal.put("allowCompanyAddr",productstoreshipmentmeth.getAllowCompanyAddr());
}

		if(productstoreshipmentmeth.getRequireCompanyAddr() != null ){
			returnVal.put("requireCompanyAddr",productstoreshipmentmeth.getRequireCompanyAddr());
}

		if(productstoreshipmentmeth.getIncludeNoChargeItems() != null ){
			returnVal.put("includeNoChargeItems",productstoreshipmentmeth.getIncludeNoChargeItems());
}

		if(productstoreshipmentmeth.getIncludeFeatureGroup() != null ){
			returnVal.put("includeFeatureGroup",productstoreshipmentmeth.getIncludeFeatureGroup());
}

		if(productstoreshipmentmeth.getExcludeFeatureGroup() != null ){
			returnVal.put("excludeFeatureGroup",productstoreshipmentmeth.getExcludeFeatureGroup());
}

		if(productstoreshipmentmeth.getIncludeGeoId() != null ){
			returnVal.put("includeGeoId",productstoreshipmentmeth.getIncludeGeoId());
}

		if(productstoreshipmentmeth.getExcludeGeoId() != null ){
			returnVal.put("excludeGeoId",productstoreshipmentmeth.getExcludeGeoId());
}

		if(productstoreshipmentmeth.getServiceName() != null ){
			returnVal.put("serviceName",productstoreshipmentmeth.getServiceName());
}

		if(productstoreshipmentmeth.getConfigProps() != null ){
			returnVal.put("configProps",productstoreshipmentmeth.getConfigProps());
}

		if(productstoreshipmentmeth.getShipmentCustomMethodId() != null ){
			returnVal.put("shipmentCustomMethodId",productstoreshipmentmeth.getShipmentCustomMethodId());
}

		if(productstoreshipmentmeth.getShipmentGatewayConfigId() != null ){
			returnVal.put("shipmentGatewayConfigId",productstoreshipmentmeth.getShipmentGatewayConfigId());
}

		if(productstoreshipmentmeth.getSequenceNumber() != null ){
			returnVal.put("sequenceNumber",productstoreshipmentmeth.getSequenceNumber());
}

		if(productstoreshipmentmeth.getAllowancePercent() != null ){
			returnVal.put("allowancePercent",productstoreshipmentmeth.getAllowancePercent());
}

		if(productstoreshipmentmeth.getMinimumPrice() != null ){
			returnVal.put("minimumPrice",productstoreshipmentmeth.getMinimumPrice());
}

		return returnVal;
}


	public static ProductStoreShipmentMeth map(Map<String, Object> fields) {

		ProductStoreShipmentMeth returnVal = new ProductStoreShipmentMeth();

		if(fields.get("productStoreShipMethId") != null) {
			returnVal.setProductStoreShipMethId((String) fields.get("productStoreShipMethId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("companyPartyId") != null) {
			returnVal.setCompanyPartyId((String) fields.get("companyPartyId"));
}

		if(fields.get("minWeight") != null) {
			returnVal.setMinWeight((BigDecimal) fields.get("minWeight"));
}

		if(fields.get("maxWeight") != null) {
			returnVal.setMaxWeight((BigDecimal) fields.get("maxWeight"));
}

		if(fields.get("minSize") != null) {
			returnVal.setMinSize((BigDecimal) fields.get("minSize"));
}

		if(fields.get("maxSize") != null) {
			returnVal.setMaxSize((BigDecimal) fields.get("maxSize"));
}

		if(fields.get("minTotal") != null) {
			returnVal.setMinTotal((BigDecimal) fields.get("minTotal"));
}

		if(fields.get("maxTotal") != null) {
			returnVal.setMaxTotal((BigDecimal) fields.get("maxTotal"));
}

		if(fields.get("allowUspsAddr") != null) {
			returnVal.setAllowUspsAddr((boolean) fields.get("allowUspsAddr"));
}

		if(fields.get("requireUspsAddr") != null) {
			returnVal.setRequireUspsAddr((boolean) fields.get("requireUspsAddr"));
}

		if(fields.get("allowCompanyAddr") != null) {
			returnVal.setAllowCompanyAddr((boolean) fields.get("allowCompanyAddr"));
}

		if(fields.get("requireCompanyAddr") != null) {
			returnVal.setRequireCompanyAddr((boolean) fields.get("requireCompanyAddr"));
}

		if(fields.get("includeNoChargeItems") != null) {
			returnVal.setIncludeNoChargeItems((boolean) fields.get("includeNoChargeItems"));
}

		if(fields.get("includeFeatureGroup") != null) {
			returnVal.setIncludeFeatureGroup((String) fields.get("includeFeatureGroup"));
}

		if(fields.get("excludeFeatureGroup") != null) {
			returnVal.setExcludeFeatureGroup((String) fields.get("excludeFeatureGroup"));
}

		if(fields.get("includeGeoId") != null) {
			returnVal.setIncludeGeoId((String) fields.get("includeGeoId"));
}

		if(fields.get("excludeGeoId") != null) {
			returnVal.setExcludeGeoId((String) fields.get("excludeGeoId"));
}

		if(fields.get("serviceName") != null) {
			returnVal.setServiceName((String) fields.get("serviceName"));
}

		if(fields.get("configProps") != null) {
			returnVal.setConfigProps((String) fields.get("configProps"));
}

		if(fields.get("shipmentCustomMethodId") != null) {
			returnVal.setShipmentCustomMethodId((String) fields.get("shipmentCustomMethodId"));
}

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("sequenceNumber") != null) {
			returnVal.setSequenceNumber((long) fields.get("sequenceNumber"));
}

		if(fields.get("allowancePercent") != null) {
			returnVal.setAllowancePercent((BigDecimal) fields.get("allowancePercent"));
}

		if(fields.get("minimumPrice") != null) {
			returnVal.setMinimumPrice((BigDecimal) fields.get("minimumPrice"));
}


		return returnVal;
 } 
	public static ProductStoreShipmentMeth mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreShipmentMeth returnVal = new ProductStoreShipmentMeth();

		if(fields.get("productStoreShipMethId") != null) {
			returnVal.setProductStoreShipMethId((String) fields.get("productStoreShipMethId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("companyPartyId") != null) {
			returnVal.setCompanyPartyId((String) fields.get("companyPartyId"));
}

		if(fields.get("minWeight") != null) {
String buf;
buf = fields.get("minWeight");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinWeight(bd);
}

		if(fields.get("maxWeight") != null) {
String buf;
buf = fields.get("maxWeight");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaxWeight(bd);
}

		if(fields.get("minSize") != null) {
String buf;
buf = fields.get("minSize");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinSize(bd);
}

		if(fields.get("maxSize") != null) {
String buf;
buf = fields.get("maxSize");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaxSize(bd);
}

		if(fields.get("minTotal") != null) {
String buf;
buf = fields.get("minTotal");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinTotal(bd);
}

		if(fields.get("maxTotal") != null) {
String buf;
buf = fields.get("maxTotal");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaxTotal(bd);
}

		if(fields.get("allowUspsAddr") != null) {
String buf;
buf = fields.get("allowUspsAddr");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAllowUspsAddr(ibuf);
}

		if(fields.get("requireUspsAddr") != null) {
String buf;
buf = fields.get("requireUspsAddr");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequireUspsAddr(ibuf);
}

		if(fields.get("allowCompanyAddr") != null) {
String buf;
buf = fields.get("allowCompanyAddr");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setAllowCompanyAddr(ibuf);
}

		if(fields.get("requireCompanyAddr") != null) {
String buf;
buf = fields.get("requireCompanyAddr");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequireCompanyAddr(ibuf);
}

		if(fields.get("includeNoChargeItems") != null) {
String buf;
buf = fields.get("includeNoChargeItems");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIncludeNoChargeItems(ibuf);
}

		if(fields.get("includeFeatureGroup") != null) {
			returnVal.setIncludeFeatureGroup((String) fields.get("includeFeatureGroup"));
}

		if(fields.get("excludeFeatureGroup") != null) {
			returnVal.setExcludeFeatureGroup((String) fields.get("excludeFeatureGroup"));
}

		if(fields.get("includeGeoId") != null) {
			returnVal.setIncludeGeoId((String) fields.get("includeGeoId"));
}

		if(fields.get("excludeGeoId") != null) {
			returnVal.setExcludeGeoId((String) fields.get("excludeGeoId"));
}

		if(fields.get("serviceName") != null) {
			returnVal.setServiceName((String) fields.get("serviceName"));
}

		if(fields.get("configProps") != null) {
			returnVal.setConfigProps((String) fields.get("configProps"));
}

		if(fields.get("shipmentCustomMethodId") != null) {
			returnVal.setShipmentCustomMethodId((String) fields.get("shipmentCustomMethodId"));
}

		if(fields.get("shipmentGatewayConfigId") != null) {
			returnVal.setShipmentGatewayConfigId((String) fields.get("shipmentGatewayConfigId"));
}

		if(fields.get("sequenceNumber") != null) {
String buf;
buf = fields.get("sequenceNumber");
long ibuf = Long.parseLong(buf);
			returnVal.setSequenceNumber(ibuf);
}

		if(fields.get("allowancePercent") != null) {
String buf;
buf = fields.get("allowancePercent");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAllowancePercent(bd);
}

		if(fields.get("minimumPrice") != null) {
String buf;
buf = fields.get("minimumPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinimumPrice(bd);
}


		return returnVal;
 } 
	public static ProductStoreShipmentMeth map(GenericValue val) {

ProductStoreShipmentMeth returnVal = new ProductStoreShipmentMeth();
		returnVal.setProductStoreShipMethId(val.getString("productStoreShipMethId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setShipmentMethodTypeId(val.getString("shipmentMethodTypeId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setCompanyPartyId(val.getString("companyPartyId"));
		returnVal.setMinWeight(val.getBigDecimal("minWeight"));
		returnVal.setMaxWeight(val.getBigDecimal("maxWeight"));
		returnVal.setMinSize(val.getBigDecimal("minSize"));
		returnVal.setMaxSize(val.getBigDecimal("maxSize"));
		returnVal.setMinTotal(val.getBigDecimal("minTotal"));
		returnVal.setMaxTotal(val.getBigDecimal("maxTotal"));
		returnVal.setAllowUspsAddr(val.getBoolean("allowUspsAddr"));
		returnVal.setRequireUspsAddr(val.getBoolean("requireUspsAddr"));
		returnVal.setAllowCompanyAddr(val.getBoolean("allowCompanyAddr"));
		returnVal.setRequireCompanyAddr(val.getBoolean("requireCompanyAddr"));
		returnVal.setIncludeNoChargeItems(val.getBoolean("includeNoChargeItems"));
		returnVal.setIncludeFeatureGroup(val.getString("includeFeatureGroup"));
		returnVal.setExcludeFeatureGroup(val.getString("excludeFeatureGroup"));
		returnVal.setIncludeGeoId(val.getString("includeGeoId"));
		returnVal.setExcludeGeoId(val.getString("excludeGeoId"));
		returnVal.setServiceName(val.getString("serviceName"));
		returnVal.setConfigProps(val.getString("configProps"));
		returnVal.setShipmentCustomMethodId(val.getString("shipmentCustomMethodId"));
		returnVal.setShipmentGatewayConfigId(val.getString("shipmentGatewayConfigId"));
		returnVal.setSequenceNumber(val.getLong("sequenceNumber"));
		returnVal.setAllowancePercent(val.getBigDecimal("allowancePercent"));
		returnVal.setMinimumPrice(val.getBigDecimal("minimumPrice"));


return returnVal;

}

public static ProductStoreShipmentMeth map(HttpServletRequest request) throws Exception {

		ProductStoreShipmentMeth returnVal = new ProductStoreShipmentMeth();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreShipMethId")) {
returnVal.setProductStoreShipMethId(request.getParameter("productStoreShipMethId"));
}

		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}
		if(paramMap.containsKey("shipmentMethodTypeId"))  {
returnVal.setShipmentMethodTypeId(request.getParameter("shipmentMethodTypeId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("companyPartyId"))  {
returnVal.setCompanyPartyId(request.getParameter("companyPartyId"));
}
		if(paramMap.containsKey("minWeight"))  {
String buf = request.getParameter("minWeight");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinWeight(bd);
}
		if(paramMap.containsKey("maxWeight"))  {
String buf = request.getParameter("maxWeight");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaxWeight(bd);
}
		if(paramMap.containsKey("minSize"))  {
String buf = request.getParameter("minSize");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinSize(bd);
}
		if(paramMap.containsKey("maxSize"))  {
String buf = request.getParameter("maxSize");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaxSize(bd);
}
		if(paramMap.containsKey("minTotal"))  {
String buf = request.getParameter("minTotal");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinTotal(bd);
}
		if(paramMap.containsKey("maxTotal"))  {
String buf = request.getParameter("maxTotal");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMaxTotal(bd);
}
		if(paramMap.containsKey("allowUspsAddr"))  {
String buf = request.getParameter("allowUspsAddr");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAllowUspsAddr(ibuf);
}
		if(paramMap.containsKey("requireUspsAddr"))  {
String buf = request.getParameter("requireUspsAddr");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setRequireUspsAddr(ibuf);
}
		if(paramMap.containsKey("allowCompanyAddr"))  {
String buf = request.getParameter("allowCompanyAddr");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setAllowCompanyAddr(ibuf);
}
		if(paramMap.containsKey("requireCompanyAddr"))  {
String buf = request.getParameter("requireCompanyAddr");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setRequireCompanyAddr(ibuf);
}
		if(paramMap.containsKey("includeNoChargeItems"))  {
String buf = request.getParameter("includeNoChargeItems");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setIncludeNoChargeItems(ibuf);
}
		if(paramMap.containsKey("includeFeatureGroup"))  {
returnVal.setIncludeFeatureGroup(request.getParameter("includeFeatureGroup"));
}
		if(paramMap.containsKey("excludeFeatureGroup"))  {
returnVal.setExcludeFeatureGroup(request.getParameter("excludeFeatureGroup"));
}
		if(paramMap.containsKey("includeGeoId"))  {
returnVal.setIncludeGeoId(request.getParameter("includeGeoId"));
}
		if(paramMap.containsKey("excludeGeoId"))  {
returnVal.setExcludeGeoId(request.getParameter("excludeGeoId"));
}
		if(paramMap.containsKey("serviceName"))  {
returnVal.setServiceName(request.getParameter("serviceName"));
}
		if(paramMap.containsKey("configProps"))  {
returnVal.setConfigProps(request.getParameter("configProps"));
}
		if(paramMap.containsKey("shipmentCustomMethodId"))  {
returnVal.setShipmentCustomMethodId(request.getParameter("shipmentCustomMethodId"));
}
		if(paramMap.containsKey("shipmentGatewayConfigId"))  {
returnVal.setShipmentGatewayConfigId(request.getParameter("shipmentGatewayConfigId"));
}
		if(paramMap.containsKey("sequenceNumber"))  {
String buf = request.getParameter("sequenceNumber");
Long ibuf = Long.parseLong(buf);
returnVal.setSequenceNumber(ibuf);
}
		if(paramMap.containsKey("allowancePercent"))  {
String buf = request.getParameter("allowancePercent");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAllowancePercent(bd);
}
		if(paramMap.containsKey("minimumPrice"))  {
String buf = request.getParameter("minimumPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setMinimumPrice(bd);
}
return returnVal;

}
}
