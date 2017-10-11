package com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.mapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.productStorePaymentSetting.model.ProductStorePaymentSetting;

public class ProductStorePaymentSettingMapper  {


	public static Map<String, Object> map(ProductStorePaymentSetting productstorepaymentsetting) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(productstorepaymentsetting.getProductStoreId() != null ){
			returnVal.put("productStoreId",productstorepaymentsetting.getProductStoreId());
}

		if(productstorepaymentsetting.getPaymentMethodTypeId() != null ){
			returnVal.put("paymentMethodTypeId",productstorepaymentsetting.getPaymentMethodTypeId());
}

		if(productstorepaymentsetting.getPaymentServiceTypeEnumId() != null ){
			returnVal.put("paymentServiceTypeEnumId",productstorepaymentsetting.getPaymentServiceTypeEnumId());
}

		if(productstorepaymentsetting.getPaymentService() != null ){
			returnVal.put("paymentService",productstorepaymentsetting.getPaymentService());
}

		if(productstorepaymentsetting.getPaymentCustomMethodId() != null ){
			returnVal.put("paymentCustomMethodId",productstorepaymentsetting.getPaymentCustomMethodId());
}

		if(productstorepaymentsetting.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",productstorepaymentsetting.getPaymentGatewayConfigId());
}

		if(productstorepaymentsetting.getPaymentPropertiesPath() != null ){
			returnVal.put("paymentPropertiesPath",productstorepaymentsetting.getPaymentPropertiesPath());
}

		if(productstorepaymentsetting.getApplyToAllProducts() != null ){
			returnVal.put("applyToAllProducts",productstorepaymentsetting.getApplyToAllProducts());
}

		return returnVal;
}


	public static ProductStorePaymentSetting map(Map<String, Object> fields) {

		ProductStorePaymentSetting returnVal = new ProductStorePaymentSetting();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("paymentServiceTypeEnumId") != null) {
			returnVal.setPaymentServiceTypeEnumId((String) fields.get("paymentServiceTypeEnumId"));
}

		if(fields.get("paymentService") != null) {
			returnVal.setPaymentService((long) fields.get("paymentService"));
}

		if(fields.get("paymentCustomMethodId") != null) {
			returnVal.setPaymentCustomMethodId((String) fields.get("paymentCustomMethodId"));
}

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("paymentPropertiesPath") != null) {
			returnVal.setPaymentPropertiesPath((long) fields.get("paymentPropertiesPath"));
}

		if(fields.get("applyToAllProducts") != null) {
			returnVal.setApplyToAllProducts((boolean) fields.get("applyToAllProducts"));
}


		return returnVal;
 } 
	public static ProductStorePaymentSetting mapstrstr(Map<String, String> fields) throws Exception {

		ProductStorePaymentSetting returnVal = new ProductStorePaymentSetting();

		if(fields.get("productStoreId") != null) {
			returnVal.setProductStoreId((String) fields.get("productStoreId"));
}

		if(fields.get("paymentMethodTypeId") != null) {
			returnVal.setPaymentMethodTypeId((String) fields.get("paymentMethodTypeId"));
}

		if(fields.get("paymentServiceTypeEnumId") != null) {
			returnVal.setPaymentServiceTypeEnumId((String) fields.get("paymentServiceTypeEnumId"));
}

		if(fields.get("paymentService") != null) {
String buf;
buf = fields.get("paymentService");
long ibuf = Long.parseLong(buf);
			returnVal.setPaymentService(ibuf);
}

		if(fields.get("paymentCustomMethodId") != null) {
			returnVal.setPaymentCustomMethodId((String) fields.get("paymentCustomMethodId"));
}

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("paymentPropertiesPath") != null) {
String buf;
buf = fields.get("paymentPropertiesPath");
long ibuf = Long.parseLong(buf);
			returnVal.setPaymentPropertiesPath(ibuf);
}

		if(fields.get("applyToAllProducts") != null) {
String buf;
buf = fields.get("applyToAllProducts");
Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setApplyToAllProducts(ibuf);
}


		return returnVal;
 } 
	public static ProductStorePaymentSetting map(GenericValue val) {

ProductStorePaymentSetting returnVal = new ProductStorePaymentSetting();
		returnVal.setProductStoreId(val.getString("productStoreId"));
		returnVal.setPaymentMethodTypeId(val.getString("paymentMethodTypeId"));
		returnVal.setPaymentServiceTypeEnumId(val.getString("paymentServiceTypeEnumId"));
		returnVal.setPaymentService(val.getLong("paymentService"));
		returnVal.setPaymentCustomMethodId(val.getString("paymentCustomMethodId"));
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setPaymentPropertiesPath(val.getLong("paymentPropertiesPath"));
		returnVal.setApplyToAllProducts(val.getBoolean("applyToAllProducts"));


return returnVal;

}

public static ProductStorePaymentSetting map(HttpServletRequest request) throws Exception {

		ProductStorePaymentSetting returnVal = new ProductStorePaymentSetting();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("productStoreId")) {
returnVal.setProductStoreId(request.getParameter("productStoreId"));
}

		if(paramMap.containsKey("paymentMethodTypeId"))  {
returnVal.setPaymentMethodTypeId(request.getParameter("paymentMethodTypeId"));
}
		if(paramMap.containsKey("paymentServiceTypeEnumId"))  {
returnVal.setPaymentServiceTypeEnumId(request.getParameter("paymentServiceTypeEnumId"));
}
		if(paramMap.containsKey("paymentService"))  {
String buf = request.getParameter("paymentService");
Long ibuf = Long.parseLong(buf);
returnVal.setPaymentService(ibuf);
}
		if(paramMap.containsKey("paymentCustomMethodId"))  {
returnVal.setPaymentCustomMethodId(request.getParameter("paymentCustomMethodId"));
}
		if(paramMap.containsKey("paymentGatewayConfigId"))  {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}
		if(paramMap.containsKey("paymentPropertiesPath"))  {
String buf = request.getParameter("paymentPropertiesPath");
Long ibuf = Long.parseLong(buf);
returnVal.setPaymentPropertiesPath(ibuf);
}
		if(paramMap.containsKey("applyToAllProducts"))  {
String buf = request.getParameter("applyToAllProducts");
Boolean ibuf = Boolean.parseBoolean(buf);
returnVal.setApplyToAllProducts(ibuf);
}
return returnVal;

}
}
