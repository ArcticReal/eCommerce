package com.skytala.eCommerce.domain.fixedAsset.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.fixedAsset.model.FixedAsset;

public class FixedAssetMapper  {


	public static Map<String, Object> map(FixedAsset fixedasset) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedasset.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedasset.getFixedAssetId());
}

		if(fixedasset.getFixedAssetTypeId() != null ){
			returnVal.put("fixedAssetTypeId",fixedasset.getFixedAssetTypeId());
}

		if(fixedasset.getParentFixedAssetId() != null ){
			returnVal.put("parentFixedAssetId",fixedasset.getParentFixedAssetId());
}

		if(fixedasset.getInstanceOfProductId() != null ){
			returnVal.put("instanceOfProductId",fixedasset.getInstanceOfProductId());
}

		if(fixedasset.getClassEnumId() != null ){
			returnVal.put("classEnumId",fixedasset.getClassEnumId());
}

		if(fixedasset.getPartyId() != null ){
			returnVal.put("partyId",fixedasset.getPartyId());
}

		if(fixedasset.getRoleTypeId() != null ){
			returnVal.put("roleTypeId",fixedasset.getRoleTypeId());
}

		if(fixedasset.getFixedAssetName() != null ){
			returnVal.put("fixedAssetName",fixedasset.getFixedAssetName());
}

		if(fixedasset.getAcquireOrderId() != null ){
			returnVal.put("acquireOrderId",fixedasset.getAcquireOrderId());
}

		if(fixedasset.getAcquireOrderItemSeqId() != null ){
			returnVal.put("acquireOrderItemSeqId",fixedasset.getAcquireOrderItemSeqId());
}

		if(fixedasset.getDateAcquired() != null ){
			returnVal.put("dateAcquired",fixedasset.getDateAcquired());
}

		if(fixedasset.getDateLastServiced() != null ){
			returnVal.put("dateLastServiced",fixedasset.getDateLastServiced());
}

		if(fixedasset.getDateNextService() != null ){
			returnVal.put("dateNextService",fixedasset.getDateNextService());
}

		if(fixedasset.getExpectedEndOfLife() != null ){
			returnVal.put("expectedEndOfLife",fixedasset.getExpectedEndOfLife());
}

		if(fixedasset.getActualEndOfLife() != null ){
			returnVal.put("actualEndOfLife",fixedasset.getActualEndOfLife());
}

		if(fixedasset.getProductionCapacity() != null ){
			returnVal.put("productionCapacity",fixedasset.getProductionCapacity());
}

		if(fixedasset.getUomId() != null ){
			returnVal.put("uomId",fixedasset.getUomId());
}

		if(fixedasset.getCalendarId() != null ){
			returnVal.put("calendarId",fixedasset.getCalendarId());
}

		if(fixedasset.getSerialNumber() != null ){
			returnVal.put("serialNumber",fixedasset.getSerialNumber());
}

		if(fixedasset.getLocatedAtFacilityId() != null ){
			returnVal.put("locatedAtFacilityId",fixedasset.getLocatedAtFacilityId());
}

		if(fixedasset.getLocatedAtLocationSeqId() != null ){
			returnVal.put("locatedAtLocationSeqId",fixedasset.getLocatedAtLocationSeqId());
}

		if(fixedasset.getSalvageValue() != null ){
			returnVal.put("salvageValue",fixedasset.getSalvageValue());
}

		if(fixedasset.getDepreciation() != null ){
			returnVal.put("depreciation",fixedasset.getDepreciation());
}

		if(fixedasset.getPurchaseCost() != null ){
			returnVal.put("purchaseCost",fixedasset.getPurchaseCost());
}

		if(fixedasset.getPurchaseCostUomId() != null ){
			returnVal.put("purchaseCostUomId",fixedasset.getPurchaseCostUomId());
}

		return returnVal;
}


	public static FixedAsset map(Map<String, Object> fields) {

		FixedAsset returnVal = new FixedAsset();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("fixedAssetTypeId") != null) {
			returnVal.setFixedAssetTypeId((String) fields.get("fixedAssetTypeId"));
}

		if(fields.get("parentFixedAssetId") != null) {
			returnVal.setParentFixedAssetId((String) fields.get("parentFixedAssetId"));
}

		if(fields.get("instanceOfProductId") != null) {
			returnVal.setInstanceOfProductId((String) fields.get("instanceOfProductId"));
}

		if(fields.get("classEnumId") != null) {
			returnVal.setClassEnumId((String) fields.get("classEnumId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("fixedAssetName") != null) {
			returnVal.setFixedAssetName((String) fields.get("fixedAssetName"));
}

		if(fields.get("acquireOrderId") != null) {
			returnVal.setAcquireOrderId((String) fields.get("acquireOrderId"));
}

		if(fields.get("acquireOrderItemSeqId") != null) {
			returnVal.setAcquireOrderItemSeqId((String) fields.get("acquireOrderItemSeqId"));
}

		if(fields.get("dateAcquired") != null) {
			returnVal.setDateAcquired((Timestamp) fields.get("dateAcquired"));
}

		if(fields.get("dateLastServiced") != null) {
			returnVal.setDateLastServiced((Timestamp) fields.get("dateLastServiced"));
}

		if(fields.get("dateNextService") != null) {
			returnVal.setDateNextService((Timestamp) fields.get("dateNextService"));
}

		if(fields.get("expectedEndOfLife") != null) {
			returnVal.setExpectedEndOfLife((Timestamp) fields.get("expectedEndOfLife"));
}

		if(fields.get("actualEndOfLife") != null) {
			returnVal.setActualEndOfLife((Timestamp) fields.get("actualEndOfLife"));
}

		if(fields.get("productionCapacity") != null) {
			returnVal.setProductionCapacity((BigDecimal) fields.get("productionCapacity"));
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("calendarId") != null) {
			returnVal.setCalendarId((String) fields.get("calendarId"));
}

		if(fields.get("serialNumber") != null) {
			returnVal.setSerialNumber((String) fields.get("serialNumber"));
}

		if(fields.get("locatedAtFacilityId") != null) {
			returnVal.setLocatedAtFacilityId((String) fields.get("locatedAtFacilityId"));
}

		if(fields.get("locatedAtLocationSeqId") != null) {
			returnVal.setLocatedAtLocationSeqId((String) fields.get("locatedAtLocationSeqId"));
}

		if(fields.get("salvageValue") != null) {
			returnVal.setSalvageValue((BigDecimal) fields.get("salvageValue"));
}

		if(fields.get("depreciation") != null) {
			returnVal.setDepreciation((BigDecimal) fields.get("depreciation"));
}

		if(fields.get("purchaseCost") != null) {
			returnVal.setPurchaseCost((BigDecimal) fields.get("purchaseCost"));
}

		if(fields.get("purchaseCostUomId") != null) {
			returnVal.setPurchaseCostUomId((String) fields.get("purchaseCostUomId"));
}


		return returnVal;
 } 
	public static FixedAsset mapstrstr(Map<String, String> fields) throws Exception {

		FixedAsset returnVal = new FixedAsset();

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("fixedAssetTypeId") != null) {
			returnVal.setFixedAssetTypeId((String) fields.get("fixedAssetTypeId"));
}

		if(fields.get("parentFixedAssetId") != null) {
			returnVal.setParentFixedAssetId((String) fields.get("parentFixedAssetId"));
}

		if(fields.get("instanceOfProductId") != null) {
			returnVal.setInstanceOfProductId((String) fields.get("instanceOfProductId"));
}

		if(fields.get("classEnumId") != null) {
			returnVal.setClassEnumId((String) fields.get("classEnumId"));
}

		if(fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
}

		if(fields.get("roleTypeId") != null) {
			returnVal.setRoleTypeId((String) fields.get("roleTypeId"));
}

		if(fields.get("fixedAssetName") != null) {
			returnVal.setFixedAssetName((String) fields.get("fixedAssetName"));
}

		if(fields.get("acquireOrderId") != null) {
			returnVal.setAcquireOrderId((String) fields.get("acquireOrderId"));
}

		if(fields.get("acquireOrderItemSeqId") != null) {
			returnVal.setAcquireOrderItemSeqId((String) fields.get("acquireOrderItemSeqId"));
}

		if(fields.get("dateAcquired") != null) {
String buf = fields.get("dateAcquired");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDateAcquired(ibuf);
}

		if(fields.get("dateLastServiced") != null) {
String buf = fields.get("dateLastServiced");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDateLastServiced(ibuf);
}

		if(fields.get("dateNextService") != null) {
String buf = fields.get("dateNextService");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDateNextService(ibuf);
}

		if(fields.get("expectedEndOfLife") != null) {
String buf = fields.get("expectedEndOfLife");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setExpectedEndOfLife(ibuf);
}

		if(fields.get("actualEndOfLife") != null) {
String buf = fields.get("actualEndOfLife");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setActualEndOfLife(ibuf);
}

		if(fields.get("productionCapacity") != null) {
String buf;
buf = fields.get("productionCapacity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductionCapacity(bd);
}

		if(fields.get("uomId") != null) {
			returnVal.setUomId((String) fields.get("uomId"));
}

		if(fields.get("calendarId") != null) {
			returnVal.setCalendarId((String) fields.get("calendarId"));
}

		if(fields.get("serialNumber") != null) {
			returnVal.setSerialNumber((String) fields.get("serialNumber"));
}

		if(fields.get("locatedAtFacilityId") != null) {
			returnVal.setLocatedAtFacilityId((String) fields.get("locatedAtFacilityId"));
}

		if(fields.get("locatedAtLocationSeqId") != null) {
			returnVal.setLocatedAtLocationSeqId((String) fields.get("locatedAtLocationSeqId"));
}

		if(fields.get("salvageValue") != null) {
String buf;
buf = fields.get("salvageValue");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSalvageValue(bd);
}

		if(fields.get("depreciation") != null) {
String buf;
buf = fields.get("depreciation");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setDepreciation(bd);
}

		if(fields.get("purchaseCost") != null) {
String buf;
buf = fields.get("purchaseCost");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPurchaseCost(bd);
}

		if(fields.get("purchaseCostUomId") != null) {
			returnVal.setPurchaseCostUomId((String) fields.get("purchaseCostUomId"));
}


		return returnVal;
 } 
	public static FixedAsset map(GenericValue val) {

FixedAsset returnVal = new FixedAsset();
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setFixedAssetTypeId(val.getString("fixedAssetTypeId"));
		returnVal.setParentFixedAssetId(val.getString("parentFixedAssetId"));
		returnVal.setInstanceOfProductId(val.getString("instanceOfProductId"));
		returnVal.setClassEnumId(val.getString("classEnumId"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setRoleTypeId(val.getString("roleTypeId"));
		returnVal.setFixedAssetName(val.getString("fixedAssetName"));
		returnVal.setAcquireOrderId(val.getString("acquireOrderId"));
		returnVal.setAcquireOrderItemSeqId(val.getString("acquireOrderItemSeqId"));
		returnVal.setDateAcquired(val.getTimestamp("dateAcquired"));
		returnVal.setDateLastServiced(val.getTimestamp("dateLastServiced"));
		returnVal.setDateNextService(val.getTimestamp("dateNextService"));
		returnVal.setExpectedEndOfLife(val.getTimestamp("expectedEndOfLife"));
		returnVal.setActualEndOfLife(val.getTimestamp("actualEndOfLife"));
		returnVal.setProductionCapacity(val.getBigDecimal("productionCapacity"));
		returnVal.setUomId(val.getString("uomId"));
		returnVal.setCalendarId(val.getString("calendarId"));
		returnVal.setSerialNumber(val.getString("serialNumber"));
		returnVal.setLocatedAtFacilityId(val.getString("locatedAtFacilityId"));
		returnVal.setLocatedAtLocationSeqId(val.getString("locatedAtLocationSeqId"));
		returnVal.setSalvageValue(val.getBigDecimal("salvageValue"));
		returnVal.setDepreciation(val.getBigDecimal("depreciation"));
		returnVal.setPurchaseCost(val.getBigDecimal("purchaseCost"));
		returnVal.setPurchaseCostUomId(val.getString("purchaseCostUomId"));


return returnVal;

}

public static FixedAsset map(HttpServletRequest request) throws Exception {

		FixedAsset returnVal = new FixedAsset();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetId")) {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}

		if(paramMap.containsKey("fixedAssetTypeId"))  {
returnVal.setFixedAssetTypeId(request.getParameter("fixedAssetTypeId"));
}
		if(paramMap.containsKey("parentFixedAssetId"))  {
returnVal.setParentFixedAssetId(request.getParameter("parentFixedAssetId"));
}
		if(paramMap.containsKey("instanceOfProductId"))  {
returnVal.setInstanceOfProductId(request.getParameter("instanceOfProductId"));
}
		if(paramMap.containsKey("classEnumId"))  {
returnVal.setClassEnumId(request.getParameter("classEnumId"));
}
		if(paramMap.containsKey("partyId"))  {
returnVal.setPartyId(request.getParameter("partyId"));
}
		if(paramMap.containsKey("roleTypeId"))  {
returnVal.setRoleTypeId(request.getParameter("roleTypeId"));
}
		if(paramMap.containsKey("fixedAssetName"))  {
returnVal.setFixedAssetName(request.getParameter("fixedAssetName"));
}
		if(paramMap.containsKey("acquireOrderId"))  {
returnVal.setAcquireOrderId(request.getParameter("acquireOrderId"));
}
		if(paramMap.containsKey("acquireOrderItemSeqId"))  {
returnVal.setAcquireOrderItemSeqId(request.getParameter("acquireOrderItemSeqId"));
}
		if(paramMap.containsKey("dateAcquired"))  {
String buf = request.getParameter("dateAcquired");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDateAcquired(ibuf);
}
		if(paramMap.containsKey("dateLastServiced"))  {
String buf = request.getParameter("dateLastServiced");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDateLastServiced(ibuf);
}
		if(paramMap.containsKey("dateNextService"))  {
String buf = request.getParameter("dateNextService");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setDateNextService(ibuf);
}
		if(paramMap.containsKey("expectedEndOfLife"))  {
String buf = request.getParameter("expectedEndOfLife");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setExpectedEndOfLife(ibuf);
}
		if(paramMap.containsKey("actualEndOfLife"))  {
String buf = request.getParameter("actualEndOfLife");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setActualEndOfLife(ibuf);
}
		if(paramMap.containsKey("productionCapacity"))  {
String buf = request.getParameter("productionCapacity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setProductionCapacity(bd);
}
		if(paramMap.containsKey("uomId"))  {
returnVal.setUomId(request.getParameter("uomId"));
}
		if(paramMap.containsKey("calendarId"))  {
returnVal.setCalendarId(request.getParameter("calendarId"));
}
		if(paramMap.containsKey("serialNumber"))  {
returnVal.setSerialNumber(request.getParameter("serialNumber"));
}
		if(paramMap.containsKey("locatedAtFacilityId"))  {
returnVal.setLocatedAtFacilityId(request.getParameter("locatedAtFacilityId"));
}
		if(paramMap.containsKey("locatedAtLocationSeqId"))  {
returnVal.setLocatedAtLocationSeqId(request.getParameter("locatedAtLocationSeqId"));
}
		if(paramMap.containsKey("salvageValue"))  {
String buf = request.getParameter("salvageValue");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setSalvageValue(bd);
}
		if(paramMap.containsKey("depreciation"))  {
String buf = request.getParameter("depreciation");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setDepreciation(bd);
}
		if(paramMap.containsKey("purchaseCost"))  {
String buf = request.getParameter("purchaseCost");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setPurchaseCost(bd);
}
		if(paramMap.containsKey("purchaseCostUomId"))  {
returnVal.setPurchaseCostUomId(request.getParameter("purchaseCostUomId"));
}
return returnVal;

}
}
