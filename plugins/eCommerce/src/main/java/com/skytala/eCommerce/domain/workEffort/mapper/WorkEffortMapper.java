package com.skytala.eCommerce.domain.workEffort.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workEffort.model.WorkEffort;

public class WorkEffortMapper  {


	public static Map<String, Object> map(WorkEffort workeffort) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(workeffort.getWorkEffortId() != null ){
			returnVal.put("workEffortId",workeffort.getWorkEffortId());
}

		if(workeffort.getWorkEffortTypeId() != null ){
			returnVal.put("workEffortTypeId",workeffort.getWorkEffortTypeId());
}

		if(workeffort.getCurrentStatusId() != null ){
			returnVal.put("currentStatusId",workeffort.getCurrentStatusId());
}

		if(workeffort.getLastStatusUpdate() != null ){
			returnVal.put("lastStatusUpdate",workeffort.getLastStatusUpdate());
}

		if(workeffort.getWorkEffortPurposeTypeId() != null ){
			returnVal.put("workEffortPurposeTypeId",workeffort.getWorkEffortPurposeTypeId());
}

		if(workeffort.getWorkEffortParentId() != null ){
			returnVal.put("workEffortParentId",workeffort.getWorkEffortParentId());
}

		if(workeffort.getScopeEnumId() != null ){
			returnVal.put("scopeEnumId",workeffort.getScopeEnumId());
}

		if(workeffort.getPriority() != null ){
			returnVal.put("priority",workeffort.getPriority());
}

		if(workeffort.getPercentComplete() != null ){
			returnVal.put("percentComplete",workeffort.getPercentComplete());
}

		if(workeffort.getWorkEffortName() != null ){
			returnVal.put("workEffortName",workeffort.getWorkEffortName());
}

		if(workeffort.getShowAsEnumId() != null ){
			returnVal.put("showAsEnumId",workeffort.getShowAsEnumId());
}

		if(workeffort.getSendNotificationEmail() != null ){
			returnVal.put("sendNotificationEmail",workeffort.getSendNotificationEmail());
}

		if(workeffort.getDescription() != null ){
			returnVal.put("description",workeffort.getDescription());
}

		if(workeffort.getLocationDesc() != null ){
			returnVal.put("locationDesc",workeffort.getLocationDesc());
}

		if(workeffort.getEstimatedStartDate() != null ){
			returnVal.put("estimatedStartDate",workeffort.getEstimatedStartDate());
}

		if(workeffort.getEstimatedCompletionDate() != null ){
			returnVal.put("estimatedCompletionDate",workeffort.getEstimatedCompletionDate());
}

		if(workeffort.getActualStartDate() != null ){
			returnVal.put("actualStartDate",workeffort.getActualStartDate());
}

		if(workeffort.getActualCompletionDate() != null ){
			returnVal.put("actualCompletionDate",workeffort.getActualCompletionDate());
}

		if(workeffort.getEstimatedMilliSeconds() != null ){
			returnVal.put("estimatedMilliSeconds",workeffort.getEstimatedMilliSeconds());
}

		if(workeffort.getEstimatedSetupMillis() != null ){
			returnVal.put("estimatedSetupMillis",workeffort.getEstimatedSetupMillis());
}

		if(workeffort.getEstimateCalcMethod() != null ){
			returnVal.put("estimateCalcMethod",workeffort.getEstimateCalcMethod());
}

		if(workeffort.getActualMilliSeconds() != null ){
			returnVal.put("actualMilliSeconds",workeffort.getActualMilliSeconds());
}

		if(workeffort.getActualSetupMillis() != null ){
			returnVal.put("actualSetupMillis",workeffort.getActualSetupMillis());
}

		if(workeffort.getTotalMilliSecondsAllowed() != null ){
			returnVal.put("totalMilliSecondsAllowed",workeffort.getTotalMilliSecondsAllowed());
}

