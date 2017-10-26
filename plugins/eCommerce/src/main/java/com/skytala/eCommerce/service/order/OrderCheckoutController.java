package com.skytala.eCommerce.service.order;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.ServiceAuthException;
import org.apache.ofbiz.service.ServiceValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/OrderCheckoutController")
public class OrderCheckoutController{

	@RequestMapping(method = RequestMethod.POST, value = "/createUpdateBillingAddressAndPaymentMethod")
	public ResponseEntity<Object> createUpdateBillingAddressAndPaymentMethod(HttpSession session, @RequestParam(value="expMonth") String expMonth, @RequestParam(value="expYear") String expYear, @RequestParam(value="lastNameOnCard") String lastNameOnCard, @RequestParam(value="firstNameOnCard") String firstNameOnCard, @RequestParam(value="cardType") String cardType, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart, @RequestParam(value="cardNumber") String cardNumber, @RequestParam(value="billToCountryCode", required=false) String billToCountryCode, @RequestParam(value="billToAreaCode", required=false) String billToAreaCode, @RequestParam(value="billToPostalCode", required=false) String billToPostalCode, @RequestParam(value="setDefaultBilling", required=false) String setDefaultBilling, @RequestParam(value="billToAddress2", required=false) String billToAddress2, @RequestParam(value="contactMechId", required=false) String contactMechId, @RequestParam(value="billToCity", required=false) String billToCity, @RequestParam(value="keepAddressBook", required=false) String keepAddressBook, @RequestParam(value="titleOnCard", required=false) String titleOnCard, @RequestParam(value="paymentMethodId", required=false) String paymentMethodId, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="billToContactNumber", required=false) String billToContactNumber, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="suffixOnCard", required=false) String suffixOnCard, @RequestParam(value="billToStateProvinceGeoId", required=false) String billToStateProvinceGeoId, @RequestParam(value="billToAttnName", required=false) String billToAttnName, @RequestParam(value="shipToContactMechId", required=false) String shipToContactMechId, @RequestParam(value="billToName", required=false) String billToName, @RequestParam(value="billToCountryGeoId", required=false) String billToCountryGeoId, @RequestParam(value="companyNameOnCard", required=false) String companyNameOnCard, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="billToCardSecurityCode", required=false) String billToCardSecurityCode, @RequestParam(value="billToAddress1", required=false) String billToAddress1, @RequestParam(value="billToContactMechId", required=false) String billToContactMechId, @RequestParam(value="middleNameOnCard", required=false) String middleNameOnCard, @RequestParam(value="useShippingAddressForBilling", required=false) String useShippingAddressForBilling, @RequestParam(value="billToExtension", required=false) String billToExtension, @RequestParam(value="billToPhoneContactMechId", required=false) String billToPhoneContactMechId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("expMonth",expMonth);
		paramMap.put("expYear",expYear);
		paramMap.put("lastNameOnCard",lastNameOnCard);
		paramMap.put("firstNameOnCard",firstNameOnCard);
		paramMap.put("cardType",cardType);
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("cardNumber",cardNumber);
		paramMap.put("billToCountryCode",billToCountryCode);
		paramMap.put("billToAreaCode",billToAreaCode);
		paramMap.put("billToPostalCode",billToPostalCode);
		paramMap.put("setDefaultBilling",setDefaultBilling);
		paramMap.put("billToAddress2",billToAddress2);
		paramMap.put("contactMechId",contactMechId);
		paramMap.put("billToCity",billToCity);
		paramMap.put("keepAddressBook",keepAddressBook);
		paramMap.put("titleOnCard",titleOnCard);
		paramMap.put("paymentMethodId",paymentMethodId);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("billToContactNumber",billToContactNumber);
		paramMap.put("partyId",partyId);
		paramMap.put("suffixOnCard",suffixOnCard);
		paramMap.put("billToStateProvinceGeoId",billToStateProvinceGeoId);
		paramMap.put("billToAttnName",billToAttnName);
		paramMap.put("shipToContactMechId",shipToContactMechId);
		paramMap.put("billToName",billToName);
		paramMap.put("billToCountryGeoId",billToCountryGeoId);
		paramMap.put("companyNameOnCard",companyNameOnCard);
		paramMap.put("userLogin",userLogin);
		paramMap.put("billToCardSecurityCode",billToCardSecurityCode);
		paramMap.put("billToAddress1",billToAddress1);
		paramMap.put("billToContactMechId",billToContactMechId);
		paramMap.put("middleNameOnCard",middleNameOnCard);
		paramMap.put("useShippingAddressForBilling",useShippingAddressForBilling);
		paramMap.put("billToExtension",billToExtension);
		paramMap.put("billToPhoneContactMechId",billToPhoneContactMechId);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUpdateBillingAddressAndPaymentMethod", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUpdateCustomerAndShippingAddress")
	public ResponseEntity<Object> createUpdateCustomerAndShippingAddress(HttpSession session, @RequestParam(value="shipToCity") String shipToCity, @RequestParam(value="emailAddress") String emailAddress, @RequestParam(value="shipToAddress1") String shipToAddress1, @RequestParam(value="shipToPostalCode") String shipToPostalCode, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart, @RequestParam(value="shipToCountryGeoId") String shipToCountryGeoId, @RequestParam(value="shipToStateProvinceGeoId") String shipToStateProvinceGeoId, @RequestParam(value="lastName", required=false) String lastName, @RequestParam(value="setDefaultShipping", required=false) String setDefaultShipping, @RequestParam(value="shipToContactMechId", required=false) String shipToContactMechId, @RequestParam(value="shipToPhoneContactMechId", required=false) String shipToPhoneContactMechId, @RequestParam(value="userLogin", required=false) org.apache.ofbiz.entity.GenericValue userLogin, @RequestParam(value="emailContactMechId", required=false) String emailContactMechId, @RequestParam(value="keepAddressBook", required=false) String keepAddressBook, @RequestParam(value="firstName", required=false) String firstName, @RequestParam(value="shipToContactNumber", required=false) String shipToContactNumber, @RequestParam(value="shipToAddress2", required=false) String shipToAddress2, @RequestParam(value="billToContactMechId", required=false) String billToContactMechId, @RequestParam(value="shipToCountryCode", required=false) String shipToCountryCode, @RequestParam(value="shipToName", required=false) String shipToName, @RequestParam(value="shipToExtension", required=false) String shipToExtension, @RequestParam(value="productStoreId", required=false) String productStoreId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="shipToAreaCode", required=false) String shipToAreaCode, @RequestParam(value="shipToAttnName", required=false) String shipToAttnName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shipToCity",shipToCity);
		paramMap.put("emailAddress",emailAddress);
		paramMap.put("shipToAddress1",shipToAddress1);
		paramMap.put("shipToPostalCode",shipToPostalCode);
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("shipToCountryGeoId",shipToCountryGeoId);
		paramMap.put("shipToStateProvinceGeoId",shipToStateProvinceGeoId);
		paramMap.put("lastName",lastName);
		paramMap.put("setDefaultShipping",setDefaultShipping);
		paramMap.put("shipToContactMechId",shipToContactMechId);
		paramMap.put("shipToPhoneContactMechId",shipToPhoneContactMechId);
		paramMap.put("userLogin",userLogin);
		paramMap.put("emailContactMechId",emailContactMechId);
		paramMap.put("keepAddressBook",keepAddressBook);
		paramMap.put("firstName",firstName);
		paramMap.put("shipToContactNumber",shipToContactNumber);
		paramMap.put("shipToAddress2",shipToAddress2);
		paramMap.put("billToContactMechId",billToContactMechId);
		paramMap.put("shipToCountryCode",shipToCountryCode);
		paramMap.put("shipToName",shipToName);
		paramMap.put("shipToExtension",shipToExtension);
		paramMap.put("productStoreId",productStoreId);
		paramMap.put("partyId",partyId);
		paramMap.put("shipToAreaCode",shipToAreaCode);
		paramMap.put("shipToAttnName",shipToAttnName);

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUpdateCustomerAndShippingAddress", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setAnonUserLogin")
	public ResponseEntity<Object> setAnonUserLogin(HttpSession session, @RequestParam(value="shoppingCart") org.apache.ofbiz.order.shoppingcart.ShoppingCart shoppingCart, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("shoppingCart",shoppingCart);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setAnonUserLogin", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);

		} catch (ServiceValidationException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(e.getMessage());
		}
		if(result.get("responseMessage").equals("error")) {
			return ResponseEntity.badRequest().header("Session-ID", "JSESSIONID=" + session.getId()).body(null);
		}

		return ResponseEntity.ok().header("Session-ID", "JSESSIONID=" + session.getId()).body(result);
	}

	@RequestMapping(value = (" * "))
	public ResponseEntity<Object> returnErrorPage(HttpSession session) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Requested service does not exist. JSESSIONID=" + session.getId());
	}

}
