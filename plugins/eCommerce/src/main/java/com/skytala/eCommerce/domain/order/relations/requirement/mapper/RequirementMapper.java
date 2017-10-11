package com.skytala.eCommerce.domain.order.relations.requirement.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.requirement.model.Requirement;

public class RequirementMapper  {


	public static Map<String, Object> map(Requirement requirement) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(requirement.getRequirementId() != null ){
			returnVal.put("requirementId",requirement.getRequirementId());
}

		if(requirement.getRequirementTypeId() != null ){
			returnVal.put("requirementTypeId",requirement.getRequirementTypeId());
}

		if(requirement.getFacilityId() != null ){
			returnVal.put("facilityId",requirement.getFacilityId());
}

		if(requirement.getDeliverableId() != null ){
			returnVal.put("deliverableId",requirement.getDeliverableId());
}

		if(requirement.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",requirement.getFixedAssetId());
}

		if(requirement.getProductId() != null ){
			returnVal.put("productId",requirement.getProductId());
}

		if(requirement.getStatusId() != null ){
			returnVal.put("statusId",requirement.getStatusId());
}

		if(requirement.getDescription() != null ){
			returnVal.put("description",requirement.getDescription());
}

		if(requirement.getRequirementStartDate() != null ){
			returnVal.put("requirementStartDate",requirement.getRequirementStartDate());
}

		if(requirement.getRequiredByDate() != null ){
			returnVal.put("requiredByDate",requirement.getRequiredByDate());
}

		if(requirement.getEstimatedBudget() != null ){
			returnVal.put("estimatedBudget",requirement.getEstimatedBudget());
}

		if(requirement.getQuantity() != null ){
			returnVal.put("quantity",requirement.getQuantity());
}

		if(requirement.getUseCase() != null ){
			returnVal.put("useCase",requirement.getUseCase());
}

		if(requirement.getReason() != null ){
			returnVal.put("reason",requirement.getReason());
}

		if(requirement.getCreatedDate() != null ){
			returnVal.put("createdDate",requirement.getCreatedDate());
}

		if(requirement.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",requirement.getCreatedByUserLogin());
}

		if(requirement.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",requirement.getLastModifiedDate());
}

