package com.skytala.eCommerce.domain.product.relations.facility.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facility.model.Facility;

public class FacilityMapper  {


	public static Map<String, Object> map(Facility facility) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facility.getFacilityId() != null ){
			returnVal.put("facilityId",facility.getFacilityId());
}

		if(facility.getFacilityTypeId() != null ){
			returnVal.put("facilityTypeId",facility.getFacilityTypeId());
}

		if(facility.getParentFacilityId() != null ){
			returnVal.put("parentFacilityId",facility.getParentFacilityId());
}

		if(facility.getOwnerPartyId() != null ){
			returnVal.put("ownerPartyId",facility.getOwnerPartyId());
}

		if(facility.getDefaultInventoryItemTypeId() != null ){
			returnVal.put("defaultInventoryItemTypeId",facility.getDefaultInventoryItemTypeId());
}

		if(facility.getFacilityName() != null ){
			returnVal.put("facilityName",facility.getFacilityName());
}

		if(facility.getPrimaryFacilityGroupId() != null ){
			returnVal.put("primaryFacilityGroupId",facility.getPrimaryFacilityGroupId());
}

		if(facility.getOldSquareFootage() != null ){
			returnVal.put("oldSquareFootage",facility.getOldSquareFootage());
}

		if(facility.getFacilitySize() != null ){
			returnVal.put("facilitySize",facility.getFacilitySize());
}

		if(facility.getFacilitySizeUomId() != null ){
			returnVal.put("facilitySizeUomId",facility.getFacilitySizeUomId());
}

		if(facility.getProductStoreId() != null ){
			returnVal.put("productStoreId",facility.getProductStoreId());
}

		if(facility.getDefaultDaysToShip() != null ){
			returnVal.put("defaultDaysToShip",facility.getDefaultDaysToShip());
}

		if(facility.getOpenedDate() != null ){
			returnVal.put("openedDate",facility.getOpenedDate());
}

		if(facility.getClosedDate() != null ){
			returnVal.put("closedDate",facility.getClosedDate());
}

		if(facility.getDescription() != null ){
			returnVal.put("description",facility.getDescription());
}

		if(facility.getDefaultDimensionUomId() != null ){
			returnVal.put("defaultDimensionUomId",facility.getDefaultDimensionUomId());
}

		if(facility.getDefaultWeightUomId() != null ){
			returnVal.put("defaultWeightUomId",facility.getDefaultWeightUomId());
}

		if(facility.getGeoPointId() != null ){
			returnVal.put("geoPointId",facility.getGeoPointId());
}

		return returnVal;
}


	public static Facility map(Map<String, Object> fields) {

		Facility returnVal = new Facility();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("facilityTypeId") != null) {
			returnVal.setFacilityTypeId((String) fields.get("facilityTypeId"));
}

		if(fields.get("parentFacilityId") != null) {
			returnVal.setParentFacilityId((String) fields.get("parentFacilityId"));
}

		if(fields.get("ownerPartyId") != null) {
			returnVal.setOwnerPartyId((String) fields.get("ownerPartyId"));
}

		if(fields.get("defaultInventoryItemTypeId") != null) {
			returnVal.setDefaultInventoryItemTypeId((String) fields.get("defaultInventoryItemTypeId"));
}

		if(fields.get("facilityName") != null) {
			returnVal.setFacilityName((String) fields.get("facilityName"));
}

		if(fields.get("primaryFacilityGroupId") != null) {
			returnVal.setPrimaryFacilityGroupId((String) fields.get("primaryFacilityGroupId"));
}

		if(fields.get("oldSquareFootage") != null) {
			returnVal.setOldSquareFootage((long) fields.get("oldSquareFootage"));
}

		if(fields.get("facilitySize") != null) {
			returnVal.setFacilitySize((BigDecimal) fields.get("facilitySize"));
}

		if(fields.get("facilitySizeUomId") != null) {
			returnVal.setFacilitySizeUomId((String) fields.get("facilitySizeUomId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("defaultDaysToShip") != null) {
			returnVal.setDefaultDaysToShip((long) fields.get("defaultDaysToShip"));
}

		if(fields.get("openedDate") != null) {
			returnVal.setOpenedDate((Timestamp) fields.get("openedDate"));
}

		if(fields.get("closedDate") != null) {
			returnVal.setClosedDate((Timestamp) fields.get("closedDate"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("defaultDimensionUomId") != null) {
			returnVal.setDefaultDimensionUomId((String) fields.get("defaultDimensionUomId"));
}

		if(fields.get("defaultWeightUomId") != null) {
			returnVal.setDefaultWeightUomId((String) fields.get("defaultWeightUomId"));
}

		if(fields.get("geoPointId") != null) {
			returnVal.setGeoPointId((String) fields.get("geoPointId"));
}


		return returnVal;
 } 
	public static Facility mapstrstr(Map<String, String> fields) throws Exception {

		Facility returnVal = new Facility();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("facilityTypeId") != null) {
			returnVal.setFacilityTypeId((String) fields.get("facilityTypeId"));
}

		if(fields.get("parentFacilityId") != null) {
			returnVal.setParentFacilityId((String) fields.get("parentFacilityId"));
}

		if(fields.get("ownerPartyId") != null) {
			returnVal.setOwnerPartyId((String) fields.get("ownerPartyId"));
}

		if(fields.get("defaultInventoryItemTypeId") != null) {
			returnVal.setDefaultInventoryItemTypeId((String) fields.get("defaultInventoryItemTypeId"));
}

		if(fields.get("facilityName") != null) {
			returnVal.setFacilityName((String) fields.get("facilityName"));
}

		if(fields.get("primaryFacilityGroupId") != null) {
			returnVal.setPrimaryFacilityGroupId((String) fields.get("primaryFacilityGroupId"));
}

		if(fields.get("oldSquareFootage") != null) {
String buf;
buf = fields.get("oldSquareFootage");
long ibuf = Long.parseLong(buf);
			returnVal.setOldSquareFootage(ibuf);
}

		if(fields.get("facilitySize") != null) {
String buf;
buf = fields.get("facilitySize");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFacilitySize(bd);
}

		if(fields.get("facilitySizeUomId") != null) {
			returnVal.setFacilitySizeUomId((String) fields.get("facilitySizeUomId"));
}

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("defaultDaysToShip") != null) {
String buf;
buf = fields.get("defaultDaysToShip");
long ibuf = Long.parseLong(buf);
			returnVal.setDefaultDaysToShip(ibuf);
}

		if(fields.get("openedDate") != null) {
String buf = fields.get("openedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setOpenedDate(ibuf);
}

		if(fields.get("closedDate") != null) {
String buf = fields.get("closedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setClosedDate(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("defaultDimensionUomId") != null) {
			returnVal.setDefaultDimensionUomId((String) fields.get("defaultDimensionUomId"));
}

		if(fields.get("defaultWeightUomId") != null) {
			returnVal.setDefaultWeightUomId((String) fields.get("defaultWeightUomId"));
}

		if(fields.get("geoPointId") != null) {
			returnVal.setGeoPointId((String) fields.get("geoPointId"));
}


		return returnVal;
 } 
	public static Facility map(GenericValue val) {

Facility returnVal = new Facility();
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setFacilityTypeId(val.getString("facilityTypeId"));
		returnVal.setParentFacilityId(val.getString("parentFacilityId"));
		returnVal.setOwnerPartyId(val.getString("ownerPartyId"));
		returnVal.setDefaultInventoryItemTypeId(val.getString("defaultInventoryItemTypeId"));
		returnVal.setFacilityName(val.getString("facilityName"));
		returnVal.setPrimaryFacilityGroupId(val.getString("primaryFacilityGroupId"));
		returnVal.setOldSquareFootage(val.getLong("oldSquareFootage"));
		returnVal.setFacilitySize(val.getBigDecimal("facilitySize"));
		returnVal.setFacilitySizeUomId(val.getString("facilitySizeUomId"));
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setDefaultDaysToShip(val.getLong("defaultDaysToShip"));
		returnVal.setOpenedDate(val.getTimestamp("openedDate"));
		returnVal.setClosedDate(val.getTimestamp("closedDate"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setDefaultDimensionUomId(val.getString("defaultDimensionUomId"));
		returnVal.setDefaultWeightUomId(val.getString("defaultWeightUomId"));
		returnVal.setGeoPointId(val.getString("geoPointId"));


return returnVal;

}

public static Facility map(HttpServletRequest request) throws Exception {

		Facility returnVal = new Facility();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityId")) {
returnVal.setFacilityId(request.getParameter("facilityId"));
}

		if(paramMap.containsKey("facilityTypeId"))  {
returnVal.setFacilityTypeId(request.getParameter("facilityTypeId"));
}
		if(paramMap.containsKey("parentFacilityId"))  {
returnVal.setParentFacilityId(request.getParameter("parentFacilityId"));
}
		if(paramMap.containsKey("ownerPartyId"))  {
returnVal.setOwnerPartyId(request.getParameter("ownerPartyId"));
}
		if(paramMap.containsKey("defaultInventoryItemTypeId"))  {
returnVal.setDefaultInventoryItemTypeId(request.getParameter("defaultInventoryItemTypeId"));
}
		if(paramMap.containsKey("facilityName"))  {
returnVal.setFacilityName(request.getParameter("facilityName"));
}
		if(paramMap.containsKey("primaryFacilityGroupId"))  {
returnVal.setPrimaryFacilityGroupId(request.getParameter("primaryFacilityGroupId"));
}
		if(paramMap.containsKey("oldSquareFootage"))  {
String buf = request.getParameter("oldSquareFootage");
Long ibuf = Long.parseLong(buf);
returnVal.setOldSquareFootage(ibuf);
}
		if(paramMap.containsKey("facilitySize"))  {
String buf = request.getParameter("facilitySize");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setFacilitySize(bd);
}
		if(paramMap.containsKey("facilitySizeUomId"))  {
returnVal.setFacilitySizeUomId(request.getParameter("facilitySizeUomId"));
}
		if(paramMap.containsKey("productStoreId"))  {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}
		if(paramMap.containsKey("defaultDaysToShip"))  {
String buf = request.getParameter("defaultDaysToShip");
Long ibuf = Long.parseLong(buf);
returnVal.setDefaultDaysToShip(ibuf);
}
		if(paramMap.containsKey("openedDate"))  {
String buf = request.getParameter("openedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setOpenedDate(ibuf);
}
		if(paramMap.containsKey("closedDate"))  {
String buf = request.getParameter("closedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setClosedDate(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("defaultDimensionUomId"))  {
returnVal.setDefaultDimensionUomId(request.getParameter("defaultDimensionUomId"));
}
		if(paramMap.containsKey("defaultWeightUomId"))  {
returnVal.setDefaultWeightUomId(request.getParameter("defaultWeightUomId"));
}
		if(paramMap.containsKey("geoPointId"))  {
returnVal.setGeoPointId(request.getParameter("geoPointId"));
}
return returnVal;

}
}