		if(workeffort.getTotalMoneyAllowed() != null ){
			returnVal.put("totalMoneyAllowed",workeffort.getTotalMoneyAllowed());
}

		if(workeffort.getMoneyUomId() != null ){
			returnVal.put("moneyUomId",workeffort.getMoneyUomId());
}

		if(workeffort.getSpecialTerms() != null ){
			returnVal.put("specialTerms",workeffort.getSpecialTerms());
}

		if(workeffort.getTimeTransparency() != null ){
			returnVal.put("timeTransparency",workeffort.getTimeTransparency());
}

		if(workeffort.getUniversalId() != null ){
			returnVal.put("universalId",workeffort.getUniversalId());
}

		if(workeffort.getSourceReferenceId() != null ){
			returnVal.put("sourceReferenceId",workeffort.getSourceReferenceId());
}

		if(workeffort.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",workeffort.getFixedAssetId());
}

		if(workeffort.getFacilityId() != null ){
			returnVal.put("facilityId",workeffort.getFacilityId());
}

		if(workeffort.getInfoUrl() != null ){
			returnVal.put("infoUrl",workeffort.getInfoUrl());
}

		if(workeffort.getRecurrenceInfoId() != null ){
			returnVal.put("recurrenceInfoId",workeffort.getRecurrenceInfoId());
}

		if(workeffort.getTempExprId() != null ){
			returnVal.put("tempExprId",workeffort.getTempExprId());
}

		if(workeffort.getRuntimeDataId() != null ){
			returnVal.put("runtimeDataId",workeffort.getRuntimeDataId());
}

		if(workeffort.getNoteId() != null ){
			returnVal.put("noteId",workeffort.getNoteId());
}

		if(workeffort.getServiceLoaderName() != null ){
			returnVal.put("serviceLoaderName",workeffort.getServiceLoaderName());
}

		if(workeffort.getQuantityToProduce() != null ){
			returnVal.put("quantityToProduce",workeffort.getQuantityToProduce());
}

		if(workeffort.getQuantityProduced() != null ){
			returnVal.put("quantityProduced",workeffort.getQuantityProduced());
}

		if(workeffort.getQuantityRejected() != null ){
			returnVal.put("quantityRejected",workeffort.getQuantityRejected());
}

		if(workeffort.getReservPersons() != null ){
			returnVal.put("reservPersons",workeffort.getReservPersons());
}

		if(workeffort.getReserv2ndPPPerc() != null ){
			returnVal.put("reserv2ndPPPerc",workeffort.getReserv2ndPPPerc());
}

		if(workeffort.getReservNthPPPerc() != null ){
			returnVal.put("reservNthPPPerc",workeffort.getReservNthPPPerc());
}

		if(workeffort.getAccommodationMapId() != null ){
			returnVal.put("accommodationMapId",workeffort.getAccommodationMapId());
}

		if(workeffort.getAccommodationSpotId() != null ){
			returnVal.put("accommodationSpotId",workeffort.getAccommodationSpotId());
}

		if(workeffort.getRevisionNumber() != null ){
			returnVal.put("revisionNumber",workeffort.getRevisionNumber());
}

		if(workeffort.getCreatedDate() != null ){
			returnVal.put("createdDate",workeffort.getCreatedDate());
}

		if(workeffort.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",workeffort.getCreatedByUserLogin());
}

		if(workeffort.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",workeffort.getLastModifiedDate());
}

		if(workeffort.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",workeffort.getLastModifiedByUserLogin());
}

