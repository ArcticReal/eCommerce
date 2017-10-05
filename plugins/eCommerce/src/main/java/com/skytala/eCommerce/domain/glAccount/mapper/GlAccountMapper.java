package com.skytala.eCommerce.domain.glAccount.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.glAccount.model.GlAccount;

public class GlAccountMapper  {


	public static Map<String, Object> map(GlAccount glaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(glaccount.getGlAccountId() != null ){
			returnVal.put("glAccountId",glaccount.getGlAccountId());
}

		if(glaccount.getGlAccountTypeId() != null ){
			returnVal.put("glAccountTypeId",glaccount.getGlAccountTypeId());
}

		if(glaccount.getGlAccountClassId() != null ){
			returnVal.put("glAccountClassId",glaccount.getGlAccountClassId());
}

		if(glaccount.getGlResourceTypeId() != null ){
			returnVal.put("glResourceTypeId",glaccount.getGlResourceTypeId());
}

		if(glaccount.getGlXbrlClassId() != null ){
			returnVal.put("glXbrlClassId",glaccount.getGlXbrlClassId());
}

		if(glaccount.getParentGlAccountId() != null ){
			returnVal.put("parentGlAccountId",glaccount.getParentGlAccountId());
}

		if(glaccount.getAccountCode() != null ){
			returnVal.put("accountCode",glaccount.getAccountCode());
}

		if(glaccount.getAccountName() != null ){
			returnVal.put("accountName",glaccount.getAccountName());
}

		if(glaccount.getDescription() != null ){
			returnVal.put("description",glaccount.getDescription());
}

		if(glaccount.getProductId() != null ){
			returnVal.put("productId",glaccount.getProductId());
}

		if(glaccount.getExternalId() != null ){
			returnVal.put("externalId",glaccount.getExternalId());
}

		return returnVal;
}


	public static GlAccount map(Map<String, Object> fields) {

		GlAccount returnVal = new GlAccount();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}

		if(fields.get("glAccountClassId") != null) {
			returnVal.setGlAccountClassId((String) fields.get("glAccountClassId"));
}

		if(fields.get("glResourceTypeId") != null) {
			returnVal.setGlResourceTypeId((String) fields.get("glResourceTypeId"));
}

		if(fields.get("glXbrlClassId") != null) {
			returnVal.setGlXbrlClassId((String) fields.get("glXbrlClassId"));
}

		if(fields.get("parentGlAccountId") != null) {
			returnVal.setParentGlAccountId((String) fields.get("parentGlAccountId"));
}

		if(fields.get("accountCode") != null) {
			returnVal.setAccountCode((String) fields.get("accountCode"));
}

		if(fields.get("accountName") != null) {
			returnVal.setAccountName((String) fields.get("accountName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("externalId") != null) {
			returnVal.setExternalId((String) fields.get("externalId"));
}


		return returnVal;
 } 
	public static GlAccount mapstrstr(Map<String, String> fields) throws Exception {

		GlAccount returnVal = new GlAccount();

		if(fields.get("glAccountId") != null) {
			returnVal.setGlAccountId((String) fields.get("glAccountId"));
}

		if(fields.get("glAccountTypeId") != null) {
			returnVal.setGlAccountTypeId((String) fields.get("glAccountTypeId"));
}

		if(fields.get("glAccountClassId") != null) {
			returnVal.setGlAccountClassId((String) fields.get("glAccountClassId"));
}

		if(fields.get("glResourceTypeId") != null) {
			returnVal.setGlResourceTypeId((String) fields.get("glResourceTypeId"));
}

		if(fields.get("glXbrlClassId") != null) {
			returnVal.setGlXbrlClassId((String) fields.get("glXbrlClassId"));
}

		if(fields.get("parentGlAccountId") != null) {
			returnVal.setParentGlAccountId((String) fields.get("parentGlAccountId"));
}

		if(fields.get("accountCode") != null) {
			returnVal.setAccountCode((String) fields.get("accountCode"));
}

		if(fields.get("accountName") != null) {
			returnVal.setAccountName((String) fields.get("accountName"));
}

		if(fields.get("description") != null) {
			returnVal.setDescription((String) fields.get("description"));
}

		if(fields.get("productId") != null) {
			returnVal.setProductId((String) fields.get("productId"));
}

		if(fields.get("externalId") != null) {
			returnVal.setExternalId((String) fields.get("externalId"));
}


		return returnVal;
 } 
	public static GlAccount map(GenericValue val) {

GlAccount returnVal = new GlAccount();
		returnVal.setGlAccountId(val.getString("glAccountId"));
		returnVal.setGlAccountTypeId(val.getString("glAccountTypeId"));
		returnVal.setGlAccountClassId(val.getString("glAccountClassId"));
		returnVal.setGlResourceTypeId(val.getString("glResourceTypeId"));
		returnVal.setGlXbrlClassId(val.getString("glXbrlClassId"));
		returnVal.setParentGlAccountId(val.getString("parentGlAccountId"));
		returnVal.setAccountCode(val.getString("accountCode"));
		returnVal.setAccountName(val.getString("accountName"));
		returnVal.setDescription(val.getString("description"));
		returnVal.setProductId(val.getString("productId"));
		returnVal.setExternalId(val.getString("externalId"));


return returnVal;

}

public static GlAccount map(HttpServletRequest request) throws Exception {

		GlAccount returnVal = new GlAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("glAccountId")) {
returnVal.setGlAccountId(request.getParameter("glAccountId"));
}

		if(paramMap.containsKey("glAccountTypeId"))  {
returnVal.setGlAccountTypeId(request.getParameter("glAccountTypeId"));
}
		if(paramMap.containsKey("glAccountClassId"))  {
returnVal.setGlAccountClassId(request.getParameter("glAccountClassId"));
}
		if(paramMap.containsKey("glResourceTypeId"))  {
returnVal.setGlResourceTypeId(request.getParameter("glResourceTypeId"));
}
		if(paramMap.containsKey("glXbrlClassId"))  {
returnVal.setGlXbrlClassId(request.getParameter("glXbrlClassId"));
}
		if(paramMap.containsKey("parentGlAccountId"))  {
returnVal.setParentGlAccountId(request.getParameter("parentGlAccountId"));
}
		if(paramMap.containsKey("accountCode"))  {
returnVal.setAccountCode(request.getParameter("accountCode"));
}
		if(paramMap.containsKey("accountName"))  {
returnVal.setAccountName(request.getParameter("accountName"));
}
		if(paramMap.containsKey("description"))  {
returnVal.setDescription(request.getParameter("description"));
}
		if(paramMap.containsKey("productId"))  {
returnVal.setProductId(request.getParameter("productId"));
}
		if(paramMap.containsKey("externalId"))  {
returnVal.setExternalId(request.getParameter("externalId"));
}
return returnVal;

}
}
