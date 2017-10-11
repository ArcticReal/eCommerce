package com.skytala.eCommerce.domain.product.relations.facilityGroup.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.facilityGroup.model.FacilityGroup;

public class FacilityGroupMapper  {


	public static Map<String, Object> map(FacilityGroup facilitygroup) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(facilitygroup.getFacilityGroupId() != null ){
			returnVal.put("facilityGroupId",facilitygroup.getFacilityGroupId());
}

		if(facilitygroup.getFacilityGroupTypeId() != null ){
			returnVal.put("facilityGroupTypeId",facilitygroup.getFacilityGroupTypeId());
}

		if(facilitygroup.getPrimaryParentGroupId() != null ){
			returnVal.put("primaryParentGroupId",facilitygroup.getPrimaryParentGroupId());
}

		if(facilitygroup.getFacilityGroupName() != null ){
			returnVal.put("facilityGroupName",facilitygroup.getFacilityGroupName());
}

		if(facilitygroup.getDescription() != null ){
			returnVal.put("description",facilitygroup.getDescription());
}

		return returnVal;
}


	public static FacilityGroup map(Map<String, Object> fields) {

		FacilityGroup returnVal = new FacilityGroup();

		if(fields.get("facilityGroupId") != null) {
			returnVal.setFacilityGroupId((String) fields.get("facilityGroupId"));
}

		if(fields.get("facilityGroupTypeId") != null) {
			returnVal.setFacilityGroupTypeId((String) fields.get("facilityGroupTypeId"));
}

		if(fields.get("primaryParentGroupId") != null) {
			returnVal.setPrimaryParentGroupId((String) fields.get("primaryParentGroupId"));
}

		if(fields.get("facilityGroupName") != null) {
			returnVal.setFacilityGroupName((String) fields.get("facilityGroupName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FacilityGroup mapstrstr(Map<String, String> fields) throws Exception {

		FacilityGroup returnVal = new FacilityGroup();

		if(fields.get("facilityGroupId") != null) {
			returnVal.setFacilityGroupId((String) fields.get("facilityGroupId"));
}

		if(fields.get("facilityGroupTypeId") != null) {
			returnVal.setFacilityGroupTypeId((String) fields.get("facilityGroupTypeId"));
}

		if(fields.get("primaryParentGroupId") != null) {
			returnVal.setPrimaryParentGroupId((String) fields.get("primaryParentGroupId"));
}

		if(fields.get("facilityGroupName") != null) {
			returnVal.setFacilityGroupName((String) fields.get("facilityGroupName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}


		return returnVal;
 } 
	public static FacilityGroup map(GenericValue val) {

FacilityGroup returnVal = new FacilityGroup();
		returnVal.setFacilityGroupId(val.getString("facilityGroupId"));
		returnVal.setFacilityGroupTypeId(val.getString("facilityGroupTypeId"));
		returnVal.setPrimaryParentGroupId(val.getString("primaryParentGroupId"));
		returnVal.setFacilityGroupName(val.getString("facilityGroupName"));
		returnVal.setDescription(val.getString("description"));


return returnVal;

}

public static FacilityGroup map(HttpServletRequest request) throws Exception {

		FacilityGroup returnVal = new FacilityGroup();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("facilityGroupId")) {
returnVal.setFacilityGroupId(request.getParameter("facilityGroupId"));
}

		if(paramMap.containsKey("facilityGroupTypeId"))  {
returnVal.setFacilityGroupTypeId(request.getParameter("facilityGroupTypeId"));
}
		if(paramMap.containsKey("primaryParentGroupId"))  {
returnVal.setPrimaryParentGroupId(request.getParameter("primaryParentGroupId"));
}
		if(paramMap.containsKey("facilityGroupName"))  {
returnVal.setFacilityGroupName(request.getParameter("facilityGroupName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
return returnVal;

}
}