		return returnVal;
}


	public static WorkEffort map(Map<String, Object> fields) {

		WorkEffort returnVal = new WorkEffort();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("workEffortTypeId") != null) {
			returnVal.setWorkEffortTypeId((String) fields.get("workEffortTypeId"));
}

		if(fields.get("currentStatusId") != null) {
			returnVal.setCurrentStatusId((String) fields.get("currentStatusId"));
}

		if(fields.get("lastStatusUpdate") != null) {
			returnVal.setLastStatusUpdate((Timestamp) fields.get("lastStatusUpdate"));
}

		if(fields.get("workEffortPurposeTypeId") != null) {
			returnVal.setWorkEffortPurposeTypeId((String) fields.get("workEffortPurposeTypeId"));
}

		if(fields.get("workEffortParentId") != null) {
			returnVal.setWorkEffortParentId((String) fields.get("workEffortParentId"));
}

		if(fields.get("scopeEnumId") != null) {
			returnVal.setScopeEnumId((String) fields.get("scopeEnumId"));
}

		if(fields.get("priority") != null) {
			returnVal.setPriority((long) fields.get("priority"));
}

		if(fields.get("percentComplete") != null) {
			returnVal.setPercentComplete((long) fields.get("percentComplete"));
}

		if(fields.get("workEffortName") != null) {
			returnVal.setWorkEffortName((String) fields.get("workEffortName"));
}

		if(fields.get("showAsEnumId") != null) {
			returnVal.setShowAsEnumId((String) fields.get("showAsEnumId"));
}

		if(fields.get("sendNotificationEmail") != null) {
			returnVal.setSendNotificationEmail((boolean) fields.get("sendNotificationEmail"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("locationDesc") != null) {
			returnVal.setLocationDesc((String) fields.get("locationDesc"));
}

		if(fields.get("estimatedStartDate") != null) {
			returnVal.setEstimatedStartDate((Timestamp) fields.get("estimatedStartDate"));
}

		if(fields.get("estimatedCompletionDate") != null) {
			returnVal.setEstimatedCompletionDate((Timestamp) fields.get("estimatedCompletionDate"));
}

		if(fields.get("actualStartDate") != null) {
			returnVal.setActualStartDate((Timestamp) fields.get("actualStartDate"));
}

		if(fields.get("actualCompletionDate") != null) {
			returnVal.setActualCompletionDate((Timestamp) fields.get("actualCompletionDate"));
}

		if(fields.get("estimatedMilliSeconds") != null) {
			returnVal.setEstimatedMilliSeconds((BigDecimal) fields.get("estimatedMilliSeconds"));
}

		if(fields.get("estimatedSetupMillis") != null) {
			returnVal.setEstimatedSetupMillis((BigDecimal) fields.get("estimatedSetupMillis"));
}

		if(fields.get("estimateCalcMethod") != null) {
			returnVal.setEstimateCalcMethod((String) fields.get("estimateCalcMethod"));
}

		if(fields.get("actualMilliSeconds") != null) {
			returnVal.setActualMilliSeconds((BigDecimal) fields.get("actualMilliSeconds"));
}

		if(fields.get("actualSetupMillis") != null) {
			returnVal.setActualSetupMillis((BigDecimal) fields.get("actualSetupMillis"));
}

		if(fields.get("totalMilliSecondsAllowed") != null) {
			returnVal.setTotalMilliSecondsAllowed((BigDecimal) fields.get("totalMilliSecondsAllowed"));
}

		if(fields.get("totalMoneyAllowed") != null) {
			returnVal.setTotalMoneyAllowed((BigDecimal) fields.get("totalMoneyAllowed"));
}

		if(fields.get("moneyUomId") != null) {
			returnVal.setMoneyUomId((String) fields.get("moneyUomId"));
}

		if(fields.get("specialTerms") != null) {
			returnVal.setSpecialTerms((String) fields.get("specialTerms"));
}

		if(fields.get("timeTransparency") != null) {
			returnVal.setTimeTransparency((long) fields.get("timeTransparency"));
}

		if(fields.get("universalId") != null) {
			returnVal.setUniversalId((String) fields.get("universalId"));
}

		if(fields.get("sourceReferenceId") != null) {
			returnVal.setSourceReferenceId((String) fields.get("sourceReferenceId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("infoUrl") != null) {
			returnVal.setInfoUrl((String) fields.get("infoUrl"));
}

		if(fields.get("recurrenceInfoId") != null) {
			returnVal.setRecurrenceInfoId((String) fields.get("recurrenceInfoId"));
}

		if(fields.get("tempExprId") != null) {
			returnVal.setTempExprId((String) fields.get("tempExprId"));
}

		if(fields.get("runtimeDataId") != null) {
			returnVal.setRuntimeDataId((String) fields.get("runtimeDataId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}

		if(fields.get("serviceLoaderName") != null) {
			returnVal.setServiceLoaderName((String) fields.get("serviceLoaderName"));
}

		if(fields.get("quantityToProduce") != null) {
			returnVal.setQuantityToProduce((BigDecimal) fields.get("quantityToProduce"));
}

		if(fields.get("quantityProduced") != null) {
			returnVal.setQuantityProduced((BigDecimal) fields.get("quantityProduced"));
}

		if(fields.get("quantityRejected") != null) {
			returnVal.setQuantityRejected((BigDecimal) fields.get("quantityRejected"));
}

		if(fields.get("reservPersons") != null) {
			returnVal.setReservPersons((BigDecimal) fields.get("reservPersons"));
}

		if(fields.get("reserv2ndPPPerc") != null) {
			returnVal.setReserv2ndPPPerc((BigDecimal) fields.get("reserv2ndPPPerc"));
}

		if(fields.get("reservNthPPPerc") != null) {
			returnVal.setReservNthPPPerc((BigDecimal) fields.get("reservNthPPPerc"));
}

		if(fields.get("accommodationMapId") != null) {
			returnVal.setAccommodationMapId((String) fields.get("accommodationMapId"));
}

		if(fields.get("accommodationSpotId") != null) {
			returnVal.setAccommodationSpotId((String) fields.get("accommodationSpotId"));
}

		if(fields.get("revisionNumber") != null) {
			returnVal.setRevisionNumber((long) fields.get("revisionNumber"));
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
	public static WorkEffort mapstrstr(Map<String, String> fields) throws Exception {

		WorkEffort returnVal = new WorkEffort();

		if(fields.get("workEffortId") != null) {
			returnVal.setWorkEffortId((String) fields.get("workEffortId"));
}

		if(fields.get("workEffortTypeId") != null) {
			returnVal.setWorkEffortTypeId((String) fields.get("workEffortTypeId"));
}

		if(fields.get("currentStatusId") != null) {
			returnVal.setCurrentStatusId((String) fields.get("currentStatusId"));
}

		if(fields.get("lastStatusUpdate") != null) {
String buf = fields.get("lastStatusUpdate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setLastStatusUpdate(ibuf);
}

		if(fields.get("workEffortPurposeTypeId") != null) {
			returnVal.setWorkEffortPurposeTypeId((String) fields.get("workEffortPurposeTypeId"));
}

		if(fields.get("workEffortParentId") != null) {
			returnVal.setWorkEffortParentId((String) fields.get("workEffortParentId"));
}

		if(fields.get("scopeEnumId") != null) {
			returnVal.setScopeEnumId((String) fields.get("scopeEnumId"));
}

		if(fields.get("priority") != null) {
String buf;
buf = fields.get("priority");
long ibuf = Long.parseLong(buf);
			returnVal.setPriority(ibuf);
}

		if(fields.get("percentComplete") != null) {
String buf;
buf = fields.get("percentComplete");
long ibuf = Long.parseLong(buf);
			returnVal.setPercentComplete(ibuf);
}

		if(fields.get("workEffortName") != null) {
			returnVal.setWorkEffortName((String) fields.get("workEffortName"));
}

		if(fields.get("showAsEnumId") != null) {
			returnVal.setShowAsEnumId((String) fields.get("showAsEnumId"));
}

		if(fields.get("sendNotificationEmail") != null) {
String buf;
buf = fields.get("sendNotificationEmail");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setSendNotificationEmail(ibuf);
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("locationDesc") != null) {
			returnVal.setLocationDesc((String) fields.get("locationDesc"));
}

		if(fields.get("estimatedStartDate") != null) {
String buf = fields.get("estimatedStartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedStartDate(ibuf);
}

		if(fields.get("estimatedCompletionDate") != null) {
String buf = fields.get("estimatedCompletionDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setEstimatedCompletionDate(ibuf);
}

		if(fields.get("actualStartDate") != null) {
String buf = fields.get("actualStartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setActualStartDate(ibuf);
}

		if(fields.get("actualCompletionDate") != null) {
String buf = fields.get("actualCompletionDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setActualCompletionDate(ibuf);
}

		if(fields.get("estimatedMilliSeconds") != null) {
String buf;
buf = fields.get("estimatedMilliSeconds");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedMilliSeconds(bd);
}

		if(fields.get("estimatedSetupMillis") != null) {
String buf;
buf = fields.get("estimatedSetupMillis");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedSetupMillis(bd);
}

		if(fields.get("estimateCalcMethod") != null) {
			returnVal.setEstimateCalcMethod((String) fields.get("estimateCalcMethod"));
}

		if(fields.get("actualMilliSeconds") != null) {
String buf;
buf = fields.get("actualMilliSeconds");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualMilliSeconds(bd);
}

		if(fields.get("actualSetupMillis") != null) {
String buf;
buf = fields.get("actualSetupMillis");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualSetupMillis(bd);
}

		if(fields.get("totalMilliSecondsAllowed") != null) {
String buf;
buf = fields.get("totalMilliSecondsAllowed");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalMilliSecondsAllowed(bd);
}

		if(fields.get("totalMoneyAllowed") != null) {
String buf;
buf = fields.get("totalMoneyAllowed");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalMoneyAllowed(bd);
}

		if(fields.get("moneyUomId") != null) {
			returnVal.setMoneyUomId((String) fields.get("moneyUomId"));
}

		if(fields.get("specialTerms") != null) {
			returnVal.setSpecialTerms((String) fields.get("specialTerms"));
}

		if(fields.get("timeTransparency") != null) {
String buf;
buf = fields.get("timeTransparency");
long ibuf = Long.parseLong(buf);
			returnVal.setTimeTransparency(ibuf);
}

		if(fields.get("universalId") != null) {
			returnVal.setUniversalId((String) fields.get("universalId"));
}

		if(fields.get("sourceReferenceId") != null) {
			returnVal.setSourceReferenceId((String) fields.get("sourceReferenceId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("facilityId") != null) {
			returnVal.setFacilityId((String) fields.get("facilityId"));
}

		if(fields.get("infoUrl") != null) {
			returnVal.setInfoUrl((String) fields.get("infoUrl"));
}

		if(fields.get("recurrenceInfoId") != null) {
			returnVal.setRecurrenceInfoId((String) fields.get("recurrenceInfoId"));
}

		if(fields.get("tempExprId") != null) {
			returnVal.setTempExprId((String) fields.get("tempExprId"));
}

		if(fields.get("runtimeDataId") != null) {
			returnVal.setRuntimeDataId((String) fields.get("runtimeDataId"));
}

		if(fields.get("noteId") != null) {
			returnVal.setNoteId((String) fields.get("noteId"));
}

		if(fields.get("serviceLoaderName") != null) {
			returnVal.setServiceLoaderName((String) fields.get("serviceLoaderName"));
}

		if(fields.get("quantityToProduce") != null) {
String buf;
buf = fields.get("quantityToProduce");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityToProduce(bd);
}

		if(fields.get("quantityProduced") != null) {
String buf;
buf = fields.get("quantityProduced");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityProduced(bd);
}

		if(fields.get("quantityRejected") != null) {
String buf;
buf = fields.get("quantityRejected");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityRejected(bd);
}

		if(fields.get("reservPersons") != null) {
String buf;
buf = fields.get("reservPersons");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservPersons(bd);
}

		if(fields.get("reserv2ndPPPerc") != null) {
String buf;
buf = fields.get("reserv2ndPPPerc");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReserv2ndPPPerc(bd);
}

		if(fields.get("reservNthPPPerc") != null) {
String buf;
buf = fields.get("reservNthPPPerc");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservNthPPPerc(bd);
}

		if(fields.get("accommodationMapId") != null) {
			returnVal.setAccommodationMapId((String) fields.get("accommodationMapId"));
}

		if(fields.get("accommodationSpotId") != null) {
			returnVal.setAccommodationSpotId((String) fields.get("accommodationSpotId"));
}

		if(fields.get("revisionNumber") != null) {
String buf;
buf = fields.get("revisionNumber");
long ibuf = Long.parseLong(buf);
			returnVal.setRevisionNumber(ibuf);
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
	public static WorkEffort map(GenericValue val) {

WorkEffort returnVal = new WorkEffort();
		returnVal.setWorkEffortId(val.getString("workEffortId"));
		returnVal.setWorkEffortTypeId(val.getString("workEffortTypeId"));
		returnVal.setCurrentStatusId(val.getString("currentStatusId"));
		returnVal.setLastStatusUpdate(val.getTimestamp("lastStatusUpdate"));
		returnVal.setWorkEffortPurposeTypeId(val.getString("workEffortPurposeTypeId"));
		returnVal.setWorkEffortParentId(val.getString("workEffortParentId"));
		returnVal.setScopeEnumId(val.getString("scopeEnumId"));
		returnVal.setPriority(val.getLong("priority"));
		returnVal.setPercentComplete(val.getLong("percentComplete"));
		returnVal.setWorkEffortName(val.getString("workEffortName"));
		returnVal.setShowAsEnumId(val.getString("showAsEnumId"));
		returnVal.setSendNotificationEmail(val.getBoolean("sendNotificationEmail"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setLocationDesc(val.getString("locationDesc"));
		returnVal.setEstimatedStartDate(val.getTimestamp("estimatedStartDate"));
		returnVal.setEstimatedCompletionDate(val.getTimestamp("estimatedCompletionDate"));
		returnVal.setActualStartDate(val.getTimestamp("actualStartDate"));
		returnVal.setActualCompletionDate(val.getTimestamp("actualCompletionDate"));
		returnVal.setEstimatedMilliSeconds(val.getBigDecimal("estimatedMilliSeconds"));
		returnVal.setEstimatedSetupMillis(val.getBigDecimal("estimatedSetupMillis"));
		returnVal.setEstimateCalcMethod(val.getString("estimateCalcMethod"));
		returnVal.setActualMilliSeconds(val.getBigDecimal("actualMilliSeconds"));
		returnVal.setActualSetupMillis(val.getBigDecimal("actualSetupMillis"));
		returnVal.setTotalMilliSecondsAllowed(val.getBigDecimal("totalMilliSecondsAllowed"));
		returnVal.setTotalMoneyAllowed(val.getBigDecimal("totalMoneyAllowed"));
		returnVal.setMoneyUomId(val.getString("moneyUomId"));
		returnVal.setSpecialTerms(val.getString("specialTerms"));
		returnVal.setTimeTransparency(val.getLong("timeTransparency"));
		returnVal.setUniversalId(val.getString("universalId"));
		returnVal.setSourceReferenceId(val.getString("sourceReferenceId"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setFacilityId(val.getString("facilityId"));
		returnVal.setInfoUrl(val.getString("infoUrl"));
		returnVal.setRecurrenceInfoId(val.getString("recurrenceInfoId"));
		returnVal.setTempExprId(val.getString("tempExprId"));
		returnVal.setRuntimeDataId(val.getString("runtimeDataId"));
		returnVal.setNoteId(val.getString("noteId"));
		returnVal.setServiceLoaderName(val.getString("serviceLoaderName"));
		returnVal.setQuantityToProduce(val.getBigDecimal("quantityToProduce"));
		returnVal.setQuantityProduced(val.getBigDecimal("quantityProduced"));
		returnVal.setQuantityRejected(val.getBigDecimal("quantityRejected"));
		returnVal.setReservPersons(val.getBigDecimal("reservPersons"));
		returnVal.setReserv2ndPPPerc(val.getBigDecimal("reserv2ndPPPerc"));
		returnVal.setReservNthPPPerc(val.getBigDecimal("reservNthPPPerc"));
		returnVal.setAccommodationMapId(val.getString("accommodationMapId"));
		returnVal.setAccommodationSpotId(val.getString("accommodationSpotId"));
		returnVal.setRevisionNumber(val.getLong("revisionNumber"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));


return returnVal;

}

public static WorkEffort map(HttpServletRequest request) throws Exception {

		WorkEffort returnVal = new WorkEffort();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("workEffortId")) {
returnVal.setWorkEffortId(request.getParameter("workEffortId"));
}

		if(paramMap.containsKey("workEffortTypeId"))  {
returnVal.setWorkEffortTypeId(request.getParameter("workEffortTypeId"));
}
		if(paramMap.containsKey("currentStatusId"))  {
returnVal.setCurrentStatusId(request.getParameter("currentStatusId"));
}
		if(paramMap.containsKey("lastStatusUpdate"))  {
String buf = request.getParameter("lastStatusUpdate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setLastStatusUpdate(ibuf);
}
		if(paramMap.containsKey("workEffortPurposeTypeId"))  {
returnVal.setWorkEffortPurposeTypeId(request.getParameter("workEffortPurposeTypeId"));
}
		if(paramMap.containsKey("workEffortParentId"))  {
returnVal.setWorkEffortParentId(request.getParameter("workEffortParentId"));
}
		if(paramMap.containsKey("scopeEnumId"))  {
returnVal.setScopeEnumId(request.getParameter("scopeEnumId"));
}
		if(paramMap.containsKey("priority"))  {
String buf = request.getParameter("priority");
Long ibuf = Long.parseLong(buf);
returnVal.setPriority(ibuf);
}
		if(paramMap.containsKey("percentComplete"))  {
String buf = request.getParameter("percentComplete");
Long ibuf = Long.parseLong(buf);
returnVal.setPercentComplete(ibuf);
}
		if(paramMap.containsKey("workEffortName"))  {
returnVal.setWorkEffortName(request.getParameter("workEffortName"));
}
		if(paramMap.containsKey("showAsEnumId"))  {
returnVal.setShowAsEnumId(request.getParameter("showAsEnumId"));
}
		if(paramMap.containsKey("sendNotificationEmail"))  {
String buf = request.getParameter("sendNotificationEmail");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setSendNotificationEmail(ibuf);
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("locationDesc"))  {
returnVal.setLocationDesc(request.getParameter("locationDesc"));
}
		if(paramMap.containsKey("estimatedStartDate"))  {
String buf = request.getParameter("estimatedStartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedStartDate(ibuf);
}
		if(paramMap.containsKey("estimatedCompletionDate"))  {
String buf = request.getParameter("estimatedCompletionDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setEstimatedCompletionDate(ibuf);
}
		if(paramMap.containsKey("actualStartDate"))  {
String buf = request.getParameter("actualStartDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setActualStartDate(ibuf);
}
		if(paramMap.containsKey("actualCompletionDate"))  {
String buf = request.getParameter("actualCompletionDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setActualCompletionDate(ibuf);
}
		if(paramMap.containsKey("estimatedMilliSeconds"))  {
String buf = request.getParameter("estimatedMilliSeconds");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedMilliSeconds(bd);
}
		if(paramMap.containsKey("estimatedSetupMillis"))  {
String buf = request.getParameter("estimatedSetupMillis");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setEstimatedSetupMillis(bd);
}
		if(paramMap.containsKey("estimateCalcMethod"))  {
returnVal.setEstimateCalcMethod(request.getParameter("estimateCalcMethod"));
}
		if(paramMap.containsKey("actualMilliSeconds"))  {
String buf = request.getParameter("actualMilliSeconds");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualMilliSeconds(bd);
}
		if(paramMap.containsKey("actualSetupMillis"))  {
String buf = request.getParameter("actualSetupMillis");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setActualSetupMillis(bd);
}
		if(paramMap.containsKey("totalMilliSecondsAllowed"))  {
String buf = request.getParameter("totalMilliSecondsAllowed");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalMilliSecondsAllowed(bd);
}
		if(paramMap.containsKey("totalMoneyAllowed"))  {
String buf = request.getParameter("totalMoneyAllowed");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setTotalMoneyAllowed(bd);
}
		if(paramMap.containsKey("moneyUomId"))  {
returnVal.setMoneyUomId(request.getParameter("moneyUomId"));
}
		if(paramMap.containsKey("specialTerms"))  {
returnVal.setSpecialTerms(request.getParameter("specialTerms"));
}
		if(paramMap.containsKey("timeTransparency"))  {
String buf = request.getParameter("timeTransparency");
Long ibuf = Long.parseLong(buf);
returnVal.setTimeTransparency(ibuf);
}
		if(paramMap.containsKey("universalId"))  {
returnVal.setUniversalId(request.getParameter("universalId"));
}
		if(paramMap.containsKey("sourceReferenceId"))  {
returnVal.setSourceReferenceId(request.getParameter("sourceReferenceId"));
}
		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}
		if(paramMap.containsKey("facilityId"))  {
returnVal.setFacilityId(request.getParameter("facilityId"));
}
		if(paramMap.containsKey("infoUrl"))  {
returnVal.setInfoUrl(request.getParameter("infoUrl"));
}
		if(paramMap.containsKey("recurrenceInfoId"))  {
returnVal.setRecurrenceInfoId(request.getParameter("recurrenceInfoId"));
}
		if(paramMap.containsKey("tempExprId"))  {
returnVal.setTempExprId(request.getParameter("tempExprId"));
}
		if(paramMap.containsKey("runtimeDataId"))  {
returnVal.setRuntimeDataId(request.getParameter("runtimeDataId"));
}
		if(paramMap.containsKey("noteId"))  {
returnVal.setNoteId(request.getParameter("noteId"));
}
		if(paramMap.containsKey("serviceLoaderName"))  {
returnVal.setServiceLoaderName(request.getParameter("serviceLoaderName"));
}
		if(paramMap.containsKey("quantityToProduce"))  {
String buf = request.getParameter("quantityToProduce");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityToProduce(bd);
}
		if(paramMap.containsKey("quantityProduced"))  {
String buf = request.getParameter("quantityProduced");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityProduced(bd);
}
		if(paramMap.containsKey("quantityRejected"))  {
String buf = request.getParameter("quantityRejected");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setQuantityRejected(bd);
}
		if(paramMap.containsKey("reservPersons"))  {
String buf = request.getParameter("reservPersons");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservPersons(bd);
}
		if(paramMap.containsKey("reserv2ndPPPerc"))  {
String buf = request.getParameter("reserv2ndPPPerc");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReserv2ndPPPerc(bd);
}
		if(paramMap.containsKey("reservNthPPPerc"))  {
String buf = request.getParameter("reservNthPPPerc");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReservNthPPPerc(bd);
}
		if(paramMap.containsKey("accommodationMapId"))  {
returnVal.setAccommodationMapId(request.getParameter("accommodationMapId"));
}
		if(paramMap.containsKey("accommodationSpotId"))  {
returnVal.setAccommodationSpotId(request.getParameter("accommodationSpotId"));
}
		if(paramMap.containsKey("revisionNumber"))  {
String buf = request.getParameter("revisionNumber");
Long ibuf = Long.parseLong(buf);
returnVal.setRevisionNumber(ibuf);
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
