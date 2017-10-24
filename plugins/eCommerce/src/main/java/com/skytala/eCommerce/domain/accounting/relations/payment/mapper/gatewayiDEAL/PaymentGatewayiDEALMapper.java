package com.skytala.eCommerce.domain.accounting.relations.payment.mapper.gatewayiDEAL;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.payment.model.gatewayiDEAL.PaymentGatewayiDEAL;

public class PaymentGatewayiDEALMapper  {


	public static Map<String, Object> map(PaymentGatewayiDEAL paymentgatewayideal) {

		Map<String, Object> returnVal = new HashMap<String, Object>();


		if(paymentgatewayideal.getPaymentGatewayConfigId() != null ){
			returnVal.put("paymentGatewayConfigId",paymentgatewayideal.getPaymentGatewayConfigId());
}

		if(paymentgatewayideal.getMerchantId() != null ){
			returnVal.put("merchantId",paymentgatewayideal.getMerchantId());
}

		if(paymentgatewayideal.getMerchantSubId() != null ){
			returnVal.put("merchantSubId",paymentgatewayideal.getMerchantSubId());
}

		if(paymentgatewayideal.getMerchantReturnURL() != null ){
			returnVal.put("merchantReturnURL",paymentgatewayideal.getMerchantReturnURL());
}

		if(paymentgatewayideal.getAcquirerURL() != null ){
			returnVal.put("acquirerURL",paymentgatewayideal.getAcquirerURL());
}

		if(paymentgatewayideal.getAcquirerTimeout() != null ){
			returnVal.put("acquirerTimeout",paymentgatewayideal.getAcquirerTimeout());
}

		if(paymentgatewayideal.getPrivateCert() != null ){
			returnVal.put("privateCert",paymentgatewayideal.getPrivateCert());
}

		if(paymentgatewayideal.getAcquirerKeyStoreFilename() != null ){
			returnVal.put("acquirerKeyStoreFilename",paymentgatewayideal.getAcquirerKeyStoreFilename());
}

		if(paymentgatewayideal.getAcquirerKeyStorePassword() != null ){
			returnVal.put("acquirerKeyStorePassword",paymentgatewayideal.getAcquirerKeyStorePassword());
}

		if(paymentgatewayideal.getMerchantKeyStoreFilename() != null ){
			returnVal.put("merchantKeyStoreFilename",paymentgatewayideal.getMerchantKeyStoreFilename());
}

		if(paymentgatewayideal.getMerchantKeyStorePassword() != null ){
			returnVal.put("merchantKeyStorePassword",paymentgatewayideal.getMerchantKeyStorePassword());
}

		if(paymentgatewayideal.getExpirationPeriod() != null ){
			returnVal.put("expirationPeriod",paymentgatewayideal.getExpirationPeriod());
}

		return returnVal;
}


	public static PaymentGatewayiDEAL map(Map<String, Object> fields) {

		PaymentGatewayiDEAL returnVal = new PaymentGatewayiDEAL();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("merchantId") != null) {
			returnVal.setMerchantId((String) fields.get("merchantId"));
}

		if(fields.get("merchantSubId") != null) {
			returnVal.setMerchantSubId((String) fields.get("merchantSubId"));
}

		if(fields.get("merchantReturnURL") != null) {
			returnVal.setMerchantReturnURL((String) fields.get("merchantReturnURL"));
}

		if(fields.get("acquirerURL") != null) {
			returnVal.setAcquirerURL((String) fields.get("acquirerURL"));
}

		if(fields.get("acquirerTimeout") != null) {
			returnVal.setAcquirerTimeout((String) fields.get("acquirerTimeout"));
}

		if(fields.get("privateCert") != null) {
			returnVal.setPrivateCert((String) fields.get("privateCert"));
}

		if(fields.get("acquirerKeyStoreFilename") != null) {
			returnVal.setAcquirerKeyStoreFilename((String) fields.get("acquirerKeyStoreFilename"));
}

		if(fields.get("acquirerKeyStorePassword") != null) {
			returnVal.setAcquirerKeyStorePassword((String) fields.get("acquirerKeyStorePassword"));
}

		if(fields.get("merchantKeyStoreFilename") != null) {
			returnVal.setMerchantKeyStoreFilename((String) fields.get("merchantKeyStoreFilename"));
}

		if(fields.get("merchantKeyStorePassword") != null) {
			returnVal.setMerchantKeyStorePassword((String) fields.get("merchantKeyStorePassword"));
}

		if(fields.get("expirationPeriod") != null) {
			returnVal.setExpirationPeriod((String) fields.get("expirationPeriod"));
}


