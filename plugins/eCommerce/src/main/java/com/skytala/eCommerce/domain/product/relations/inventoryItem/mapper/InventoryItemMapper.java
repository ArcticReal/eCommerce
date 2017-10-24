package com.skytala.eCommerce.domain.product.relations.inventoryItem.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.inventoryItem.model.InventoryItem;

public class InventoryItemMapper  {


	public static Map<String, Object> map(InventoryItem inventoryitem) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(inventoryitem.getInventoryItemId() != null ){
			returnVal.put("inventoryItemId",inventoryitem.getInventoryItemId());
}

		if(inventoryitem.getInventoryItemTypeId() != null ){
			returnVal.put("inventoryItemTypeId",inventoryitem.getInventoryItemTypeId());
}

		if(inventoryitem.getProductId() != null ){
			returnVal.put("productId",inventoryitem.getProductId());
}

		if(inventoryitem.getPartyId() != null ){
			returnVal.put("partyId",inventoryitem.getPartyId());
}

		if(inventoryitem.getOwnerPartyId() != null ){
			returnVal.put("ownerPartyId",inventoryitem.getOwnerPartyId());
}

		if(inventoryitem.getStatusId() != null ){
			returnVal.put("statusId",inventoryitem.getStatusId());
}

		if(inventoryitem.getDatetimeReceived() != null ){
			returnVal.put("datetimeReceived",inventoryitem.getDatetimeReceived());
}

		if(inventoryitem.getDatetimeManufactured() != null ){
			returnVal.put("datetimeManufactured",inventoryitem.getDatetimeManufactured());
}

		if(inventoryitem.getExpireDate() != null ){
			returnVal.put("expireDate",inventoryitem.getExpireDate());
}

		if(inventoryitem.getFacilityId() != null ){
			returnVal.put("facilityId",inventoryitem.getFacilityId());
}

		if(inventoryitem.getContainerId() != null ){
			returnVal.put("containerId",inventoryitem.getContainerId());
}

		if(inventoryitem.getLotId() != null ){
			returnVal.put("lotId",inventoryitem.getLotId());
}

		if(inventoryitem.getUomId() != null ){
			returnVal.put("uomId",inventoryitem.getUomId());
}

		if(inventoryitem.getBinNumber() != null ){
			returnVal.put("binNumber",inventoryitem.getBinNumber());
}

		if(inventoryitem.getLocationSeqId() != null ){
			returnVal.put("locationSeqId",inventoryitem.getLocationSeqId());
}

		if(inventoryitem.getComments() != null ){
			returnVal.put("comments",inventoryitem.getComments());
}

		if(inventoryitem.getQuantityOnHandTotal() != null ){
			returnVal.put("quantityOnHandTotal",inventoryitem.getQuantityOnHandTotal());
}

		if(inventoryitem.getAvailableToPromiseTotal() != null ){
			returnVal.put("availableToPromiseTotal",inventoryitem.getAvailableToPromiseTotal());
}

		if(inventoryitem.getAccountingQuantityTotal() != null ){
			returnVal.put("accountingQuantityTotal",inventoryitem.getAccountingQuantityTotal());
}

		if(inventoryitem.getOldQuantityOnHand() != null ){
			returnVal.put("oldQuantityOnHand",inventoryitem.getOldQuantityOnHand());
}

		if(inventoryitem.getOldAvailableToPromise() != null ){
			returnVal.put("oldAvailableToPromise",inventoryitem.getOldAvailableToPromise());
}

		if(inventoryitem.getSerialNumber() != null ){
			returnVal.put("serialNumber",inventoryitem.getSerialNumber());
}

		if(inventoryitem.getSoftIdentifier() != null ){
			returnVal.put("softIdentifier",inventoryitem.getSoftIdentifier());
}

		if(inventoryitem.getActivationNumber() != null ){
			returnVal.put("activationNumber",inventoryitem.getActivationNumber());
}

		if(inventoryitem.getActivationValidThru() != null ){
			returnVal.put("activationValidThru",inventoryitem.getActivationValidThru());
}

		if(inventoryitem.getUnitCost() != null ){
			returnVal.put("unitCost",inventoryitem.getUnitCost());
}

		if(inventoryitem.getCurrencyUomId() != null ){
			returnVal.put("currencyUomId",inventoryitem.getCurrencyUomId());
}

