package com.skytala.eCommerce.domain.prodCatalog.mapper;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.prodCatalog.model.ProdCatalog;

public class ProdCatalogMapper  {


	public static Map<String, Object> map(ProdCatalog prodcatalog) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(prodcatalog.getProdCatalogId() != null ){
			returnVal.put("prodCatalogId",prodcatalog.getProdCatalogId());
}

		if(prodcatalog.getCatalogName() != null ){
			returnVal.put("catalogName",prodcatalog.getCatalogName());
}

		if(prodcatalog.getUseQuickAdd() != null ){
			returnVal.put("useQuickAdd",prodcatalog.getUseQuickAdd());
}

		if(prodcatalog.getStyleSheet() != null ){
			returnVal.put("styleSheet",prodcatalog.getStyleSheet());
}

		if(prodcatalog.getHeaderLogo() != null ){
			returnVal.put("headerLogo",prodcatalog.getHeaderLogo());
}

		if(prodcatalog.getContentPathPrefix() != null ){
			returnVal.put("contentPathPrefix",prodcatalog.getContentPathPrefix());
}

		if(prodcatalog.getTemplatePathPrefix() != null ){
			returnVal.put("templatePathPrefix",prodcatalog.getTemplatePathPrefix());
}

		if(prodcatalog.getViewAllowPermReqd() != null ){
			returnVal.put("viewAllowPermReqd",prodcatalog.getViewAllowPermReqd());
}

		if(prodcatalog.getPurchaseAllowPermReqd() != null ){
			returnVal.put("purchaseAllowPermReqd",prodcatalog.getPurchaseAllowPermReqd());
}

		return returnVal;
}


	public static ProdCatalog map(Map<String, Object> fields) {

		ProdCatalog returnVal = new ProdCatalog();

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
}

		if(fields.get("catalogName") != null) {
			returnVal.setCatalogName((String) fields.get("catalogName"));
}

		if(fields.get("useQuickAdd") != null) {
			returnVal.setUseQuickAdd((boolean) fields.get("useQuickAdd"));
}

		if(fields.get("styleSheet") != null) {
			returnVal.setStyleSheet((String) fields.get("styleSheet"));
}

		if(fields.get("headerLogo") != null) {
			returnVal.setHeaderLogo((String) fields.get("headerLogo"));
}

		if(fields.get("contentPathPrefix") != null) {
			returnVal.setContentPathPrefix((String) fields.get("contentPathPrefix"));
}

		if(fields.get("templatePathPrefix") != null) {
			returnVal.setTemplatePathPrefix((String) fields.get("templatePathPrefix"));
}

		if(fields.get("viewAllowPermReqd") != null) {
			returnVal.setViewAllowPermReqd((boolean) fields.get("viewAllowPermReqd"));
}

		if(fields.get("purchaseAllowPermReqd") != null) {
			returnVal.setPurchaseAllowPermReqd((boolean) fields.get("purchaseAllowPermReqd"));
}


		return returnVal;
 } 
	public static ProdCatalog mapstrstr(Map<String, String> fields) throws Exception {

		ProdCatalog returnVal = new ProdCatalog();

		if(fields.get("prodCatalogId") != null) {
			returnVal.setProdCatalogId((String) fields.get("prodCatalogId"));
}

		if(fields.get("catalogName") != null) {
			returnVal.setCatalogName((String) fields.get("catalogName"));
}

		if(fields.get("useQuickAdd") != null) {
String buf;
buf = fields.get("useQuickAdd");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setUseQuickAdd(ibuf);
}

		if(fields.get("styleSheet") != null) {
			returnVal.setStyleSheet((String) fields.get("styleSheet"));
}

		if(fields.get("headerLogo") != null) {
			returnVal.setHeaderLogo((String) fields.get("headerLogo"));
}

		if(fields.get("contentPathPrefix") != null) {
			returnVal.setContentPathPrefix((String) fields.get("contentPathPrefix"));
}

		if(fields.get("templatePathPrefix") != null) {
			returnVal.setTemplatePathPrefix((String) fields.get("templatePathPrefix"));
}

		if(fields.get("viewAllowPermReqd") != null) {
String buf;
buf = fields.get("viewAllowPermReqd");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setViewAllowPermReqd(ibuf);
}

		if(fields.get("purchaseAllowPermReqd") != null) {
String buf;
buf = fields.get("purchaseAllowPermReqd");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setPurchaseAllowPermReqd(ibuf);
}


		return returnVal;
 } 
	public static ProdCatalog map(GenericValue val) {

ProdCatalog returnVal = new ProdCatalog();
		returnVal.setProdCatalogId(val.getString("prodCatalogId"));
		returnVal.setCatalogName(val.getString("catalogName"));
		returnVal.setUseQuickAdd(val.getBoolean("useQuickAdd"));
		returnVal.setStyleSheet(val.getString("styleSheet"));
		returnVal.setHeaderLogo(val.getString("headerLogo"));
		returnVal.setContentPathPrefix(val.getString("contentPathPrefix"));
		returnVal.setTemplatePathPrefix(val.getString("templatePathPrefix"));
		returnVal.setViewAllowPermReqd(val.getBoolean("viewAllowPermReqd"));
		returnVal.setPurchaseAllowPermReqd(val.getBoolean("purchaseAllowPermReqd"));


return returnVal;

}

public static ProdCatalog map(HttpServletRequest request) throws Exception {

		ProdCatalog returnVal = new ProdCatalog();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("prodCatalogId")) {
returnVal.setProdCatalogId(request.getParameter("prodCatalogId"));
}

		if(paramMap.containsKey("catalogName"))  {
returnVal.setCatalogName(request.getParameter("catalogName"));
}
		if(paramMap.containsKey("useQuickAdd"))  {
String buf = request.getParameter("useQuickAdd");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setUseQuickAdd(ibuf);
}
		if(paramMap.containsKey("styleSheet"))  {
returnVal.setStyleSheet(request.getParameter("styleSheet"));
}
		if(paramMap.containsKey("headerLogo"))  {
returnVal.setHeaderLogo(request.getParameter("headerLogo"));
}
		if(paramMap.containsKey("contentPathPrefix"))  {
returnVal.setContentPathPrefix(request.getParameter("contentPathPrefix"));
}
		if(paramMap.containsKey("templatePathPrefix"))  {
returnVal.setTemplatePathPrefix(request.getParameter("templatePathPrefix"));
}
		if(paramMap.containsKey("viewAllowPermReqd"))  {
String buf = request.getParameter("viewAllowPermReqd");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setViewAllowPermReqd(ibuf);
}
		if(paramMap.containsKey("purchaseAllowPermReqd"))  {
String buf = request.getParameter("purchaseAllowPermReqd");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setPurchaseAllowPermReqd(ibuf);
}
return returnVal;

}
}
