package com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStoreEmailSetting.model.ProductStoreEmailSetting;

public class ProductStoreEmailSettingMapper  {


	public static Map<String, Object> map(ProductStoreEmailSetting productstoreemailsetting) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstoreemailsetting.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstoreemailsetting.getProductStoreId());
}

		if(productstoreemailsetting.getEmailType() != null ){
			returnVal.put("emailType",productstoreemailsetting.getEmailType());
}

		if(productstoreemailsetting.getBodyScreenLocation() != null ){
			returnVal.put("bodyScreenLocation",productstoreemailsetting.getBodyScreenLocation());
}

		if(productstoreemailsetting.getXslfoAttachScreenLocation() != null ){
			returnVal.put("xslfoAttachScreenLocation",productstoreemailsetting.getXslfoAttachScreenLocation());
}

		if(productstoreemailsetting.getFromAddress() != null ){
			returnVal.put("fromAddress",productstoreemailsetting.getFromAddress());
}

		if(productstoreemailsetting.getCcAddress() != null ){
			returnVal.put("ccAddress",productstoreemailsetting.getCcAddress());
}

		if(productstoreemailsetting.getBccAddress() != null ){
			returnVal.put("bccAddress",productstoreemailsetting.getBccAddress());
}

		if(productstoreemailsetting.getSubject() != null ){
			returnVal.put("subject",productstoreemailsetting.getSubject());
}

		if(productstoreemailsetting.getContentType() != null ){
			returnVal.put("contentType",productstoreemailsetting.getContentType());
}

		return returnVal;
}


	public static ProductStoreEmailSetting map(Map<String, Object> fields) {

		ProductStoreEmailSetting returnVal = new ProductStoreEmailSetting();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("emailType") != null) {
			returnVal.setEmailType((String) fields.get("emailType"));
}

		if(fields.get("bodyScreenLocation") != null) {
			returnVal.setBodyScreenLocation((String) fields.get("bodyScreenLocation"));
}

		if(fields.get("xslfoAttachScreenLocation") != null) {
			returnVal.setXslfoAttachScreenLocation((String) fields.get("xslfoAttachScreenLocation"));
}

		if(fields.get("fromAddress") != null) {
			returnVal.setFromAddress((String) fields.get("fromAddress"));
}

		if(fields.get("ccAddress") != null) {
			returnVal.setCcAddress((String) fields.get("ccAddress"));
}

		if(fields.get("bccAddress") != null) {
			returnVal.setBccAddress((String) fields.get("bccAddress"));
}

		if(fields.get("subject") != null) {
			returnVal.setSubject((String) fields.get("subject"));
}

		if(fields.get("contentType") != null) {
			returnVal.setContentType((String) fields.get("contentType"));
}


		return returnVal;
 } 
	public static ProductStoreEmailSetting mapstrstr(Map<String, String> fields) throws Exception {

		ProductStoreEmailSetting returnVal = new ProductStoreEmailSetting();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("emailType") != null) {
			returnVal.setEmailType((String) fields.get("emailType"));
}

		if(fields.get("bodyScreenLocation") != null) {
			returnVal.setBodyScreenLocation((String) fields.get("bodyScreenLocation"));
}

		if(fields.get("xslfoAttachScreenLocation") != null) {
			returnVal.setXslfoAttachScreenLocation((String) fields.get("xslfoAttachScreenLocation"));
}

		if(fields.get("fromAddress") != null) {
			returnVal.setFromAddress((String) fields.get("fromAddress"));
}

		if(fields.get("ccAddress") != null) {
			returnVal.setCcAddress((String) fields.get("ccAddress"));
}

		if(fields.get("bccAddress") != null) {
			returnVal.setBccAddress((String) fields.get("bccAddress"));
}

		if(fields.get("subject") != null) {
			returnVal.setSubject((String) fields.get("subject"));
}

		if(fields.get("contentType") != null) {
			returnVal.setContentType((String) fields.get("contentType"));
}


		return returnVal;
 } 
	public static ProductStoreEmailSetting map(GenericValue val) {

ProductStoreEmailSetting returnVal = new ProductStoreEmailSetting();
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setEmailType(val.getString("emailType"));
		returnVal.setBodyScreenLocation(val.getString("bodyScreenLocation"));
		returnVal.setXslfoAttachScreenLocation(val.getString("xslfoAttachScreenLocation"));
		returnVal.setFromAddress(val.getString("fromAddress"));
		returnVal.setCcAddress(val.getString("ccAddress"));
		returnVal.setBccAddress(val.getString("bccAddress"));
		returnVal.setSubject(val.getString("subject"));
		returnVal.setContentType(val.getString("contentType"));


return returnVal;

}

public static ProductStoreEmailSetting map(HttpServletRequest request) throws Exception {

		ProductStoreEmailSetting returnVal = new ProductStoreEmailSetting();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreId")) {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}

		if(paramMap.containsKey("emailType"))  {
returnVal.setEmailType(request.getParameter("emailType"));
}
		if(paramMap.containsKey("bodyScreenLocation"))  {
returnVal.setBodyScreenLocation(request.getParameter("bodyScreenLocation"));
}
		if(paramMap.containsKey("xslfoAttachScreenLocation"))  {
returnVal.setXslfoAttachScreenLocation(request.getParameter("xslfoAttachScreenLocation"));
}
		if(paramMap.containsKey("fromAddress"))  {
returnVal.setFromAddress(request.getParameter("fromAddress"));
}
		if(paramMap.containsKey("ccAddress"))  {
returnVal.setCcAddress(request.getParameter("ccAddress"));
}
		if(paramMap.containsKey("bccAddress"))  {
returnVal.setBccAddress(request.getParameter("bccAddress"));
}
		if(paramMap.containsKey("subject"))  {
returnVal.setSubject(request.getParameter("subject"));
}
		if(paramMap.containsKey("contentType"))  {
returnVal.setContentType(request.getParameter("contentType"));
}
return returnVal;

}
}
