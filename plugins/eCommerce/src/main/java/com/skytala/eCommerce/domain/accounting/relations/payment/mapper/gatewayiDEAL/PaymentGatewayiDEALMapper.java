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
			returnVal.setMerchantId((long) fields.get("merchantId"));
}

		if(fields.get("merchantSubId") != null) {
			returnVal.setMerchantSubId((long) fields.get("merchantSubId"));
}

		if(fields.get("merchantReturnURL") != null) {
			returnVal.setMerchantReturnURL((long) fields.get("merchantReturnURL"));
}

		if(fields.get("acquirerURL") != null) {
			returnVal.setAcquirerURL((long) fields.get("acquirerURL"));
}

		if(fields.get("acquirerTimeout") != null) {
			returnVal.setAcquirerTimeout((long) fields.get("acquirerTimeout"));
}

		if(fields.get("privateCert") != null) {
			returnVal.setPrivateCert((long) fields.get("privateCert"));
}

		if(fields.get("acquirerKeyStoreFilename") != null) {
			returnVal.setAcquirerKeyStoreFilename((long) fields.get("acquirerKeyStoreFilename"));
}

		if(fields.get("acquirerKeyStorePassword") != null) {
			returnVal.setAcquirerKeyStorePassword((long) fields.get("acquirerKeyStorePassword"));
}

		if(fields.get("merchantKeyStoreFilename") != null) {
			returnVal.setMerchantKeyStoreFilename((long) fields.get("merchantKeyStoreFilename"));
}

		if(fields.get("merchantKeyStorePassword") != null) {
			returnVal.setMerchantKeyStorePassword((long) fields.get("merchantKeyStorePassword"));
}

		if(fields.get("expirationPeriod") != null) {
			returnVal.setExpirationPeriod((long) fields.get("expirationPeriod"));
}


		return returnVal;
 } 
	public static PaymentGatewayiDEAL mapstrstr(Map<String, String> fields) throws Exception {

		PaymentGatewayiDEAL returnVal = new PaymentGatewayiDEAL();

		if(fields.get("paymentGatewayConfigId") != null) {
			returnVal.setPaymentGatewayConfigId((String) fields.get("paymentGatewayConfigId"));
}

		if(fields.get("merchantId") != null) {
String buf;
buf = fields.get("merchantId");
long ibuf = Long.parseLong(buf);
			returnVal.setMerchantId(ibuf);
}

		if(fields.get("merchantSubId") != null) {
String buf;
buf = fields.get("merchantSubId");
long ibuf = Long.parseLong(buf);
			returnVal.setMerchantSubId(ibuf);
}

		if(fields.get("merchantReturnURL") != null) {
String buf;
buf = fields.get("merchantReturnURL");
long ibuf = Long.parseLong(buf);
			returnVal.setMerchantReturnURL(ibuf);
}

		if(fields.get("acquirerURL") != null) {
String buf;
buf = fields.get("acquirerURL");
long ibuf = Long.parseLong(buf);
			returnVal.setAcquirerURL(ibuf);
}

		if(fields.get("acquirerTimeout") != null) {
String buf;
buf = fields.get("acquirerTimeout");
long ibuf = Long.parseLong(buf);
			returnVal.setAcquirerTimeout(ibuf);
}

		if(fields.get("privateCert") != null) {
String buf;
buf = fields.get("privateCert");
long ibuf = Long.parseLong(buf);
			returnVal.setPrivateCert(ibuf);
}

		if(fields.get("acquirerKeyStoreFilename") != null) {
String buf;
buf = fields.get("acquirerKeyStoreFilename");
long ibuf = Long.parseLong(buf);
			returnVal.setAcquirerKeyStoreFilename(ibuf);
}

		if(fields.get("acquirerKeyStorePassword") != null) {
String buf;
buf = fields.get("acquirerKeyStorePassword");
long ibuf = Long.parseLong(buf);
			returnVal.setAcquirerKeyStorePassword(ibuf);
}

		if(fields.get("merchantKeyStoreFilename") != null) {
String buf;
buf = fields.get("merchantKeyStoreFilename");
long ibuf = Long.parseLong(buf);
			returnVal.setMerchantKeyStoreFilename(ibuf);
}

		if(fields.get("merchantKeyStorePassword") != null) {
String buf;
buf = fields.get("merchantKeyStorePassword");
long ibuf = Long.parseLong(buf);
			returnVal.setMerchantKeyStorePassword(ibuf);
}

		if(fields.get("expirationPeriod") != null) {
String buf;
buf = fields.get("expirationPeriod");
long ibuf = Long.parseLong(buf);
			returnVal.setExpirationPeriod(ibuf);
}


		return returnVal;
 } 
	public static PaymentGatewayiDEAL map(GenericValue val) {

PaymentGatewayiDEAL returnVal = new PaymentGatewayiDEAL();
		returnVal.setPaymentGatewayConfigId(val.getString("paymentGatewayConfigId"));
		returnVal.setMerchantId(val.getLong("merchantId"));
		returnVal.setMerchantSubId(val.getLong("merchantSubId"));
		returnVal.setMerchantReturnURL(val.getLong("merchantReturnURL"));
		returnVal.setAcquirerURL(val.getLong("acquirerURL"));
		returnVal.setAcquirerTimeout(val.getLong("acquirerTimeout"));
		returnVal.setPrivateCert(val.getLong("privateCert"));
		returnVal.setAcquirerKeyStoreFilename(val.getLong("acquirerKeyStoreFilename"));
		returnVal.setAcquirerKeyStorePassword(val.getLong("acquirerKeyStorePassword"));
		returnVal.setMerchantKeyStoreFilename(val.getLong("merchantKeyStoreFilename"));
		returnVal.setMerchantKeyStorePassword(val.getLong("merchantKeyStorePassword"));
		returnVal.setExpirationPeriod(val.getLong("expirationPeriod"));


return returnVal;

}