		if(requirement.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",requirement.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static Requirement map(Map<String, Object> fields) {

		Requirement returnVal = new Requirement();

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("requirementTypeId") != null) {
			returnVal.setRequirementTypeId((String) fields.get("requirementTypeId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("deliverableId") != null) {
			returnVal.setDeliverableId((String) fields.get("deliverableId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("requirementStartDate") != null) {
			returnVal.setRequirementStartDate((Timestamp) fields.get("requirementStartDate"));
}

		if(fields.get("requiredByDate") != null) {
			returnVal.setRequiredByDate((Timestamp) fields.get("requiredByDate"));
}

		if(fields.get("estimatedBudget") != null) {
			returnVal.setEstimatedBudget((BigDecimal) fields.get("estimatedBudget"));
}

		if(fields.get("quantity") != null) {
			returnVal.setQuantity((BigDecimal) fields.get("quantity"));
}

		if(fields.get("useCase") != null) {
			returnVal.setUseCase((String) fields.get("useCase"));
}

		if(fields.get("reason") != null) {
			returnVal.setReason((String) fields.get("reason"));
}

		if(fields.get("createdDate") != null) {
			returnVal.setCreatedDate((Timestamp) fields.get("createdDate"));
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
			returnVal.setLastModifiedDate((Timestamp) fields.get("lastModifiedDate"));
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static Requirement mapstrstr(Map<String, String> fields) throws Exception {

		Requirement returnVal = new Requirement();

		if(fields.get("requirementId") != null) {
			returnVal.setRequirementId((String) fields.get("requirementId"));
}

		if(fields.get("requirementTypeId") != null) {
			returnVal.setRequirementTypeId((String) fields.get("requirementTypeId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("deliverableId") != null) {
			returnVal.setDeliverableId((String) fields.get("deliverableId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("requirementStartDate") != null) {
String buf = fields.get("requirementStartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setRequirementStartDate(ibuf);
}

		if(fields.get("requiredByDate") != null) {
String buf = fields.get("requiredByDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setRequiredByDate(ibuf);
}

		if(fields.get("estimatedBudget") != null) {
String buf;
buf = fields.get("estimatedBudget");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedBudget(bd);
}

		if(fields.get("quantity") != null) {
String buf;
buf = fields.get("quantity");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}

		if(fields.get("useCase") != null) {
			returnVal.setUseCase((String) fields.get("useCase"));
}

		if(fields.get("reason") != null) {
			returnVal.setReason((String) fields.get("reason"));
}

		if(fields.get("createdDate") != null) {
String buf = fields.get("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setCreatedDate(ibuf);
}

		if(fields.get("createdByUserLogin") != null) {
			returnVal.setCreatedByUserLogin((String) fields.get("createdByUserLogin"));
}

		if(fields.get("lastModifiedDate") != null) {
String buf = fields.get("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastModifiedDate(ibuf);
}

		if(fields.get("lastModifiedByUserLogin") != null) {
			returnVal.setLastModifiedByUserLogin((String) fields.get("lastModifiedByUserLogin"));
}


		return returnVal;
 } 
	public static Requirement map(GenericValue val) {

Requirement returnVal = new Requirement();
		returnVal.setRequirementId(val.getString("requirementId"));
		returnVal.setRequirementTypeId(val.getString("requirementTypeId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setDeliverableId(val.getString("deliverableId"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setRequirementStartDate(val.getTimestamp("requirementStartDate"));
		returnVal.setRequiredByDate(val.getTimestamp("requiredByDate"));
		returnVal.setEstimatedBudget(val.getBigDecimal("estimatedBudget"));
		returnVal.setQuantity(val.getBigDecimal("quantity"));
		returnVal.setUseCase(val.getString("useCase"));
		returnVal.setReason(val.getString("reason"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static Requirement map(HttpServletRequest request) throws Exception {

		Requirement returnVal = new Requirement();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("requirementId")) {
returnVal.setRequirementId(request.getParameter("requirementId"));
}

		if(paramMap.containsKey("requirementTypeId"))  {
returnVal.setRequirementTypeId(request.getParameter("requirementTypeId"));
}
		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("deliverableId"))  {
returnVal.setDeliverableId(request.getParameter("deliverableId"));
}
		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("requirementStartDate"))  {
String buf = request.getParameter("requirementStartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setRequirementStartDate(ibuf);
}
		if(paramMap.containsKey("requiredByDate"))  {
String buf = request.getParameter("requiredByDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setRequiredByDate(ibuf);
}
		if(paramMap.containsKey("estimatedBudget"))  {
String buf = request.getParameter("estimatedBudget");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedBudget(bd);
}
		if(paramMap.containsKey("quantity"))  {
String buf = request.getParameter("quantity");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantity(bd);
}
		if(paramMap.containsKey("useCase"))  {
returnVal.setUseCase(request.getParameter("useCase"));
}
		if(paramMap.containsKey("reason"))  {
returnVal.setReason(request.getParameter("reason"));
}
		if(paramMap.containsKey("createdDate"))  {
String buf = request.getParameter("createdDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setCreatedDate(ibuf);
}
		if(paramMap.containsKey("createdByUserLogin"))  {
returnVal.setCreatedByUserLogin(request.getParameter("createdByUserLogin"));
}
		if(paramMap.containsKey("lastModifiedDate"))  {
String buf = request.getParameter("lastModifiedDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastModifiedDate(ibuf);
}
		if(paramMap.containsKey("lastModifiedByUserLogin"))  {
returnVal.setLastModifiedByUserLogin(request.getParameter("lastModifiedByUserLogin"));
}
return returnVal;

}
}
