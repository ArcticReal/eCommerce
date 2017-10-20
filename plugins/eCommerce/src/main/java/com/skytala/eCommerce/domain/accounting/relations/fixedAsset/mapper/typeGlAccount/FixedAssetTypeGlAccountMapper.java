package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.typeGlAccount;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeGlAccount.FixedAssetTypeGlAccount;

public class FixedAssetTypeGlAccountMapper  {


	public static Map<String, Object> map(FixedAssetTypeGlAccount fixedassettypeglaccount) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(fixedassettypeglaccount.getFixedAssetTypeId() != null ){
			returnVal.put("fixedAssetTypeId",fixedassettypeglaccount.getFixedAssetTypeId());
}

		if(fixedassettypeglaccount.getFixedAssetId() != null ){
			returnVal.put("fixedAssetId",fixedassettypeglaccount.getFixedAssetId());
}

		if(fixedassettypeglaccount.getOrganizationPartyId() != null ){
			returnVal.put("organizationPartyId",fixedassettypeglaccount.getOrganizationPartyId());
}

		if(fixedassettypeglaccount.getAssetGlAccountId() != null ){
			returnVal.put("assetGlAccountId",fixedassettypeglaccount.getAssetGlAccountId());
}

		if(fixedassettypeglaccount.getAccDepGlAccountId() != null ){
			returnVal.put("accDepGlAccountId",fixedassettypeglaccount.getAccDepGlAccountId());
}

		if(fixedassettypeglaccount.getDepGlAccountId() != null ){
			returnVal.put("depGlAccountId",fixedassettypeglaccount.getDepGlAccountId());
}

		if(fixedassettypeglaccount.getProfitGlAccountId() != null ){
			returnVal.put("profitGlAccountId",fixedassettypeglaccount.getProfitGlAccountId());
}

		if(fixedassettypeglaccount.getLossGlAccountId() != null ){
			returnVal.put("lossGlAccountId",fixedassettypeglaccount.getLossGlAccountId());
}

		return returnVal;
}


	public static FixedAssetTypeGlAccount map(Map<String, Object> fields) {

		FixedAssetTypeGlAccount returnVal = new FixedAssetTypeGlAccount();

		if(fields.get("fixedAssetTypeId") != null) {
			returnVal.setFixedAssetTypeId((String) fields.get("fixedAssetTypeId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("assetGlAccountId") != null) {
			returnVal.setAssetGlAccountId((String) fields.get("assetGlAccountId"));
}

		if(fields.get("accDepGlAccountId") != null) {
			returnVal.setAccDepGlAccountId((String) fields.get("accDepGlAccountId"));
}

		if(fields.get("depGlAccountId") != null) {
			returnVal.setDepGlAccountId((String) fields.get("depGlAccountId"));
}

		if(fields.get("profitGlAccountId") != null) {
			returnVal.setProfitGlAccountId((String) fields.get("profitGlAccountId"));
}

		if(fields.get("lossGlAccountId") != null) {
			returnVal.setLossGlAccountId((String) fields.get("lossGlAccountId"));
}


		return returnVal;
 } 
	public static FixedAssetTypeGlAccount mapstrstr(Map<String, String> fields) throws Exception {

		FixedAssetTypeGlAccount returnVal = new FixedAssetTypeGlAccount();

		if(fields.get("fixedAssetTypeId") != null) {
			returnVal.setFixedAssetTypeId((String) fields.get("fixedAssetTypeId"));
}

		if(fields.get("fixedAssetId") != null) {
			returnVal.setFixedAssetId((String) fields.get("fixedAssetId"));
}

		if(fields.get("organizationPartyId") != null) {
			returnVal.setOrganizationPartyId((String) fields.get("organizationPartyId"));
}

		if(fields.get("assetGlAccountId") != null) {
			returnVal.setAssetGlAccountId((String) fields.get("assetGlAccountId"));
}

		if(fields.get("accDepGlAccountId") != null) {
			returnVal.setAccDepGlAccountId((String) fields.get("accDepGlAccountId"));
}

		if(fields.get("depGlAccountId") != null) {
			returnVal.setDepGlAccountId((String) fields.get("depGlAccountId"));
}

		if(fields.get("profitGlAccountId") != null) {
			returnVal.setProfitGlAccountId((String) fields.get("profitGlAccountId"));
}

		if(fields.get("lossGlAccountId") != null) {
			returnVal.setLossGlAccountId((String) fields.get("lossGlAccountId"));
}


		return returnVal;
 } 
	public static FixedAssetTypeGlAccount map(GenericValue val) {

FixedAssetTypeGlAccount returnVal = new FixedAssetTypeGlAccount();
		returnVal.setFixedAssetTypeId(val.getString("fixedAssetTypeId"));
		returnVal.setFixedAssetId(val.getString("fixedAssetId"));
		returnVal.setOrganizationPartyId(val.getString("organizationPartyId"));
		returnVal.setAssetGlAccountId(val.getString("assetGlAccountId"));
		returnVal.setAccDepGlAccountId(val.getString("accDepGlAccountId"));
		returnVal.setDepGlAccountId(val.getString("depGlAccountId"));
		returnVal.setProfitGlAccountId(val.getString("profitGlAccountId"));
		returnVal.setLossGlAccountId(val.getString("lossGlAccountId"));


return returnVal;

}

public static FixedAssetTypeGlAccount map(HttpServletRequest request) throws Exception {

		FixedAssetTypeGlAccount returnVal = new FixedAssetTypeGlAccount();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("fixedAssetTypeId")) {
returnVal.setFixedAssetTypeId(request.getParameter("fixedAssetTypeId"));
}

		if(paramMap.containsKey("fixedAssetId"))  {
returnVal.setFixedAssetId(request.getParameter("fixedAssetId"));
}
		if(paramMap.containsKey("organizationPartyId"))  {
returnVal.setOrganizationPartyId(request.getParameter("organizationPartyId"));
}
		if(paramMap.containsKey("assetGlAccountId"))  {
returnVal.setAssetGlAccountId(request.getParameter("assetGlAccountId"));
}
		if(paramMap.containsKey("accDepGlAccountId"))  {
returnVal.setAccDepGlAccountId(request.getParameter("accDepGlAccountId"));
}
		if(paramMap.containsKey("depGlAccountId"))  {
returnVal.setDepGlAccountId(request.getParameter("depGlAccountId"));
}
		if(paramMap.containsKey("profitGlAccountId"))  {
returnVal.setProfitGlAccountId(request.getParameter("profitGlAccountId"));
}
		if(paramMap.containsKey("lossGlAccountId"))  {
returnVal.setLossGlAccountId(request.getParameter("lossGlAccountId"));
}
return returnVal;

}
}