		return returnVal;
}


	public static InventoryItem map(Map<String, Object> fields) {

		InventoryItem returnVal = new InventoryItem();

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("inventoryItemTypeId") != null) {
			returnVal.setInventoryItemTypeId((String) fields.get("inventoryItemTypeId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("ownerPartyId") != null) {
			returnVal.setOwnerPartyId((String) fields.get("ownerPartyId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("datetimeReceived") != null) {
			returnVal.setDatetimeReceived((Timestamp) fields.get("datetimeReceived"));
}

		if(fields.get("datetimeManufactured") != null) {
			returnVal.setDatetimeManufactured((Timestamp) fields.get("datetimeManufactured"));
}

		if(fields.get("expireDate") != null) {
			returnVal.setExpireDate((Timestamp) fields.get("expireDate"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("containerId") != null) {
			returnVal.setContainerId((String) fields.get("containerId"));
}

		if(fields.get("lotId") != null) {
			returnVal.setLotId((String) fields.get("lotId"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("binNumber") != null) {
			returnVal.setBinNumber((String) fields.get("binNumber"));
}

		if(fields.get("locationSeqId") != null) {
			returnVal.setLocationSeqId((String) fields.get("locationSeqId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("quantityOnHandTotal") != null) {
			returnVal.setQuantityOnHandTotal((BigDecimal) fields.get("quantityOnHandTotal"));
}

		if(fields.get("availableToPromiseTotal") != null) {
			returnVal.setAvailableToPromiseTotal((BigDecimal) fields.get("availableToPromiseTotal"));
}

		if(fields.get("accountingQuantityTotal") != null) {
			returnVal.setAccountingQuantityTotal((BigDecimal) fields.get("accountingQuantityTotal"));
}

		if(fields.get("oldQuantityOnHand") != null) {
			returnVal.setOldQuantityOnHand((BigDecimal) fields.get("oldQuantityOnHand"));
}

		if(fields.get("oldAvailableToPromise") != null) {
			returnVal.setOldAvailableToPromise((BigDecimal) fields.get("oldAvailableToPromise"));
}

		if(fields.get("serialNumber") != null) {
			returnVal.setSerialNumber((String) fields.get("serialNumber"));
}

		if(fields.get("softIdentifier") != null) {
			returnVal.setSoftIdentifier((String) fields.get("softIdentifier"));
}

		if(fields.get("activationNumber") != null) {
			returnVal.setActivationNumber((String) fields.get("activationNumber"));
}

		if(fields.get("activationValidThru") != null) {
			returnVal.setActivationValidThru((Timestamp) fields.get("activationValidThru"));
}

		if(fields.get("unitCost") != null) {
			returnVal.setUnitCost((BigDecimal) fields.get("unitCost"));
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}


		return returnVal;
 } 
	public static InventoryItem mapstrstr(Map<String, String> fields) throws Exception {

		InventoryItem returnVal = new InventoryItem();

		if(fields.get("inventoryItemId") != null) {
			returnVal.setInventoryItemId((String) fields.get("inventoryItemId"));
}

		if(fields.get("inventoryItemTypeId") != null) {
			returnVal.setInventoryItemTypeId((String) fields.get("inventoryItemTypeId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("ownerPartyId") != null) {
			returnVal.setOwnerPartyId((String) fields.get("ownerPartyId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("datetimeReceived") != null) {
String buf = fields.get("datetimeReceived");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDatetimeReceived(ibuf);
}

		if(fields.get("datetimeManufactured") != null) {
String buf = fields.get("datetimeManufactured");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDatetimeManufactured(ibuf);
}

		if(fields.get("expireDate") != null) {
String buf = fields.get("expireDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setExpireDate(ibuf);
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("containerId") != null) {
			returnVal.setContainerId((String) fields.get("containerId"));
}

		if(fields.get("lotId") != null) {
			returnVal.setLotId((String) fields.get("lotId"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("binNumber") != null) {
			returnVal.setBinNumber((String) fields.get("binNumber"));
}

		if(fields.get("locationSeqId") != null) {
			returnVal.setLocationSeqId((String) fields.get("locationSeqId"));
}

		if(fields.get("comments") != null) {
			returnVal.setComments((String) fields.get("comments"));
}

		if(fields.get("quantityOnHandTotal") != null) {
String buf;
buf = fields.get("quantityOnHandTotal");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityOnHandTotal(bd);
}

		if(fields.get("availableToPromiseTotal") != null) {
String buf;
buf = fields.get("availableToPromiseTotal");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAvailableToPromiseTotal(bd);
}

		if(fields.get("accountingQuantityTotal") != null) {
String buf;
buf = fields.get("accountingQuantityTotal");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAccountingQuantityTotal(bd);
}

		if(fields.get("oldQuantityOnHand") != null) {
String buf;
buf = fields.get("oldQuantityOnHand");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOldQuantityOnHand(bd);
}

		if(fields.get("oldAvailableToPromise") != null) {
String buf;
buf = fields.get("oldAvailableToPromise");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOldAvailableToPromise(bd);
}

		if(fields.get("serialNumber") != null) {
			returnVal.setSerialNumber((String) fields.get("serialNumber"));
}

		if(fields.get("softIdentifier") != null) {
			returnVal.setSoftIdentifier((String) fields.get("softIdentifier"));
}

		if(fields.get("activationNumber") != null) {
			returnVal.setActivationNumber((String) fields.get("activationNumber"));
}

		if(fields.get("activationValidThru") != null) {
String buf = fields.get("activationValidThru");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setActivationValidThru(ibuf);
}

		if(fields.get("unitCost") != null) {
String buf;
buf = fields.get("unitCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitCost(bd);
}

		if(fields.get("currencyUomId") != null) {
			returnVal.setCurrencyUomId((String) fields.get("currencyUomId"));
}


		return returnVal;
 } 
	public static InventoryItem map(GenericValue val) {

InventoryItem returnVal = new InventoryItem();
		returnVal.setInventoryItemId(val.getString("inventoryItemId"));
		returnVal.setInventoryItemTypeId(val.getString("inventoryItemTypeId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setOwnerPartyId(val.getString("ownerPartyId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setDatetimeReceived(val.getTimestamp("datetimeReceived"));
		returnVal.setDatetimeManufactured(val.getTimestamp("datetimeManufactured"));
		returnVal.setExpireDate(val.getTimestamp("expireDate"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setContainerId(val.getString("containerId"));
		returnVal.setLotId(val.getString("lotId"));
		returnVal.setUomId(val.getString("uomId"));
		returnVal.setBinNumber(val.getString("binNumber"));
		returnVal.setLocationSeqId(val.getString("locationSeqId"));
		returnVal.setComments(val.getString("comments"));
		returnVal.setQuantityOnHandTotal(val.getBigDecimal("quantityOnHandTotal"));
		returnVal.setAvailableToPromiseTotal(val.getBigDecimal("availableToPromiseTotal"));
		returnVal.setAccountingQuantityTotal(val.getBigDecimal("accountingQuantityTotal"));
		returnVal.setOldQuantityOnHand(val.getBigDecimal("oldQuantityOnHand"));
		returnVal.setOldAvailableToPromise(val.getBigDecimal("oldAvailableToPromise"));
		returnVal.setSerialNumber(val.getString("serialNumber"));
		returnVal.setSoftIdentifier(val.getString("softIdentifier"));
		returnVal.setActivationNumber(val.getString("activationNumber"));
		returnVal.setActivationValidThru(val.getTimestamp("activationValidThru"));
		returnVal.setUnitCost(val.getBigDecimal("unitCost"));
		returnVal.setCurrencyUomId(val.getString("currencyUomId"));


return returnVal;

}

public static InventoryItem map(HttpServletRequest request) throws Exception {

		InventoryItem returnVal = new InventoryItem();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("inventoryItemId")) {
returnVal.setInventoryItemId(request.getParameter("inventoryItemId"));
}

		if(paramMap.containsKey("inventoryItemTypeId"))  {
returnVal.setInventoryItemTypeId(request.getParameter("inventoryItemTypeId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("ownerPartyId"))  {
returnVal.setOwnerPartyId(request.getParameter("ownerPartyId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("datetimeReceived"))  {
String buf = request.getParameter("datetimeReceived");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDatetimeReceived(ibuf);
}
		if(paramMap.containsKey("datetimeManufactured"))  {
String buf = request.getParameter("datetimeManufactured");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDatetimeManufactured(ibuf);
}
		if(paramMap.containsKey("expireDate"))  {
String buf = request.getParameter("expireDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setExpireDate(ibuf);
}
		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("containerId"))  {
returnVal.setContainerId(request.getParameter("containerId"));
}
		if(paramMap.containsKey("lotId"))  {
returnVal.setLotId(request.getParameter("lotId"));
}
		if(paramMap.containsKey("uomId"))  {
returnVal.setUomId(request.getParameter("uomId"));
}
		if(paramMap.containsKey("binNumber"))  {
returnVal.setBinNumber(request.getParameter("binNumber"));
}
		if(paramMap.containsKey("locationSeqId"))  {
returnVal.setLocationSeqId(request.getParameter("locationSeqId"));
}
		if(paramMap.containsKey("comments"))  {
returnVal.setComments(request.getParameter("comments"));
}
		if(paramMap.containsKey("quantityOnHandTotal"))  {
String buf = request.getParameter("quantityOnHandTotal");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityOnHandTotal(bd);
}
		if(paramMap.containsKey("availableToPromiseTotal"))  {
String buf = request.getParameter("availableToPromiseTotal");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAvailableToPromiseTotal(bd);
}
		if(paramMap.containsKey("accountingQuantityTotal"))  {
String buf = request.getParameter("accountingQuantityTotal");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setAccountingQuantityTotal(bd);
}
		if(paramMap.containsKey("oldQuantityOnHand"))  {
String buf = request.getParameter("oldQuantityOnHand");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOldQuantityOnHand(bd);
}
		if(paramMap.containsKey("oldAvailableToPromise"))  {
String buf = request.getParameter("oldAvailableToPromise");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOldAvailableToPromise(bd);
}
		if(paramMap.containsKey("serialNumber"))  {
returnVal.setSerialNumber(request.getParameter("serialNumber"));
}
		if(paramMap.containsKey("softIdentifier"))  {
returnVal.setSoftIdentifier(request.getParameter("softIdentifier"));
}
		if(paramMap.containsKey("activationNumber"))  {
returnVal.setActivationNumber(request.getParameter("activationNumber"));
}
		if(paramMap.containsKey("activationValidThru"))  {
String buf = request.getParameter("activationValidThru");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setActivationValidThru(ibuf);
}
		if(paramMap.containsKey("unitCost"))  {
String buf = request.getParameter("unitCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setUnitCost(bd);
}
		if(paramMap.containsKey("currencyUomId"))  {
returnVal.setCurrencyUomId(request.getParameter("currencyUomId"));
}
return returnVal;

}
}
