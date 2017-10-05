package com.skytala.eCommerce.domain.shipmentCostEstimate.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipmentCostEstimate.model.ShipmentCostEstimate;

public class ShipmentCostEstimateMapper  {


	public static Map<String, Object> map(ShipmentCostEstimate shipmentcostestimate) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(shipmentcostestimate.getShipmentCostEstimateId() != null ){
			returnVal.put("shipmentCostEstimateId",shipmentcostestimate.getShipmentCostEstimateId());
}

		if(shipmentcostestimate.getShipmentMethodTypeId() != null ){
			returnVal.put("shipmentMethodTypeId",shipmentcostestimate.getShipmentMethodTypeId());
}

		if(shipmentcostestimate.getCarrierPartyId() != null ){
			returnVal.put("carrierPartyId",shipmentcostestimate.getCarrierPartyId());
}

		if(shipmentcostestimate.getCarrierRoleTypeId() != null ){
			returnVal.put("carrierRoleTypeId",shipmentcostestimate.getCarrierRoleTypeId());
}

		if(shipmentcostestimate.getProductStoreShipMethId() != null ){
			returnVal.put("productStoreShipMethId",shipmentcostestimate.getProductStoreShipMethId());
}

		if(shipmentcostestimate.getProductStoreId() != null ){
			returnVal.put("productStoreId",shipmentcostestimate.getProductStoreId());
}

		if(shipmentcostestimate.getPartyId() != null ){
			returnVal.put("partyId",shipmentcostestimate.getPartyId());
}

		if(shipmentcostestimate.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",shipmentcostestimate.getRoleTypeId());
}

		if(shipmentcostestimate.getGeoIdTo() != null ){
			returnVal.put("geoIdTo",shipmentcostestimate.getGeoIdTo());
}

		if(shipmentcostestimate.getGeoIdFrom() != null ){
			returnVal.put("geoIdFrom",shipmentcostestimate.getGeoIdFrom());
}

		if(shipmentcostestimate.getWeightBreakId() != null ){
			returnVal.put("weightBreakId",shipmentcostestimate.getWeightBreakId());
}

		if(shipmentcostestimate.getWeightUomId() != null ){
			returnVal.put("weightUomId",shipmentcostestimate.getWeightUomId());
}

		if(shipmentcostestimate.getWeightUnitPrice() != null ){
			returnVal.put("weightUnitPrice",shipmentcostestimate.getWeightUnitPrice());
}

		if(shipmentcostestimate.getQuantityBreakId() != null ){
			returnVal.put("quantityBreakId",shipmentcostestimate.getQuantityBreakId());
}

		if(shipmentcostestimate.getQuantityUomId() != null ){
			returnVal.put("quantityUomId",shipmentcostestimate.getQuantityUomId());
}

		if(shipmentcostestimate.getQuantityUnitPrice() != null ){
			returnVal.put("quantityUnitPrice",shipmentcostestimate.getQuantityUnitPrice());
}

		if(shipmentcostestimate.getPriceBreakId() != null ){
			returnVal.put("priceBreakId",shipmentcostestimate.getPriceBreakId());
}

		if(shipmentcostestimate.getPriceUomId() != null ){
			returnVal.put("priceUomId",shipmentcostestimate.getPriceUomId());
}

		if(shipmentcostestimate.getPriceUnitPrice() != null ){
			returnVal.put("priceUnitPrice",shipmentcostestimate.getPriceUnitPrice());
}

		if(shipmentcostestimate.getOrderFlatPrice() != null ){
			returnVal.put("orderFlatPrice",shipmentcostestimate.getOrderFlatPrice());
}

		if(shipmentcostestimate.getOrderPricePercent() != null ){
			returnVal.put("orderPricePercent",shipmentcostestimate.getOrderPricePercent());
}

		if(shipmentcostestimate.getOrderItemFlatPrice() != null ){
			returnVal.put("orderItemFlatPrice",shipmentcostestimate.getOrderItemFlatPrice());
}

		if(shipmentcostestimate.getShippingPricePercent() != null ){
			returnVal.put("shippingPricePercent",shipmentcostestimate.getShippingPricePercent());
}

		if(shipmentcostestimate.getProductFeatureGroupId() != null ){
			returnVal.put("productFeatureGroupId",shipmentcostestimate.getProductFeatureGroupId());
}

		if(shipmentcostestimate.getOversizeUnit() != null ){
			returnVal.put("oversizeUnit",shipmentcostestimate.getOversizeUnit());
}

		if(shipmentcostestimate.getOversizePrice() != null ){
			returnVal.put("oversizePrice",shipmentcostestimate.getOversizePrice());
}