		return returnVal;
 } 
	public static PaymentGatewayiDEAL mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayiDEAL returnVal = new PaymentGatewayiDEAL();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("merchantId") != null) {
			returnVal.setMerchantId((String) fields.get("merchantId"));
}

		if(fields.get("merchantSubId") != null) {
			returnVal.setMerchantSubId((String) fields.get("merchantSubId"));
}

		if(fields.get("merchantReturnURL") != null) {
			returnVal.setMerchantReturnURL((String) fields.get("merchantReturnURL"));
}

		if(fields.get("acquirerURL") != null) {
			returnVal.setAcquirerURL((String) fields.get("acquirerURL"));
}

		if(fields.get("acquirerTimeout") != null) {
			returnVal.setAcquirerTimeout((String) fields.get("acquirerTimeout"));
}

		if(fields.get("privateCert") != null) {
			returnVal.setPrivateCert((String) fields.get("privateCert"));
}

		if(fields.get("acquirerKeyStoreFilename") != null) {
			returnVal.setAcquirerKeyStoreFilename((String) fields.get("acquirerKeyStoreFilename"));
}

		if(fields.get("acquirerKeyStorePassword") != null) {
			returnVal.setAcquirerKeyStorePassword((String) fields.get("acquirerKeyStorePassword"));
}

		if(fields.get("merchantKeyStoreFilename") != null) {
			returnVal.setMerchantKeyStoreFilename((String) fields.get("merchantKeyStoreFilename"));
}

		if(fields.get("merchantKeyStorePassword") != null) {
			returnVal.setMerchantKeyStorePassword((String) fields.get("merchantKeyStorePassword"));
}

		if(fields.get("expirationPeriod") != null) {
			returnVal.setExpirationPeriod((String) fields.get("expirationPeriod"));
}


		return returnVal;
 } 
	public static PaymentGatewayiDEAL map(GenericValue val) {

PaymentGatewayiDEAL returnVal = new PaymentGatewayiDEAL();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setMerchantId(val.getString("merchantId"));
		returnVal.setMerchantSubId(val.getString("merchantSubId"));
		returnVal.setMerchantReturnURL(val.getString("merchantReturnURL"));
		returnVal.setAcquirerURL(val.getString("acquirerURL"));
		returnVal.setAcquirerTimeout(val.getString("acquirerTimeout"));
		returnVal.setPrivateCert(val.getString("privateCert"));
		returnVal.setAcquirerKeyStoreFilename(val.getString("acquirerKeyStoreFilename"));
		returnVal.setAcquirerKeyStorePassword(val.getString("acquirerKeyStorePassword"));
		returnVal.setMerchantKeyStoreFilename(val.getString("merchantKeyStoreFilename"));
		returnVal.setMerchantKeyStorePassword(val.getString("merchantKeyStorePassword"));
		returnVal.setExpirationPeriod(val.getString("expirationPeriod"));


return returnVal;

}

public static PaymentGatewayiDEAL map(HttpServletRequest request) throws Exception {

		PaymentGatewayiDEAL returnVal = new PaymentGatewayiDEAL();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("merchantId"))  {
returnVal.setMerchantId(request.getParameter("merchantId"));
}
		if(paramMap.containsKey("merchantSubId"))  {
returnVal.setMerchantSubId(request.getParameter("merchantSubId"));
}
		if(paramMap.containsKey("merchantReturnURL"))  {
returnVal.setMerchantReturnURL(request.getParameter("merchantReturnURL"));
}
		if(paramMap.containsKey("acquirerURL"))  {
returnVal.setAcquirerURL(request.getParameter("acquirerURL"));
}
		if(paramMap.containsKey("acquirerTimeout"))  {
returnVal.setAcquirerTimeout(request.getParameter("acquirerTimeout"));
}
		if(paramMap.containsKey("privateCert"))  {
returnVal.setPrivateCert(request.getParameter("privateCert"));
}
		if(paramMap.containsKey("acquirerKeyStoreFilename"))  {
returnVal.setAcquirerKeyStoreFilename(request.getParameter("acquirerKeyStoreFilename"));
}
		if(paramMap.containsKey("acquirerKeyStorePassword"))  {
returnVal.setAcquirerKeyStorePassword(request.getParameter("acquirerKeyStorePassword"));
}
		if(paramMap.containsKey("merchantKeyStoreFilename"))  {
returnVal.setMerchantKeyStoreFilename(request.getParameter("merchantKeyStoreFilename"));
}
		if(paramMap.containsKey("merchantKeyStorePassword"))  {
returnVal.setMerchantKeyStorePassword(request.getParameter("merchantKeyStorePassword"));
}
		if(paramMap.containsKey("expirationPeriod"))  {
returnVal.setExpirationPeriod(request.getParameter("expirationPeriod"));
}
return returnVal;

}
}
