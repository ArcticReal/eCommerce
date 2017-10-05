package com.skytala.eCommerce.domain.glReconciliation.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.glReconciliation.model.GlReconciliation;

public class GlReconciliationMapper  {


	public static Map<String, Object> map(GlReconciliation glreconciliation) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glreconciliation.getGlReconciliationId() != null ){
			returnVal.put("glReconciliationId",glreconciliation.getGlReconciliationId());
}

		if(glreconciliation.getGlReconciliationName() != null ){
			returnVal.put("glReconciliationName",glreconciliation.getGlReconciliationName());
}

		if(glreconciliation.getDescription() != null ){
			returnVal.put("description",glreconciliation.getDescription());
}

		if(glreconciliation.getCreatedDate() != null ){
			returnVal.put("createdDate",glreconciliation.getCreatedDate());
}

		if(glreconciliation.getCreatedByUserLogin() != null ){
			returnVal.put("createdByUserLogin",glreconciliation.getCreatedByUserLogin());
}

		if(glreconciliation.getLastModifiedDate() != null ){
			returnVal.put("lastModifiedDate",glreconciliation.getLastModifiedDate());
}

		if(glreconciliation.getLastModifiedByUserLogin() != null ){
			returnVal.put("lastModifiedByUserLogin",glreconciliation.getLastModifiedByUserLogin());
}

		if(glreconciliation.getGlAccountId() != null ){
			returnVal.put("glAccountId",glreconciliation.getGlAccountId());
}

		if(glreconciliation.getStatusId() != null ){
			returnVal.put("statusId",glreconciliation.getStatusId());
}

		if(glreconciliation.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",glreconciliation.getOrganizationPartyId());
}

		if(glreconciliation.getReconciledBalance() != null ){
			returnVal.put("reconciledBalance",glreconciliation.getReconciledBalance());
}

		if(glreconciliation.getOpeningBalance() != null ){
			returnVal.put("openingBalance",glreconciliation.getOpeningBalance());
}

		if(glreconciliation.getReconciledDate() != null ){
			returnVal.put("reconciledDate",glreconciliation.getReconciledDate());
}

		return returnVal;
}


	public static GlReconciliation map(Map<String, Object> fields) {

		GlReconciliation returnVal = new GlReconciliation();

		if(fields.get("glReconciliationId") != null) {
			returnVal.setGlReconciliationId((String) fields.get("glReconciliationId"));
}

		if(fields.get("glReconciliationName") != null) {
			returnVal.setGlReconciliationName((String) fields.get("glReconciliationName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
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

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("reconciledBalance") != null) {
			returnVal.setReconciledBalance((BigDecimal) fields.get("reconciledBalance"));
}

		if(fields.get("openingBalance") != null) {
			returnVal.setOpeningBalance((BigDecimal) fields.get("openingBalance"));
}

		if(fields.get("reconciledDate") != null) {
			returnVal.setReconciledDate((Timestamp) fields.get("reconciledDate"));
}


		return returnVal;
 } 
	public static GlReconciliation mapstrstr(Map<String, String> fields) throws Exception {

		GlReconciliation returnVal = new GlReconciliation();

		if(fields.get("glReconciliationId") != null) {
			returnVal.setGlReconciliationId((String) fields.get("glReconciliationId"));
}

		if(fields.get("glReconciliationName") != null) {
			returnVal.setGlReconciliationName((String) fields.get("glReconciliationName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
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

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("statusId") != null) {
			returnVal.setStatusId((String) fields.get("statusId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("reconciledBalance") != null) {
String buf;
buf = fields.get("reconciledBalance");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReconciledBalance(bd);
}

		if(fields.get("openingBalance") != null) {
String buf;
buf = fields.get("openingBalance");
float ibuf = Float.parseFloat(buf); 
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOpeningBalance(bd);
}

		if(fields.get("reconciledDate") != null) {
String buf = fields.get("reconciledDate");
Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setReconciledDate(ibuf);
}


		return returnVal;
 } 
	public static GlReconciliation map(GenericValue val) {

GlReconciliation returnVal = new GlReconciliation();
		returnVal.setGlReconciliationId(val.getString("glReconciliationId"));
		returnVal.setGlReconciliationName(val.getString("glReconciliationName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setCreatedDate(val.getTimestamp("createdDate"));
		returnVal.setCreatedByUserLogin(val.getString("createdByUserLogin"));
		returnVal.setLastModifiedDate(val.getTimestamp("lastModifiedDate"));
		returnVal.setLastModifiedByUserLogin(val.getString("lastModifiedByUserLogin"));
		returnVal.setGlAccountId(val.getString("glAccountId"));
		returnVal.setStatusId(val.getString("statusId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setReconciledBalance(val.getBigDecimal("reconciledBalance"));
		returnVal.setOpeningBalance(val.getBigDecimal("openingBalance"));
		returnVal.setReconciledDate(val.getTimestamp("reconciledDate"));


return returnVal;

}

public static GlReconciliation map(HttpServletRequest request) throws Exception {

		GlReconciliation returnVal = new GlReconciliation();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glReconciliationId")) {
returnVal.setGlReconciliationId(request.getParameter("glReconciliationId"));
}

		if(paramMap.containsKey("glReconciliationName"))  {
returnVal.setGlReconciliationName(request.getParameter("glReconciliationName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
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
		if(paramMap.containsKey("glAccountId"))  {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}
		if(paramMap.containsKey("statusId"))  {
returnVal.setStatusId(request.getParameter("statusId"));
}
		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("reconciledBalance"))  {
String buf = request.getParameter("reconciledBalance");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setReconciledBalance(bd);
}
		if(paramMap.containsKey("openingBalance"))  {
String buf = request.getParameter("openingBalance");
Float ibuf = Float.parseFloat(buf);
BigDecimal bd = BigDecimal.valueOf(ibuf);
			returnVal.setOpeningBalance(bd);
}
		if(paramMap.containsKey("reconciledDate"))  {
String buf = request.getParameter("reconciledDate");
Timestamp ibuf = Timestamp.valueOf(buf);
returnVal.setReconciledDate(ibuf);
}
return returnVal;

}
}