		if(shipmentcostestimate.getFeaturePercent() != null ){
			returnVal.put("featurePercent",shipmentcostestimate.getFeaturePercent());
}

		if(shipmentcostestimate.getFeaturePrice() != null ){
			returnVal.put("featurePrice",shipmentcostestimate.getFeaturePrice());
}

		return returnVal;
}


	public static ShipmentCostEstimate map(Map<String, Object> fields) {

		ShipmentCostEstimate returnVal = new ShipmentCostEstimate();

		if(fields.get("shipmentCostEstimateId") != null) {
			returnVal.setShipmentCostEstimateId((String) fields.get("shipmentCostEstimateId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("carrierPartyId") != null) {
			returnVal.setCarrierPartyId((String) fields.get("carrierPartyId"));
}

		if(fields.get("carrierRoleTypeId") != null) {
			returnVal.setCarrierRoleTypeId((String) fields.get("carrierRoleTypeId"));
}

		if(fields.get("productStoreShipMethId") != null) {
			returnVal.setProductStoreShipMethId((String) fields.get("productStoreShipMethId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("geoIdTo") != null) {
			returnVal.setGeoIdTo((String) fields.get("geoIdTo"));
}

		if(fields.get("geoIdFrom") != null) {
			returnVal.setGeoIdFrom((String) fields.get("geoIdFrom"));
}

		if(fields.get("weightBreakId") != null) {
			returnVal.setWeightBreakId((String) fields.get("weightBreakId"));
}

		if(fields.get("weightUomId") != null) {
			returnVal.setWeightUomId((String) fields.get("weightUomId"));
}

		if(fields.get("weightUnitPrice") != null) {
			returnVal.setWeightUnitPrice((BigDecimal) fields.get("weightUnitPrice"));
}

		if(fields.get("quantityBreakId") != null) {
			returnVal.setQuantityBreakId((String) fields.get("quantityBreakId"));
}

		if(fields.get("quantityUomId") != null) {
			returnVal.setQuantityUomId((String) fields.get("quantityUomId"));
}

		if(fields.get("quantityUnitPrice") != null) {
			returnVal.setQuantityUnitPrice((BigDecimal) fields.get("quantityUnitPrice"));
}

		if(fields.get("priceBreakId") != null) {
			returnVal.setPriceBreakId((String) fields.get("priceBreakId"));
}

		if(fields.get("priceUomId") != null) {
			returnVal.setPriceUomId((String) fields.get("priceUomId"));
}

		if(fields.get("priceUnitPrice") != null) {
			returnVal.setPriceUnitPrice((BigDecimal) fields.get("priceUnitPrice"));
}

		if(fields.get("orderFlatPrice") != null) {
			returnVal.setOrderFlatPrice((BigDecimal) fields.get("orderFlatPrice"));
}

		if(fields.get("orderPricePercent") != null) {
			returnVal.setOrderPricePercent((BigDecimal) fields.get("orderPricePercent"));
}

		if(fields.get("orderItemFlatPrice") != null) {
			returnVal.setOrderItemFlatPrice((BigDecimal) fields.get("orderItemFlatPrice"));
}

		if(fields.get("shippingPricePercent") != null) {
			returnVal.setShippingPricePercent((BigDecimal) fields.get("shippingPricePercent"));
}

		if(fields.get("productFeatureGroupId") != null) {
			returnVal.setProductFeatureGroupId((String) fields.get("productFeatureGroupId"));
}

		if(fields.get("oversizeUnit") != null) {
			returnVal.setOversizeUnit((BigDecimal) fields.get("oversizeUnit"));
}

		if(fields.get("oversizePrice") != null) {
			returnVal.setOversizePrice((BigDecimal) fields.get("oversizePrice"));
}

		if(fields.get("featurePercent") != null) {
			returnVal.setFeaturePercent((BigDecimal) fields.get("featurePercent"));
}

		if(fields.get("featurePrice") != null) {
			returnVal.setFeaturePrice((BigDecimal) fields.get("featurePrice"));
}


		return returnVal;
 } 
	public static ShipmentCostEstimate mapstrstr(Map<String, String> fields) throws Exception {

		ShipmentCostEstimate returnVal = new ShipmentCostEstimate();

		if(fields.get("shipmentCostEstimateId") != null) {
			returnVal.setShipmentCostEstimateId((String) fields.get("shipmentCostEstimateId"));
}

		if(fields.get("shipmentMethodTypeId") != null) {
			returnVal.setShipmentMethodTypeId((String) fields.get("shipmentMethodTypeId"));
}

		if(fields.get("carrierPartyId") != null) {
			returnVal.setCarrierPartyId((String) fields.get("carrierPartyId"));
}

		if(fields.get("carrierRoleTypeId") != null) {
			returnVal.setCarrierRoleTypeId((String) fields.get("carrierRoleTypeId"));
}

		if(fields.get("productStoreShipMethId") != null) {
			returnVal.setProductStoreShipMethId((String) fields.get("productStoreShipMethId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("geoIdTo") != null) {
			returnVal.setGeoIdTo((String) fields.get("geoIdTo"));
}

		if(fields.get("geoIdFrom") != null) {
			returnVal.setGeoIdFrom((String) fields.get("geoIdFrom"));
}

		if(fields.get("weightBreakId") != null) {
			returnVal.setWeightBreakId((String) fields.get("weightBreakId"));
}

		if(fields.get("weightUomId") != null) {
			returnVal.setWeightUomId((String) fields.get("weightUomId"));
}

		if(fields.get("weightUnitPrice") != null) {
String buf;
buf = fields.get("weightUnitPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setWeightUnitPrice(bd);
}

		if(fields.get("quantityBreakId") != null) {
			returnVal.setQuantityBreakId((String) fields.get("quantityBreakId"));
}

		if(fields.get("quantityUomId") != null) {
			returnVal.setQuantityUomId((String) fields.get("quantityUomId"));
}

		if(fields.get("quantityUnitPrice") != null) {
String buf;
buf = fields.get("quantityUnitPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityUnitPrice(bd);
}

		if(fields.get("priceBreakId") != null) {
			returnVal.setPriceBreakId((String) fields.get("priceBreakId"));
}

		if(fields.get("priceUomId") != null) {
			returnVal.setPriceUomId((String) fields.get("priceUomId"));
}

		if(fields.get("priceUnitPrice") != null) {
String buf;
buf = fields.get("priceUnitPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPriceUnitPrice(bd);
}

		if(fields.get("orderFlatPrice") != null) {
String buf;
buf = fields.get("orderFlatPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOrderFlatPrice(bd);
}

		if(fields.get("orderPricePercent") != null) {
String buf;
buf = fields.get("orderPricePercent");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOrderPricePercent(bd);
}

		if(fields.get("orderItemFlatPrice") != null) {
String buf;
buf = fields.get("orderItemFlatPrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOrderItemFlatPrice(bd);
}

		if(fields.get("shippingPricePercent") != null) {
String buf;
buf = fields.get("shippingPricePercent");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setShippingPricePercent(bd);
}

		if(fields.get("productFeatureGroupId") != null) {
			returnVal.setProductFeatureGroupId((String) fields.get("productFeatureGroupId"));
}

		if(fields.get("oversizeUnit") != null) {
String buf;
buf = fields.get("oversizeUnit");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOversizeUnit(bd);
}

		if(fields.get("oversizePrice") != null) {
String buf;
buf = fields.get("oversizePrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOversizePrice(bd);
}

		if(fields.get("featurePercent") != null) {
String buf;
buf = fields.get("featurePercent");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFeaturePercent(bd);
}

		if(fields.get("featurePrice") != null) {
String buf;
buf = fields.get("featurePrice");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFeaturePrice(bd);
}


		return returnVal;
 } 
	public static ShipmentCostEstimate map(GenericValue val) {

ShipmentCostEstimate returnVal = new ShipmentCostEstimate();
		returnVal.setShipmentCostEstimateId(val.getString("shipmentCostEstimateId"));
		returnVal.setShipmentMethodTypeId(val.getString("shipmentMethodTypeId"));
		returnVal.setCarrierPartyId(val.getString("carrierPartyId"));
		returnVal.setCarrierRoleTypeId(val.getString("carrierRoleTypeId"));
		returnVal.setProductStoreShipMethId(val.getString("productStoreShipMethId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setGeoIdTo(val.getString("geoIdTo"));
		returnVal.setGeoIdFrom(val.getString("geoIdFrom"));
		returnVal.setWeightBreakId(val.getString("weightBreakId"));
		returnVal.setWeightUomId(val.getString("weightUomId"));
		returnVal.setWeightUnitPrice(val.getBigDecimal("weightUnitPrice"));
		returnVal.setQuantityBreakId(val.getString("quantityBreakId"));
		returnVal.setQuantityUomId(val.getString("quantityUomId"));
		returnVal.setQuantityUnitPrice(val.getBigDecimal("quantityUnitPrice"));
		returnVal.setPriceBreakId(val.getString("priceBreakId"));
		returnVal.setPriceUomId(val.getString("priceUomId"));
		returnVal.setPriceUnitPrice(val.getBigDecimal("priceUnitPrice"));
		returnVal.setOrderFlatPrice(val.getBigDecimal("orderFlatPrice"));
		returnVal.setOrderPricePercent(val.getBigDecimal("orderPricePercent"));
		returnVal.setOrderItemFlatPrice(val.getBigDecimal("orderItemFlatPrice"));
		returnVal.setShippingPricePercent(val.getBigDecimal("shippingPricePercent"));
		returnVal.setProductFeatureGroupId(val.getString("productFeatureGroupId"));
		returnVal.setOversizeUnit(val.getBigDecimal("oversizeUnit"));
		returnVal.setOversizePrice(val.getBigDecimal("oversizePrice"));
		returnVal.setFeaturePercent(val.getBigDecimal("featurePercent"));
		returnVal.setFeaturePrice(val.getBigDecimal("featurePrice"));


return returnVal;

}

public static ShipmentCostEstimate map(HttpServletRequest request) throws Exception {

		ShipmentCostEstimate returnVal = new ShipmentCostEstimate();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("shipmentCostEstimateId")) {
returnVal.setShipmentCostEstimateId(request.getParameter("shipmentCostEstimateId"));
}

		if(paramMap.containsKey("shipmentMethodTypeId"))  {
returnVal.setShipmentMethodTypeId(request.getParameter("shipmentMethodTypeId"));
}
		if(paramMap.containsKey("carrierPartyId"))  {
returnVal.setCarrierPartyId(request.getParameter("carrierPartyId"));
}
		if(paramMap.containsKey("carrierRoleTypeId"))  {
returnVal.setCarrierRoleTypeId(request.getParameter("carrierRoleTypeId"));
}
		if(paramMap.containsKey("productStoreShipMethId"))  {
returnVal.setProductStoreShipMethId(request.getParameter("productStoreShipMethId"));
}
		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("geoIdTo"))  {
returnVal.setGeoIdTo(request.getParameter("geoIdTo"));
}
		if(paramMap.containsKey("geoIdFrom"))  {
returnVal.setGeoIdFrom(request.getParameter("geoIdFrom"));
}
		if(paramMap.containsKey("weightBreakId"))  {
returnVal.setWeightBreakId(request.getParameter("weightBreakId"));
}
		if(paramMap.containsKey("weightUomId"))  {
returnVal.setWeightUomId(request.getParameter("weightUomId"));
}
		if(paramMap.containsKey("weightUnitPrice"))  {
String buf = request.getParameter("weightUnitPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setWeightUnitPrice(bd);
}
		if(paramMap.containsKey("quantityBreakId"))  {
returnVal.setQuantityBreakId(request.getParameter("quantityBreakId"));
}
		if(paramMap.containsKey("quantityUomId"))  {
returnVal.setQuantityUomId(request.getParameter("quantityUomId"));
}
		if(paramMap.containsKey("quantityUnitPrice"))  {
String buf = request.getParameter("quantityUnitPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityUnitPrice(bd);
}
		if(paramMap.containsKey("priceBreakId"))  {
returnVal.setPriceBreakId(request.getParameter("priceBreakId"));
}
		if(paramMap.containsKey("priceUomId"))  {
returnVal.setPriceUomId(request.getParameter("priceUomId"));
}
		if(paramMap.containsKey("priceUnitPrice"))  {
String buf = request.getParameter("priceUnitPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPriceUnitPrice(bd);
}
		if(paramMap.containsKey("orderFlatPrice"))  {
String buf = request.getParameter("orderFlatPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOrderFlatPrice(bd);
}
		if(paramMap.containsKey("orderPricePercent"))  {
String buf = request.getParameter("orderPricePercent");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOrderPricePercent(bd);
}
		if(paramMap.containsKey("orderItemFlatPrice"))  {
String buf = request.getParameter("orderItemFlatPrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOrderItemFlatPrice(bd);
}
		if(paramMap.containsKey("shippingPricePercent"))  {
String buf = request.getParameter("shippingPricePercent");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setShippingPricePercent(bd);
}
		if(paramMap.containsKey("productFeatureGroupId"))  {
returnVal.setProductFeatureGroupId(request.getParameter("productFeatureGroupId"));
}
		if(paramMap.containsKey("oversizeUnit"))  {
String buf = request.getParameter("oversizeUnit");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOversizeUnit(bd);
}
		if(paramMap.containsKey("oversizePrice"))  {
String buf = request.getParameter("oversizePrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOversizePrice(bd);
}
		if(paramMap.containsKey("featurePercent"))  {
String buf = request.getParameter("featurePercent");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFeaturePercent(bd);
}
		if(paramMap.containsKey("featurePrice"))  {
String buf = request.getParameter("featurePrice");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFeaturePrice(bd);
}
return returnVal;

}
}