public static PaymentGatewayiDEAL map(HttpServletRequest request) throws Exception {

		PaymentGatewayiDEAL returnVal = new PaymentGatewayiDEAL();

Map<String, String[]> paramMap = request.getParameterMap();

		if(paramMap.containsKey("paymentGatewayConfigId")) {
returnVal.setPaymentGatewayConfigId(request.getParameter("paymentGatewayConfigId"));
}

		if(paramMap.containsKey("merchantId"))  {
String buf = request.getParameter("merchantId");
Long ibuf = Long.parseLong(buf);
returnVal.setMerchantId(ibuf);
}
		if(paramMap.containsKey("merchantSubId"))  {
String buf = request.getParameter("merchantSubId");
Long ibuf = Long.parseLong(buf);
returnVal.setMerchantSubId(ibuf);
}
		if(paramMap.containsKey("merchantReturnURL"))  {
String buf = request.getParameter("merchantReturnURL");
Long ibuf = Long.parseLong(buf);
returnVal.setMerchantReturnURL(ibuf);
}
		if(paramMap.containsKey("acquirerURL"))  {
String buf = request.getParameter("acquirerURL");
Long ibuf = Long.parseLong(buf);
returnVal.setAcquirerURL(ibuf);
}
		if(paramMap.containsKey("acquirerTimeout"))  {
String buf = request.getParameter("acquirerTimeout");
Long ibuf = Long.parseLong(buf);
returnVal.setAcquirerTimeout(ibuf);
}
		if(paramMap.containsKey("privateCert"))  {
String buf = request.getParameter("privateCert");
Long ibuf = Long.parseLong(buf);
returnVal.setPrivateCert(ibuf);
}
		if(paramMap.containsKey("acquirerKeyStoreFilename"))  {
String buf = request.getParameter("acquirerKeyStoreFilename");
Long ibuf = Long.parseLong(buf);
returnVal.setAcquirerKeyStoreFilename(ibuf);
}
		if(paramMap.containsKey("acquirerKeyStorePassword"))  {
String buf = request.getParameter("acquirerKeyStorePassword");
Long ibuf = Long.parseLong(buf);
returnVal.setAcquirerKeyStorePassword(ibuf);
}
		if(paramMap.containsKey("merchantKeyStoreFilename"))  {
String buf = request.getParameter("merchantKeyStoreFilename");
Long ibuf = Long.parseLong(buf);
returnVal.setMerchantKeyStoreFilename(ibuf);
}
		if(paramMap.containsKey("merchantKeyStorePassword"))  {
String buf = request.getParameter("merchantKeyStorePassword");
Long ibuf = Long.parseLong(buf);
returnVal.setMerchantKeyStorePassword(ibuf);
}
		if(paramMap.containsKey("expirationPeriod"))  {
String buf = request.getParameter("expirationPeriod");
Long ibuf = Long.parseLong(buf);
returnVal.setExpirationPeriod(ibuf);
}
return returnVal;

}
}
