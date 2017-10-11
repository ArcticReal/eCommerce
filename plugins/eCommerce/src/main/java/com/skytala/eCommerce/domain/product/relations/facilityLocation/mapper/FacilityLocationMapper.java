package com.skytala.eCommerce.domain.product.relations.facilityLocation.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityLocation.model.FacilityLocation;

public class FacilityLocationMapper  {


	public static Map<String, Object> map(FacilityLocation facilitylocation) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitylocation.getFacilityId() != null ){
			returnVal.put("facilityId",facilitylocation.getFacilityId());
}

		if(facilitylocation.getLocationSeqId() != null ){
			returnVal.put("locationSeqId",facilitylocation.getLocationSeqId());
}

		if(facilitylocation.getLocationTypeEnumId() != null ){
			returnVal.put("locationTypeEnumId",facilitylocation.getLocationTypeEnumId());
}

		if(facilitylocation.getAreaId() != null ){
			returnVal.put("areaId",facilitylocation.getAreaId());
}

		if(facilitylocation.getAisleId() != null ){
			returnVal.put("aisleId",facilitylocation.getAisleId());
}

		if(facilitylocation.getSectionId() != null ){
			returnVal.put("sectionId",facilitylocation.getSectionId());
}

		if(facilitylocation.getLevelId() != null ){
			returnVal.put("levelId",facilitylocation.getLevelId());
}

		if(facilitylocation.getPositionId() != null ){
			returnVal.put("positionId",facilitylocation.getPositionId());
}

		if(facilitylocation.getGeoPointId() != null ){
			returnVal.put("geoPointId",facilitylocation.getGeoPointId());
}

		return returnVal;
}


	public static FacilityLocation map(Map<String, Object> fields) {

		FacilityLocation returnVal = new FacilityLocation();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("locationSeqId") != null) {
			returnVal.setLocationSeqId((String) fields.get("locationSeqId"));
}

		if(fields.get("locationTypeEnumId") != null) {
			returnVal.setLocationTypeEnumId((String) fields.get("locationTypeEnumId"));
}

		if(fields.get("areaId") != null) {
			returnVal.setAreaId((String) fields.get("areaId"));
}

		if(fields.get("aisleId") != null) {
			returnVal.setAisleId((String) fields.get("aisleId"));
}

		if(fields.get("sectionId") != null) {
			returnVal.setSectionId((String) fields.get("sectionId"));
}

		if(fields.get("levelId") != null) {
			returnVal.setLevelId((String) fields.get("levelId"));
}

		if(fields.get("positionId") != null) {
			returnVal.setPositionId((String) fields.get("positionId"));
}

		if(fields.get("geoPointId") != null) {
			returnVal.setGeoPointId((String) fields.get("geoPointId"));
}


		return returnVal;
 } 
	public static FacilityLocation mapstrstr(Map<String, String> fields) throws Exception {

		FacilityLocation returnVal = new FacilityLocation();

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("locationSeqId") != null) {
			returnVal.setLocationSeqId((String) fields.get("locationSeqId"));
}

		if(fields.get("locationTypeEnumId") != null) {
			returnVal.setLocationTypeEnumId((String) fields.get("locationTypeEnumId"));
}

		if(fields.get("areaId") != null) {
			returnVal.setAreaId((String) fields.get("areaId"));
}

		if(fields.get("aisleId") != null) {
			returnVal.setAisleId((String) fields.get("aisleId"));
}

		if(fields.get("sectionId") != null) {
			returnVal.setSectionId((String) fields.get("sectionId"));
}

		if(fields.get("levelId") != null) {
			returnVal.setLevelId((String) fields.get("levelId"));
}

		if(fields.get("positionId") != null) {
			returnVal.setPositionId((String) fields.get("positionId"));
}

		if(fields.get("geoPointId") != null) {
			returnVal.setGeoPointId((String) fields.get("geoPointId"));
}


		return returnVal;
 } 
	public static FacilityLocation map(GenericValue val) {

FacilityLocation returnVal = new FacilityLocation();
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setLocationSeqId(val.getString("locationSeqId"));
		returnVal.setLocationTypeEnumId(val.getString("locationTypeEnumId"));
		returnVal.setAreaId(val.getString("areaId"));
		returnVal.setAisleId(val.getString("aisleId"));
		returnVal.setSectionId(val.getString("sectionId"));
		returnVal.setLevelId(val.getString("levelId"));
		returnVal.setPositionId(val.getString("positionId"));
		returnVal.setGeoPointId(val.getString("geoPointId"));


return returnVal;

}

public static FacilityLocation map(HttpServletRequest request) throws Exception {

		FacilityLocation returnVal = new FacilityLocation();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityId")) {
returnVal.setFacilityId(request.getParameter("facilityId"));
}

		if(paramMap.containsKey("locationSeqId"))  {
returnVal.setLocationSeqId(request.getParameter("locationSeqId"));
}
		if(paramMap.containsKey("locationTypeEnumId"))  {
returnVal.setLocationTypeEnumId(request.getParameter("locationTypeEnumId"));
}
		if(paramMap.containsKey("areaId"))  {
returnVal.setAreaId(request.getParameter("areaId"));
}
		if(paramMap.containsKey("aisleId"))  {
returnVal.setAisleId(request.getParameter("aisleId"));
}
		if(paramMap.containsKey("sectionId"))  {
returnVal.setSectionId(request.getParameter("sectionId"));
}
		if(paramMap.containsKey("levelId"))  {
returnVal.setLevelId(request.getParameter("levelId"));
}
		if(paramMap.containsKey("positionId"))  {
returnVal.setPositionId(request.getParameter("positionId"));
}
		if(paramMap.containsKey("geoPointId"))  {
returnVal.setGeoPointId(request.getParameter("geoPointId"));
}
return returnVal;

}
}
