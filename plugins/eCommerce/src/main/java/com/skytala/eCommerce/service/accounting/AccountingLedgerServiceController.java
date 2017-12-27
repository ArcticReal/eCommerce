package com.skytala.eCommerce.service.accounting;

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

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;

@RestController
@RequestMapping("/service/accountingLedger")
public class AccountingLedgerServiceController{

	@RequestMapping(method = RequestMethod.POST, value = "/deleteAcctgTransAttribute")
	public ResponseEntity<Map<String, Object>> deleteAcctgTransAttribute(HttpSession session, @RequestParam(value="acctgTransId") String acctgTransId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteAcctgTransAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlAccountCategoryMember")
	public ResponseEntity<Map<String, Object>> updateGlAccountCategoryMember(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="glAccountCategoryId") String glAccountCategoryId, @RequestParam(value="amountPercentage", required=false) BigDecimal amountPercentage, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("glAccountCategoryId",glAccountCategoryId);
		paramMap.put("amountPercentage",amountPercentage);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlAccountCategoryMember", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteFinAccountTypeGlAccount")
	public ResponseEntity<Map<String, Object>> deleteFinAccountTypeGlAccount(HttpSession session, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="finAccountTypeId") String finAccountTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteFinAccountTypeGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/revertAcctgTransOnRemovePaymentApplications")
	public ResponseEntity<Map<String, Object>> revertAcctgTransOnRemovePaymentApplications(HttpSession session, @RequestParam(value="paymentApplicationId") String paymentApplicationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentApplicationId",paymentApplicationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("revertAcctgTransOnRemovePaymentApplications", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlAccountOrganization")
	public ResponseEntity<Map<String, Object>> deleteGlAccountOrganization(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlAccountOrganization", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransAndEntriesForCustomerRefundPaymentApplication")
	public ResponseEntity<Map<String, Object>> createAcctgTransAndEntriesForCustomerRefundPaymentApplication(HttpSession session, @RequestParam(value="paymentApplicationId") String paymentApplicationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentApplicationId",paymentApplicationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransAndEntriesForCustomerRefundPaymentApplication", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createUpdateCostCenter")
	public ResponseEntity<Map<String, Object>> createUpdateCostCenter(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="amountPercentageMap", required=false) Map amountPercentageMap) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("amountPercentageMap",amountPercentageMap);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createUpdateCostCenter", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForCanceledSalesShipmentIssuance")
	public ResponseEntity<Map<String, Object>> createAcctgTransForCanceledSalesShipmentIssuance(HttpSession session, @RequestParam(value="itemIssuanceId") String itemIssuanceId, @RequestParam(value="canceledQuantity") BigDecimal canceledQuantity) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("canceledQuantity",canceledQuantity);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForCanceledSalesShipmentIssuance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlAccountType")
	public ResponseEntity<Map<String, Object>> updateGlAccountType(HttpSession session, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlAccountType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAcctgTransEntryType")
	public ResponseEntity<Map<String, Object>> updateAcctgTransEntryType(HttpSession session, @RequestParam(value="acctgTransEntryTypeId") String acctgTransEntryTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransEntryTypeId",acctgTransEntryTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAcctgTransEntryType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlAccountCategoryType")
	public ResponseEntity<Map<String, Object>> deleteGlAccountCategoryType(HttpSession session, @RequestParam(value="glAccountCategoryTypeId") String glAccountCategoryTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountCategoryTypeId",glAccountCategoryTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlAccountCategoryType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForInventoryItemOwnerChange")
	public ResponseEntity<Map<String, Object>> createAcctgTransForInventoryItemOwnerChange(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="oldOwnerPartyId") String oldOwnerPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("oldOwnerPartyId",oldOwnerPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForInventoryItemOwnerChange", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteAcctgTransTypeAttr")
	public ResponseEntity<Map<String, Object>> deleteAcctgTransTypeAttr(HttpSession session, @RequestParam(value="acctgTransTypeId") String acctgTransTypeId, @RequestParam(value="attrName") String attrName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransTypeId",acctgTransTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteAcctgTransTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/interfaceAcctgTransEntry")
	public ResponseEntity<Map<String, Object>> interfaceAcctgTransEntry(HttpSession session, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="debitCreditFlag") String debitCreditFlag, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="theirProductId", required=false) String theirProductId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="dueDate", required=false) Timestamp dueDate, @RequestParam(value="groupId", required=false) String groupId, @RequestParam(value="settlementTermId", required=false) String settlementTermId, @RequestParam(value="description", required=false) String description, @RequestParam(value="theirPartyId", required=false) String theirPartyId, @RequestParam(value="acctgTransEntryTypeId", required=false) String acctgTransEntryTypeId, @RequestParam(value="isSummary", required=false) String isSummary, @RequestParam(value="voucherRef", required=false) String voucherRef, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="glAccountTypeId", required=false) String glAccountTypeId, @RequestParam(value="origAmount", required=false) BigDecimal origAmount, @RequestParam(value="taxId", required=false) String taxId, @RequestParam(value="origCurrencyUomId", required=false) String origCurrencyUomId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("amount",amount);
		paramMap.put("debitCreditFlag",debitCreditFlag);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("theirProductId",theirProductId);
		paramMap.put("productId",productId);
		paramMap.put("dueDate",dueDate);
		paramMap.put("groupId",groupId);
		paramMap.put("settlementTermId",settlementTermId);
		paramMap.put("description",description);
		paramMap.put("theirPartyId",theirPartyId);
		paramMap.put("acctgTransEntryTypeId",acctgTransEntryTypeId);
		paramMap.put("isSummary",isSummary);
		paramMap.put("voucherRef",voucherRef);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("origAmount",origAmount);
		paramMap.put("taxId",taxId);
		paramMap.put("origCurrencyUomId",origCurrencyUomId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("interfaceAcctgTransEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteAcctgTransEntry")
	public ResponseEntity<Map<String, Object>> deleteAcctgTransEntry(HttpSession session, @RequestParam(value="acctgTransEntrySeqId") String acctgTransEntrySeqId, @RequestParam(value="acctgTransId") String acctgTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransEntrySeqId",acctgTransEntrySeqId);
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteAcctgTransEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlAccountCategory")
	public ResponseEntity<Map<String, Object>> updateGlAccountCategory(HttpSession session, @RequestParam(value="glAccountCategoryId") String glAccountCategoryId, @RequestParam(value="glAccountCategoryTypeId", required=false) String glAccountCategoryTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountCategoryId",glAccountCategoryId);
		paramMap.put("glAccountCategoryTypeId",glAccountCategoryTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlAccountCategory", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateProductAverageCostType")
	public ResponseEntity<Map<String, Object>> updateProductAverageCostType(HttpSession session, @RequestParam(value="productAverageCostTypeId") String productAverageCostTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productAverageCostTypeId",productAverageCostTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateProductAverageCostType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransEntry")
	public ResponseEntity<Map<String, Object>> createAcctgTransEntry(HttpSession session, @RequestParam(value="acctgTransId") String acctgTransId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="theirProductId", required=false) String theirProductId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="dueDate", required=false) Timestamp dueDate, @RequestParam(value="groupId", required=false) String groupId, @RequestParam(value="settlementTermId", required=false) String settlementTermId, @RequestParam(value="glAccountId", required=false) String glAccountId, @RequestParam(value="description", required=false) String description, @RequestParam(value="theirPartyId", required=false) String theirPartyId, @RequestParam(value="acctgTransEntryTypeId", required=false) String acctgTransEntryTypeId, @RequestParam(value="isSummary", required=false) String isSummary, @RequestParam(value="voucherRef", required=false) String voucherRef, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="purposeEnumId", required=false) String purposeEnumId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="glAccountTypeId", required=false) String glAccountTypeId, @RequestParam(value="origAmount", required=false) BigDecimal origAmount, @RequestParam(value="debitCreditFlag", required=false) String debitCreditFlag, @RequestParam(value="taxId", required=false) String taxId, @RequestParam(value="origCurrencyUomId", required=false) String origCurrencyUomId, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("theirProductId",theirProductId);
		paramMap.put("amount",amount);
		paramMap.put("productId",productId);
		paramMap.put("dueDate",dueDate);
		paramMap.put("groupId",groupId);
		paramMap.put("settlementTermId",settlementTermId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("description",description);
		paramMap.put("theirPartyId",theirPartyId);
		paramMap.put("acctgTransEntryTypeId",acctgTransEntryTypeId);
		paramMap.put("isSummary",isSummary);
		paramMap.put("voucherRef",voucherRef);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("purposeEnumId",purposeEnumId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("origAmount",origAmount);
		paramMap.put("debitCreditFlag",debitCreditFlag);
		paramMap.put("taxId",taxId);
		paramMap.put("origCurrencyUomId",origCurrencyUomId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/computeGlAccountBalanceForTimePeriod")
	public ResponseEntity<Map<String, Object>> computeGlAccountBalanceForTimePeriod(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="customTimePeriodId") String customTimePeriodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("customTimePeriodId",customTimePeriodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("computeGlAccountBalanceForTimePeriod", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForFixedAssetMaintIssuance")
	public ResponseEntity<Map<String, Object>> createAcctgTransForFixedAssetMaintIssuance(HttpSession session, @RequestParam(value="itemIssuanceId") String itemIssuanceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForFixedAssetMaintIssuance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAccountCategoryType")
	public ResponseEntity<Map<String, Object>> createGlAccountCategoryType(HttpSession session, @RequestParam(value="glAccountCategoryTypeId", required=false) String glAccountCategoryTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountCategoryTypeId",glAccountCategoryTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAccountCategoryType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createFinAccountTypeGlAccount")
	public ResponseEntity<Map<String, Object>> createFinAccountTypeGlAccount(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="finAccountTypeId") String finAccountTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createFinAccountTypeGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlAccountGroupMember")
	public ResponseEntity<Map<String, Object>> updateGlAccountGroupMember(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="glAccountGroupTypeId") String glAccountGroupTypeId, @RequestParam(value="glAccountGroupId", required=false) String glAccountGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("glAccountGroupTypeId",glAccountGroupTypeId);
		paramMap.put("glAccountGroupId",glAccountGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlAccountGroupMember", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAccountType")
	public ResponseEntity<Map<String, Object>> createGlAccountType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="glAccountTypeId", required=false) String glAccountTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAccountType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/postGlJournal")
	public ResponseEntity<Map<String, Object>> postGlJournal(HttpSession session, @RequestParam(value="glJournalId") String glJournalId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glJournalId",glJournalId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("postGlJournal", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlJournal")
	public ResponseEntity<Map<String, Object>> createGlJournal(HttpSession session, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="glJournalName", required=false) String glJournalName) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("glJournalName",glJournalName);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlJournal", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlResourceType")
	public ResponseEntity<Map<String, Object>> updateGlResourceType(HttpSession session, @RequestParam(value="glResourceTypeId") String glResourceTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glResourceTypeId",glResourceTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlResourceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateVarianceReasonGlAccount")
	public ResponseEntity<Map<String, Object>> updateVarianceReasonGlAccount(HttpSession session, @RequestParam(value="varianceReasonId") String varianceReasonId, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("varianceReasonId",varianceReasonId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateVarianceReasonGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransEntryType")
	public ResponseEntity<Map<String, Object>> createAcctgTransEntryType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description, @RequestParam(value="acctgTransEntryTypeId", required=false) String acctgTransEntryTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("acctgTransEntryTypeId",acctgTransEntryTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransEntryType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createPartyGlAccount")
	public ResponseEntity<Map<String, Object>> createPartyGlAccount(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createPartyGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlFiscalType")
	public ResponseEntity<Map<String, Object>> deleteGlFiscalType(HttpSession session, @RequestParam(value="glFiscalTypeId") String glFiscalTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glFiscalTypeId",glFiscalTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlFiscalType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForShipmentReceipt")
	public ResponseEntity<Map<String, Object>> createAcctgTransForShipmentReceipt(HttpSession session, @RequestParam(value="receiptId") String receiptId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("receiptId",receiptId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForShipmentReceipt", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlResourceType")
	public ResponseEntity<Map<String, Object>> deleteGlResourceType(HttpSession session, @RequestParam(value="glResourceTypeId") String glResourceTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glResourceTypeId",glResourceTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlResourceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlAccount")
	public ResponseEntity<Map<String, Object>> deleteGlAccount(HttpSession session, @RequestParam(value="glAccountId") String glAccountId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/prepareIncomeStatement")
	public ResponseEntity<Map<String, Object>> prepareIncomeStatement(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="glFiscalTypeId") String glFiscalTypeId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="thruDate") Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("glFiscalTypeId",glFiscalTypeId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("prepareIncomeStatement", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlJournal")
	public ResponseEntity<Map<String, Object>> updateGlJournal(HttpSession session, @RequestParam(value="glJournalId") String glJournalId, @RequestParam(value="glJournalName", required=false) String glJournalName, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glJournalId",glJournalId);
		paramMap.put("glJournalName",glJournalName);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlJournal", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlXbrlClass")
	public ResponseEntity<Map<String, Object>> updateGlXbrlClass(HttpSession session, @RequestParam(value="glXbrlClassId") String glXbrlClassId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glXbrlClassId",glXbrlClassId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlXbrlClass", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForWorkEffortInventoryProduced")
	public ResponseEntity<Map<String, Object>> createAcctgTransForWorkEffortInventoryProduced(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="inventoryItemId") String inventoryItemId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForWorkEffortInventoryProduced", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlReconciliationEntry")
	public ResponseEntity<Map<String, Object>> updateGlReconciliationEntry(HttpSession session, @RequestParam(value="glReconciliationId") String glReconciliationId, @RequestParam(value="reconciledAmount") BigDecimal reconciledAmount, @RequestParam(value="acctgTransEntrySeqId") String acctgTransEntrySeqId, @RequestParam(value="acctgTransId") String acctgTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("reconciledAmount",reconciledAmount);
		paramMap.put("acctgTransEntrySeqId",acctgTransEntrySeqId);
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlReconciliationEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAcctgTrans")
	public ResponseEntity<Map<String, Object>> updateAcctgTrans(HttpSession session, @RequestParam(value="acctgTransId") String acctgTransId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="theirAcctgTransId", required=false) String theirAcctgTransId, @RequestParam(value="description", required=false) String description, @RequestParam(value="postedDate", required=false) Timestamp postedDate, @RequestParam(value="physicalInventoryId", required=false) String physicalInventoryId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="isPosted", required=false) String isPosted, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="voucherDate", required=false) Timestamp voucherDate, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="receiptId", required=false) String receiptId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="acctgTransTypeId", required=false) String acctgTransTypeId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="glFiscalTypeId", required=false) String glFiscalTypeId, @RequestParam(value="glJournalId", required=false) String glJournalId, @RequestParam(value="transactionDate", required=false) Timestamp transactionDate, @RequestParam(value="scheduledPostingDate", required=false) Timestamp scheduledPostingDate, @RequestParam(value="voucherRef", required=false) String voucherRef, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="groupStatusId", required=false) String groupStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("theirAcctgTransId",theirAcctgTransId);
		paramMap.put("description",description);
		paramMap.put("postedDate",postedDate);
		paramMap.put("physicalInventoryId",physicalInventoryId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("isPosted",isPosted);
		paramMap.put("paymentId",paymentId);
		paramMap.put("voucherDate",voucherDate);
		paramMap.put("partyId",partyId);
		paramMap.put("receiptId",receiptId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("acctgTransTypeId",acctgTransTypeId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("glFiscalTypeId",glFiscalTypeId);
		paramMap.put("glJournalId",glJournalId);
		paramMap.put("transactionDate",transactionDate);
		paramMap.put("scheduledPostingDate",scheduledPostingDate);
		paramMap.put("voucherRef",voucherRef);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("groupStatusId",groupStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAcctgTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getInventoryValuationList")
	public ResponseEntity<Map<String, Object>> getInventoryValuationList(HttpSession session, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="cogsMethodId", required=false) String cogsMethodId, @RequestParam(value="facilityId", required=false) String facilityId, @RequestParam(value="productCategoryId", required=false) String productCategoryId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("cogsMethodId",cogsMethodId);
		paramMap.put("facilityId",facilityId);
		paramMap.put("productCategoryId",productCategoryId);
		paramMap.put("productId",productId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getInventoryValuationList", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/closeFinancialTimePeriod")
	public ResponseEntity<Map<String, Object>> closeFinancialTimePeriod(HttpSession session, @RequestParam(value="customTimePeriodId") String customTimePeriodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customTimePeriodId",customTimePeriodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("closeFinancialTimePeriod", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createVarianceReasonGlAccount")
	public ResponseEntity<Map<String, Object>> createVarianceReasonGlAccount(HttpSession session, @RequestParam(value="varianceReasonId") String varianceReasonId, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("varianceReasonId",varianceReasonId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createVarianceReasonGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/calculateGlAccountTrialBalance")
	public ResponseEntity<Map<String, Object>> calculateGlAccountTrialBalance(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="thruDate") Timestamp thruDate, @RequestParam(value="isPosted", required=false) String isPosted) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("isPosted",isPosted);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calculateGlAccountTrialBalance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlFiscalType")
	public ResponseEntity<Map<String, Object>> updateGlFiscalType(HttpSession session, @RequestParam(value="glFiscalTypeId") String glFiscalTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glFiscalTypeId",glFiscalTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlFiscalType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/findCustomTimePeriods")
	public ResponseEntity<Map<String, Object>> findCustomTimePeriods(HttpSession session, @RequestParam(value="findDate") Timestamp findDate, @RequestParam(value="onlyIncludePeriodTypeIdList", required=false) List onlyIncludePeriodTypeIdList, @RequestParam(value="excludeNoOrganizationPeriods", required=false) String excludeNoOrganizationPeriods, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("findDate",findDate);
		paramMap.put("onlyIncludePeriodTypeIdList",onlyIncludePeriodTypeIdList);
		paramMap.put("excludeNoOrganizationPeriods",excludeNoOrganizationPeriods);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findCustomTimePeriods", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getPreviousTimePeriod")
	public ResponseEntity<Map<String, Object>> getPreviousTimePeriod(HttpSession session, @RequestParam(value="customTimePeriodId") String customTimePeriodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customTimePeriodId",customTimePeriodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getPreviousTimePeriod", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlAccountGroupType")
	public ResponseEntity<Map<String, Object>> deleteGlAccountGroupType(HttpSession session, @RequestParam(value="glAccountGroupTypeId") String glAccountGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountGroupTypeId",glAccountGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlAccountGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForSalesInvoice")
	public ResponseEntity<Map<String, Object>> createAcctgTransForSalesInvoice(HttpSession session, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForSalesInvoice", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAcctgTransType")
	public ResponseEntity<Map<String, Object>> updateAcctgTransType(HttpSession session, @RequestParam(value="acctgTransTypeId") String acctgTransTypeId, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransTypeId",acctgTransTypeId);
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAcctgTransType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlFiscalType")
	public ResponseEntity<Map<String, Object>> createGlFiscalType(HttpSession session, @RequestParam(value="glFiscalTypeId", required=false) String glFiscalTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glFiscalTypeId",glFiscalTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlFiscalType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlAccountClass")
	public ResponseEntity<Map<String, Object>> updateGlAccountClass(HttpSession session, @RequestParam(value="glAccountClassId") String glAccountClassId, @RequestParam(value="parentClassId", required=false) String parentClassId, @RequestParam(value="description", required=false) String description, @RequestParam(value="isAssetClass", required=false) String isAssetClass) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountClassId",glAccountClassId);
		paramMap.put("parentClassId",parentClassId);
		paramMap.put("description",description);
		paramMap.put("isAssetClass",isAssetClass);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlAccountClass", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlXbrlClass")
	public ResponseEntity<Map<String, Object>> deleteGlXbrlClass(HttpSession session, @RequestParam(value="glXbrlClassId") String glXbrlClassId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glXbrlClassId",glXbrlClassId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlXbrlClass", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransAttribute")
	public ResponseEntity<Map<String, Object>> createAcctgTransAttribute(HttpSession session, @RequestParam(value="acctgTransId") String acctgTransId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/expireGlBudgetXref")
	public ResponseEntity<Map<String, Object>> expireGlBudgetXref(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="budgetItemTypeId") String budgetItemTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("expireGlBudgetXref", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlAccountType")
	public ResponseEntity<Map<String, Object>> deleteGlAccountType(HttpSession session, @RequestParam(value="glAccountTypeId") String glAccountTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlAccountType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTrans")
	public ResponseEntity<Map<String, Object>> createAcctgTrans(HttpSession session, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="theirAcctgTransId", required=false) String theirAcctgTransId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="acctgTransTypeId", required=false) String acctgTransTypeId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="glFiscalTypeId", required=false) String glFiscalTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="glJournalId", required=false) String glJournalId, @RequestParam(value="transactionDate", required=false) Timestamp transactionDate, @RequestParam(value="scheduledPostingDate", required=false) Timestamp scheduledPostingDate, @RequestParam(value="voucherRef", required=false) String voucherRef, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="physicalInventoryId", required=false) String physicalInventoryId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="voucherDate", required=false) Timestamp voucherDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="receiptId", required=false) String receiptId, @RequestParam(value="groupStatusId", required=false) String groupStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("theirAcctgTransId",theirAcctgTransId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("acctgTransTypeId",acctgTransTypeId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("glFiscalTypeId",glFiscalTypeId);
		paramMap.put("description",description);
		paramMap.put("glJournalId",glJournalId);
		paramMap.put("transactionDate",transactionDate);
		paramMap.put("scheduledPostingDate",scheduledPostingDate);
		paramMap.put("voucherRef",voucherRef);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("physicalInventoryId",physicalInventoryId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("paymentId",paymentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("voucherDate",voucherDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("partyId",partyId);
		paramMap.put("receiptId",receiptId);
		paramMap.put("groupStatusId",groupStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAccountOrganization")
	public ResponseEntity<Map<String, Object>> createGlAccountOrganization(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAccountOrganization", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAccountCategoryMember")
	public ResponseEntity<Map<String, Object>> createGlAccountCategoryMember(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="glAccountCategoryId") String glAccountCategoryId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="amountPercentage", required=false) BigDecimal amountPercentage, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("glAccountCategoryId",glAccountCategoryId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("amountPercentage",amountPercentage);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAccountCategoryMember", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/calculateAcctgTransTrialBalance")
	public ResponseEntity<Map<String, Object>> calculateAcctgTransTrialBalance(HttpSession session, @RequestParam(value="acctgTransId") String acctgTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calculateAcctgTransTrialBalance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateSettlementTerm")
	public ResponseEntity<Map<String, Object>> updateSettlementTerm(HttpSession session, @RequestParam(value="settlementTermId") String settlementTermId, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="termName", required=false) String termName, @RequestParam(value="termValue", required=false) Long termValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("settlementTermId",settlementTermId);
		paramMap.put("uomId",uomId);
		paramMap.put("termName",termName);
		paramMap.put("termValue",termValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateSettlementTerm", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlReconciliation")
	public ResponseEntity<Map<String, Object>> createGlReconciliation(HttpSession session, @RequestParam(value="glReconciliationName") String glReconciliationName, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="reconciledBalance", required=false) BigDecimal reconciledBalance, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="glAccountId", required=false) String glAccountId, @RequestParam(value="description", required=false) String description, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="reconciledDate", required=false) Timestamp reconciledDate, @RequestParam(value="openingBalance", required=false) BigDecimal openingBalance) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glReconciliationName",glReconciliationName);
		paramMap.put("createdDate",createdDate);
		paramMap.put("reconciledBalance",reconciledBalance);
		paramMap.put("statusId",statusId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("description",description);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("reconciledDate",reconciledDate);
		paramMap.put("openingBalance",openingBalance);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlReconciliation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAcctCatMemFromCostCenters")
	public ResponseEntity<Map<String, Object>> createGlAcctCatMemFromCostCenters(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="glAccountCategoryId") String glAccountCategoryId, @RequestParam(value="amountPercentage", required=false) BigDecimal amountPercentage, @RequestParam(value="totalAmountPercentage", required=false) BigDecimal totalAmountPercentage) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("glAccountCategoryId",glAccountCategoryId);
		paramMap.put("amountPercentage",amountPercentage);
		paramMap.put("totalAmountPercentage",totalAmountPercentage);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAcctCatMemFromCostCenters", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getInventoryItemOwner")
	public ResponseEntity<Map<String, Object>> getInventoryItemOwner(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getInventoryItemOwner", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/postAcctgTrans")
	public ResponseEntity<Map<String, Object>> postAcctgTrans(HttpSession session, @RequestParam(value="acctgTransId") String acctgTransId, @RequestParam(value="verifyOnly", required=false) String verifyOnly) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("verifyOnly",verifyOnly);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("postAcctgTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlAccountGroupMember")
	public ResponseEntity<Map<String, Object>> deleteGlAccountGroupMember(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="glAccountGroupTypeId") String glAccountGroupTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("glAccountGroupTypeId",glAccountGroupTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlAccountGroupMember", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForPhysicalInventoryVariance")
	public ResponseEntity<Map<String, Object>> createAcctgTransForPhysicalInventoryVariance(HttpSession session, @RequestParam(value="physicalInventoryId") String physicalInventoryId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("physicalInventoryId",physicalInventoryId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForPhysicalInventoryVariance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForPurchaseInvoice")
	public ResponseEntity<Map<String, Object>> createAcctgTransForPurchaseInvoice(HttpSession session, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForPurchaseInvoice", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/expireGlAccountRole")
	public ResponseEntity<Map<String, Object>> expireGlAccountRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("expireGlAccountRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/calculateGlJournalTrialBalance")
	public ResponseEntity<Map<String, Object>> calculateGlJournalTrialBalance(HttpSession session, @RequestParam(value="glJournalId") String glJournalId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glJournalId",glJournalId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("calculateGlJournalTrialBalance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAcctgTransAttribute")
	public ResponseEntity<Map<String, Object>> updateAcctgTransAttribute(HttpSession session, @RequestParam(value="acctgTransId") String acctgTransId, @RequestParam(value="attrName") String attrName, @RequestParam(value="attrDescription", required=false) String attrDescription, @RequestParam(value="attrValue", required=false) Long attrValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("attrName",attrName);
		paramMap.put("attrDescription",attrDescription);
		paramMap.put("attrValue",attrValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAcctgTransAttribute", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransAndEntriesForPaymentApplication")
	public ResponseEntity<Map<String, Object>> createAcctgTransAndEntriesForPaymentApplication(HttpSession session, @RequestParam(value="paymentApplicationId") String paymentApplicationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentApplicationId",paymentApplicationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransAndEntriesForPaymentApplication", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlBudgetXref")
	public ResponseEntity<Map<String, Object>> createGlBudgetXref(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="budgetItemTypeId") String budgetItemTypeId, @RequestParam(value="allocationPercentage", required=false) BigDecimal allocationPercentage, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("allocationPercentage",allocationPercentage);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlBudgetXref", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransAndEntries")
	public ResponseEntity<Map<String, Object>> createAcctgTransAndEntries(HttpSession session, @RequestParam(value="acctgTransEntries") java.util.List acctgTransEntries, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="theirAcctgTransId", required=false) String theirAcctgTransId, @RequestParam(value="description", required=false) String description, @RequestParam(value="postedDate", required=false) Timestamp postedDate, @RequestParam(value="physicalInventoryId", required=false) String physicalInventoryId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="isPosted", required=false) String isPosted, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="voucherDate", required=false) Timestamp voucherDate, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="receiptId", required=false) String receiptId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="acctgTransTypeId", required=false) String acctgTransTypeId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="glFiscalTypeId", required=false) String glFiscalTypeId, @RequestParam(value="glJournalId", required=false) String glJournalId, @RequestParam(value="transactionDate", required=false) Timestamp transactionDate, @RequestParam(value="scheduledPostingDate", required=false) Timestamp scheduledPostingDate, @RequestParam(value="voucherRef", required=false) String voucherRef, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="groupStatusId", required=false) String groupStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransEntries",acctgTransEntries);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("theirAcctgTransId",theirAcctgTransId);
		paramMap.put("description",description);
		paramMap.put("postedDate",postedDate);
		paramMap.put("physicalInventoryId",physicalInventoryId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("isPosted",isPosted);
		paramMap.put("paymentId",paymentId);
		paramMap.put("voucherDate",voucherDate);
		paramMap.put("partyId",partyId);
		paramMap.put("receiptId",receiptId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("acctgTransTypeId",acctgTransTypeId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("glFiscalTypeId",glFiscalTypeId);
		paramMap.put("glJournalId",glJournalId);
		paramMap.put("transactionDate",transactionDate);
		paramMap.put("scheduledPostingDate",scheduledPostingDate);
		paramMap.put("voucherRef",voucherRef);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("groupStatusId",groupStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransAndEntries", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/computeAndStoreGlAccountHistoryBalance")
	public ResponseEntity<Map<String, Object>> computeAndStoreGlAccountHistoryBalance(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="customTimePeriodId") String customTimePeriodId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("customTimePeriodId",customTimePeriodId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("computeAndStoreGlAccountHistoryBalance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlReconciliationEntry")
	public ResponseEntity<Map<String, Object>> createGlReconciliationEntry(HttpSession session, @RequestParam(value="glReconciliationId") String glReconciliationId, @RequestParam(value="reconciledAmount") BigDecimal reconciledAmount, @RequestParam(value="acctgTransEntrySeqId") String acctgTransEntrySeqId, @RequestParam(value="acctgTransId") String acctgTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("reconciledAmount",reconciledAmount);
		paramMap.put("acctgTransEntrySeqId",acctgTransEntrySeqId);
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlReconciliationEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransTypeAttr")
	public ResponseEntity<Map<String, Object>> createAcctgTransTypeAttr(HttpSession session, @RequestParam(value="acctgTransTypeId") String acctgTransTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransTypeId",acctgTransTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/copyAcctgTransAndEntries")
	public ResponseEntity<Map<String, Object>> copyAcctgTransAndEntries(HttpSession session, @RequestParam(value="fromAcctgTransId") String fromAcctgTransId, @RequestParam(value="revert", required=false) String revert) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromAcctgTransId",fromAcctgTransId);
		paramMap.put("revert",revert);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("copyAcctgTransAndEntries", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteAcctgTransEntryType")
	public ResponseEntity<Map<String, Object>> deleteAcctgTransEntryType(HttpSession session, @RequestParam(value="acctgTransEntryTypeId") String acctgTransEntryTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransEntryTypeId",acctgTransEntryTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteAcctgTransEntryType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlBudgetXref")
	public ResponseEntity<Map<String, Object>> updateGlBudgetXref(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="budgetItemTypeId") String budgetItemTypeId, @RequestParam(value="allocationPercentage", required=false) BigDecimal allocationPercentage, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("budgetItemTypeId",budgetItemTypeId);
		paramMap.put("allocationPercentage",allocationPercentage);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlBudgetXref", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getAcctgTransEntriesAndTransTotal")
	public ResponseEntity<Map<String, Object>> getAcctgTransEntriesAndTransTotal(HttpSession session, @RequestParam(value="customTimePeriodEndDate") Timestamp customTimePeriodEndDate, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="customTimePeriodStartDate") Timestamp customTimePeriodStartDate, @RequestParam(value="isPosted", required=false) String isPosted) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("customTimePeriodEndDate",customTimePeriodEndDate);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("customTimePeriodStartDate",customTimePeriodStartDate);
		paramMap.put("isPosted",isPosted);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getAcctgTransEntriesAndTransTotal", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteSettlementTerm")
	public ResponseEntity<Map<String, Object>> deleteSettlementTerm(HttpSession session, @RequestParam(value="settlementTermId") String settlementTermId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("settlementTermId",settlementTermId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteSettlementTerm", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlReconciliationEntry")
	public ResponseEntity<Map<String, Object>> deleteGlReconciliationEntry(HttpSession session, @RequestParam(value="glReconciliationId") String glReconciliationId, @RequestParam(value="acctgTransEntrySeqId") String acctgTransEntrySeqId, @RequestParam(value="acctgTransId") String acctgTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("acctgTransEntrySeqId",acctgTransEntrySeqId);
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlReconciliationEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updatePartyGlAccount")
	public ResponseEntity<Map<String, Object>> updatePartyGlAccount(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updatePartyGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAccountGroupType")
	public ResponseEntity<Map<String, Object>> createGlAccountGroupType(HttpSession session, @RequestParam(value="glAccountGroupTypeId", required=false) String glAccountGroupTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountGroupTypeId",glAccountGroupTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAccountGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlAccountGroup")
	public ResponseEntity<Map<String, Object>> deleteGlAccountGroup(HttpSession session, @RequestParam(value="glAccountGroupId") String glAccountGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountGroupId",glAccountGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlAccountGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAccountGroup")
	public ResponseEntity<Map<String, Object>> createGlAccountGroup(HttpSession session, @RequestParam(value="glAccountGroupTypeId", required=false) String glAccountGroupTypeId, @RequestParam(value="description", required=false) String description, @RequestParam(value="glAccountGroupId", required=false) String glAccountGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountGroupTypeId",glAccountGroupTypeId);
		paramMap.put("description",description);
		paramMap.put("glAccountGroupId",glAccountGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAccountGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransAndEntriesForIncomingPayment")
	public ResponseEntity<Map<String, Object>> createAcctgTransAndEntriesForIncomingPayment(HttpSession session, @RequestParam(value="paymentId") String paymentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransAndEntriesForIncomingPayment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForCustomerReturnInvoice")
	public ResponseEntity<Map<String, Object>> createAcctgTransForCustomerReturnInvoice(HttpSession session, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForCustomerReturnInvoice", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteVarianceReasonGlAccount")
	public ResponseEntity<Map<String, Object>> deleteVarianceReasonGlAccount(HttpSession session, @RequestParam(value="varianceReasonId") String varianceReasonId, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("varianceReasonId",varianceReasonId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteVarianceReasonGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeAcctgTransType")
	public ResponseEntity<Map<String, Object>> removeAcctgTransType(HttpSession session, @RequestParam(value="acctgTransTypeId") String acctgTransTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransTypeId",acctgTransTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("removeAcctgTransType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForWorkEffortCost")
	public ResponseEntity<Map<String, Object>> createAcctgTransForWorkEffortCost(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="costComponentId") String costComponentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("costComponentId",costComponentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForWorkEffortCost", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/interfaceAcctgTrans")
	public ResponseEntity<Map<String, Object>> interfaceAcctgTrans(HttpSession session, @RequestParam(value="acctgTransTypeId") String acctgTransTypeId, @RequestParam(value="glFiscalTypeId") String glFiscalTypeId, @RequestParam(value="transactionDate") Timestamp transactionDate, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="theirAcctgTransId", required=false) String theirAcctgTransId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="description", required=false) String description, @RequestParam(value="glJournalId", required=false) String glJournalId, @RequestParam(value="scheduledPostingDate", required=false) Timestamp scheduledPostingDate, @RequestParam(value="voucherRef", required=false) String voucherRef, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="physicalInventoryId", required=false) String physicalInventoryId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="voucherDate", required=false) Timestamp voucherDate, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="receiptId", required=false) String receiptId, @RequestParam(value="groupStatusId", required=false) String groupStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransTypeId",acctgTransTypeId);
		paramMap.put("glFiscalTypeId",glFiscalTypeId);
		paramMap.put("transactionDate",transactionDate);
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("theirAcctgTransId",theirAcctgTransId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("description",description);
		paramMap.put("glJournalId",glJournalId);
		paramMap.put("scheduledPostingDate",scheduledPostingDate);
		paramMap.put("voucherRef",voucherRef);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("physicalInventoryId",physicalInventoryId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("paymentId",paymentId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("voucherDate",voucherDate);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("partyId",partyId);
		paramMap.put("receiptId",receiptId);
		paramMap.put("groupStatusId",groupStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("interfaceAcctgTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransAndEntriesForOutgoingPayment")
	public ResponseEntity<Map<String, Object>> createAcctgTransAndEntriesForOutgoingPayment(HttpSession session, @RequestParam(value="paymentId") String paymentId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("paymentId",paymentId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransAndEntriesForOutgoingPayment", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/findLastClosedDate")
	public ResponseEntity<Map<String, Object>> findLastClosedDate(HttpSession session, @RequestParam(value="periodTypeId", required=false) String periodTypeId, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="findDate", required=false) Timestamp findDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("periodTypeId",periodTypeId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("findDate",findDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("findLastClosedDate", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlAccountOrganization")
	public ResponseEntity<Map<String, Object>> updateGlAccountOrganization(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="fromDate", required=false) Timestamp fromDate, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlAccountOrganization", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteProductAverageCostType")
	public ResponseEntity<Map<String, Object>> deleteProductAverageCostType(HttpSession session, @RequestParam(value="productAverageCostTypeId") String productAverageCostTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("productAverageCostTypeId",productAverageCostTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteProductAverageCostType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAcctgTransEntry")
	public ResponseEntity<Map<String, Object>> updateAcctgTransEntry(HttpSession session, @RequestParam(value="acctgTransEntrySeqId") String acctgTransEntrySeqId, @RequestParam(value="acctgTransId") String acctgTransId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="theirProductId", required=false) String theirProductId, @RequestParam(value="amount", required=false) BigDecimal amount, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="dueDate", required=false) Timestamp dueDate, @RequestParam(value="groupId", required=false) String groupId, @RequestParam(value="settlementTermId", required=false) String settlementTermId, @RequestParam(value="glAccountId", required=false) String glAccountId, @RequestParam(value="description", required=false) String description, @RequestParam(value="theirPartyId", required=false) String theirPartyId, @RequestParam(value="reconcileStatusId", required=false) String reconcileStatusId, @RequestParam(value="acctgTransEntryTypeId", required=false) String acctgTransEntryTypeId, @RequestParam(value="isSummary", required=false) String isSummary, @RequestParam(value="voucherRef", required=false) String voucherRef, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="glAccountTypeId", required=false) String glAccountTypeId, @RequestParam(value="origAmount", required=false) BigDecimal origAmount, @RequestParam(value="debitCreditFlag", required=false) String debitCreditFlag, @RequestParam(value="taxId", required=false) String taxId, @RequestParam(value="origCurrencyUomId", required=false) String origCurrencyUomId, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransEntrySeqId",acctgTransEntrySeqId);
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("theirProductId",theirProductId);
		paramMap.put("amount",amount);
		paramMap.put("productId",productId);
		paramMap.put("dueDate",dueDate);
		paramMap.put("groupId",groupId);
		paramMap.put("settlementTermId",settlementTermId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("description",description);
		paramMap.put("theirPartyId",theirPartyId);
		paramMap.put("reconcileStatusId",reconcileStatusId);
		paramMap.put("acctgTransEntryTypeId",acctgTransEntryTypeId);
		paramMap.put("isSummary",isSummary);
		paramMap.put("voucherRef",voucherRef);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("origAmount",origAmount);
		paramMap.put("debitCreditFlag",debitCreditFlag);
		paramMap.put("taxId",taxId);
		paramMap.put("origCurrencyUomId",origCurrencyUomId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAcctgTransEntry", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlResourceType")
	public ResponseEntity<Map<String, Object>> createGlResourceType(HttpSession session, @RequestParam(value="glResourceTypeId", required=false) String glResourceTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glResourceTypeId",glResourceTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlResourceType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlXbrlClass")
	public ResponseEntity<Map<String, Object>> createGlXbrlClass(HttpSession session, @RequestParam(value="description", required=false) String description, @RequestParam(value="glXbrlClassId", required=false) String glXbrlClassId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("description",description);
		paramMap.put("glXbrlClassId",glXbrlClassId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlXbrlClass", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForWorkEffortIssuance")
	public ResponseEntity<Map<String, Object>> createAcctgTransForWorkEffortIssuance(HttpSession session, @RequestParam(value="workEffortId") String workEffortId, @RequestParam(value="inventoryItemId") String inventoryItemId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForWorkEffortIssuance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransType")
	public ResponseEntity<Map<String, Object>> createAcctgTransType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="acctgTransTypeId", required=false) String acctgTransTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("acctgTransTypeId",acctgTransTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createProductAverageCostType")
	public ResponseEntity<Map<String, Object>> createProductAverageCostType(HttpSession session, @RequestParam(value="parentTypeId", required=false) String parentTypeId, @RequestParam(value="hasTable", required=false) String hasTable, @RequestParam(value="productAverageCostTypeId", required=false) String productAverageCostTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentTypeId",parentTypeId);
		paramMap.put("hasTable",hasTable);
		paramMap.put("productAverageCostTypeId",productAverageCostTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createProductAverageCostType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/revertAcctgTransOnCancelInvoice")
	public ResponseEntity<Map<String, Object>> revertAcctgTransOnCancelInvoice(HttpSession session, @RequestParam(value="invoiceId") String invoiceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("revertAcctgTransOnCancelInvoice", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlAccountCategoryMember")
	public ResponseEntity<Map<String, Object>> deleteGlAccountCategoryMember(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="glAccountCategoryId") String glAccountCategoryId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("glAccountCategoryId",glAccountCategoryId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlAccountCategoryMember", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlAccountGroupType")
	public ResponseEntity<Map<String, Object>> updateGlAccountGroupType(HttpSession session, @RequestParam(value="glAccountGroupTypeId") String glAccountGroupTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountGroupTypeId",glAccountGroupTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlAccountGroupType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlAccount")
	public ResponseEntity<Map<String, Object>> updateGlAccount(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="accountCode", required=false) String accountCode, @RequestParam(value="glAccountTypeId", required=false) String glAccountTypeId, @RequestParam(value="glResourceTypeId", required=false) String glResourceTypeId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="parentGlAccountId", required=false) String parentGlAccountId, @RequestParam(value="accountName", required=false) String accountName, @RequestParam(value="glAccountClassId", required=false) String glAccountClassId, @RequestParam(value="description", required=false) String description, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="glXbrlClassId", required=false) String glXbrlClassId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("accountCode",accountCode);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("glResourceTypeId",glResourceTypeId);
		paramMap.put("productId",productId);
		paramMap.put("parentGlAccountId",parentGlAccountId);
		paramMap.put("accountName",accountName);
		paramMap.put("glAccountClassId",glAccountClassId);
		paramMap.put("description",description);
		paramMap.put("externalId",externalId);
		paramMap.put("glXbrlClassId",glXbrlClassId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/setGlReconciliationStatus")
	public ResponseEntity<Map<String, Object>> setGlReconciliationStatus(HttpSession session, @RequestParam(value="statusId") String statusId, @RequestParam(value="glReconciliationId") String glReconciliationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("statusId",statusId);
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("setGlReconciliationStatus", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAccount")
	public ResponseEntity<Map<String, Object>> createGlAccount(HttpSession session, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="glResourceTypeId") String glResourceTypeId, @RequestParam(value="accountName") String accountName, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="glAccountClassId") String glAccountClassId, @RequestParam(value="accountCode", required=false) String accountCode, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="parentGlAccountId", required=false) String parentGlAccountId, @RequestParam(value="description", required=false) String description, @RequestParam(value="externalId", required=false) String externalId, @RequestParam(value="glXbrlClassId", required=false) String glXbrlClassId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("glResourceTypeId",glResourceTypeId);
		paramMap.put("accountName",accountName);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("glAccountClassId",glAccountClassId);
		paramMap.put("accountCode",accountCode);
		paramMap.put("productId",productId);
		paramMap.put("parentGlAccountId",parentGlAccountId);
		paramMap.put("description",description);
		paramMap.put("externalId",externalId);
		paramMap.put("glXbrlClassId",glXbrlClassId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlAccountGroup")
	public ResponseEntity<Map<String, Object>> updateGlAccountGroup(HttpSession session, @RequestParam(value="glAccountGroupId") String glAccountGroupId, @RequestParam(value="glAccountGroupTypeId", required=false) String glAccountGroupTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountGroupId",glAccountGroupId);
		paramMap.put("glAccountGroupTypeId",glAccountGroupTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlAccountGroup", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlReconciliation")
	public ResponseEntity<Map<String, Object>> updateGlReconciliation(HttpSession session, @RequestParam(value="glReconciliationId") String glReconciliationId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="reconciledBalance", required=false) BigDecimal reconciledBalance, @RequestParam(value="statusId", required=false) String statusId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="glAccountId", required=false) String glAccountId, @RequestParam(value="glReconciliationName", required=false) String glReconciliationName, @RequestParam(value="description", required=false) String description, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="reconciledDate", required=false) Timestamp reconciledDate, @RequestParam(value="openingBalance", required=false) BigDecimal openingBalance) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("reconciledBalance",reconciledBalance);
		paramMap.put("statusId",statusId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("glReconciliationName",glReconciliationName);
		paramMap.put("description",description);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("reconciledDate",reconciledDate);
		paramMap.put("openingBalance",openingBalance);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlReconciliation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deletePartyGlAccount")
	public ResponseEntity<Map<String, Object>> deletePartyGlAccount(HttpSession session, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="glAccountTypeId") String glAccountTypeId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="partyId") String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deletePartyGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/quickCreateAcctgTransAndEntries")
	public ResponseEntity<Map<String, Object>> quickCreateAcctgTransAndEntries(HttpSession session, @RequestParam(value="creditGlAccountId") String creditGlAccountId, @RequestParam(value="amount") BigDecimal amount, @RequestParam(value="debitGlAccountId") String debitGlAccountId, @RequestParam(value="workEffortId", required=false) String workEffortId, @RequestParam(value="theirAcctgTransId", required=false) String theirAcctgTransId, @RequestParam(value="dueDate", required=false) Timestamp dueDate, @RequestParam(value="groupId", required=false) String groupId, @RequestParam(value="description", required=false) String description, @RequestParam(value="theirPartyId", required=false) String theirPartyId, @RequestParam(value="acctgTransEntryTypeId", required=false) String acctgTransEntryTypeId, @RequestParam(value="postedDate", required=false) Timestamp postedDate, @RequestParam(value="physicalInventoryId", required=false) String physicalInventoryId, @RequestParam(value="lastModifiedByUserLogin", required=false) String lastModifiedByUserLogin, @RequestParam(value="isPosted", required=false) String isPosted, @RequestParam(value="origAmount", required=false) BigDecimal origAmount, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="voucherDate", required=false) Timestamp voucherDate, @RequestParam(value="partyId", required=false) String partyId, @RequestParam(value="receiptId", required=false) String receiptId, @RequestParam(value="createdByUserLogin", required=false) String createdByUserLogin, @RequestParam(value="finAccountTransId", required=false) String finAccountTransId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="theirProductId", required=false) String theirProductId, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="acctgTransTypeId", required=false) String acctgTransTypeId, @RequestParam(value="lastModifiedDate", required=false) Timestamp lastModifiedDate, @RequestParam(value="glFiscalTypeId", required=false) String glFiscalTypeId, @RequestParam(value="settlementTermId", required=false) String settlementTermId, @RequestParam(value="reconcileStatusId", required=false) String reconcileStatusId, @RequestParam(value="glJournalId", required=false) String glJournalId, @RequestParam(value="isSummary", required=false) String isSummary, @RequestParam(value="transactionDate", required=false) Timestamp transactionDate, @RequestParam(value="scheduledPostingDate", required=false) Timestamp scheduledPostingDate, @RequestParam(value="voucherRef", required=false) String voucherRef, @RequestParam(value="inventoryItemId", required=false) String inventoryItemId, @RequestParam(value="createdDate", required=false) Timestamp createdDate, @RequestParam(value="currencyUomId", required=false) String currencyUomId, @RequestParam(value="glAccountTypeId", required=false) String glAccountTypeId, @RequestParam(value="shipmentId", required=false) String shipmentId, @RequestParam(value="taxId", required=false) String taxId, @RequestParam(value="origCurrencyUomId", required=false) String origCurrencyUomId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="organizationPartyId", required=false) String organizationPartyId, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="groupStatusId", required=false) String groupStatusId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("creditGlAccountId",creditGlAccountId);
		paramMap.put("amount",amount);
		paramMap.put("debitGlAccountId",debitGlAccountId);
		paramMap.put("workEffortId",workEffortId);
		paramMap.put("theirAcctgTransId",theirAcctgTransId);
		paramMap.put("dueDate",dueDate);
		paramMap.put("groupId",groupId);
		paramMap.put("description",description);
		paramMap.put("theirPartyId",theirPartyId);
		paramMap.put("acctgTransEntryTypeId",acctgTransEntryTypeId);
		paramMap.put("postedDate",postedDate);
		paramMap.put("physicalInventoryId",physicalInventoryId);
		paramMap.put("lastModifiedByUserLogin",lastModifiedByUserLogin);
		paramMap.put("isPosted",isPosted);
		paramMap.put("origAmount",origAmount);
		paramMap.put("paymentId",paymentId);
		paramMap.put("voucherDate",voucherDate);
		paramMap.put("partyId",partyId);
		paramMap.put("receiptId",receiptId);
		paramMap.put("createdByUserLogin",createdByUserLogin);
		paramMap.put("finAccountTransId",finAccountTransId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("theirProductId",theirProductId);
		paramMap.put("productId",productId);
		paramMap.put("acctgTransTypeId",acctgTransTypeId);
		paramMap.put("lastModifiedDate",lastModifiedDate);
		paramMap.put("glFiscalTypeId",glFiscalTypeId);
		paramMap.put("settlementTermId",settlementTermId);
		paramMap.put("reconcileStatusId",reconcileStatusId);
		paramMap.put("glJournalId",glJournalId);
		paramMap.put("isSummary",isSummary);
		paramMap.put("transactionDate",transactionDate);
		paramMap.put("scheduledPostingDate",scheduledPostingDate);
		paramMap.put("voucherRef",voucherRef);
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("createdDate",createdDate);
		paramMap.put("currencyUomId",currencyUomId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("shipmentId",shipmentId);
		paramMap.put("taxId",taxId);
		paramMap.put("origCurrencyUomId",origCurrencyUomId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("groupStatusId",groupStatusId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("quickCreateAcctgTransAndEntries", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForSalesShipmentIssuance")
	public ResponseEntity<Map<String, Object>> createAcctgTransForSalesShipmentIssuance(HttpSession session, @RequestParam(value="itemIssuanceId") String itemIssuanceId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("itemIssuanceId",itemIssuanceId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForSalesShipmentIssuance", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAccountGroupMember")
	public ResponseEntity<Map<String, Object>> createGlAccountGroupMember(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="glAccountGroupTypeId") String glAccountGroupTypeId, @RequestParam(value="glAccountGroupId", required=false) String glAccountGroupId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("glAccountGroupTypeId",glAccountGroupTypeId);
		paramMap.put("glAccountGroupId",glAccountGroupId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAccountGroupMember", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAccountRole")
	public ResponseEntity<Map<String, Object>> createGlAccountRole(HttpSession session, @RequestParam(value="fromDate") Timestamp fromDate, @RequestParam(value="roleTypeId") String roleTypeId, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="partyId") String partyId, @RequestParam(value="thruDate", required=false) Timestamp thruDate) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("fromDate",fromDate);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("partyId",partyId);
		paramMap.put("thruDate",thruDate);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAccountRole", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlJournal")
	public ResponseEntity<Map<String, Object>> deleteGlJournal(HttpSession session, @RequestParam(value="glJournalId") String glJournalId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glJournalId",glJournalId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlJournal", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlAccountClass")
	public ResponseEntity<Map<String, Object>> deleteGlAccountClass(HttpSession session, @RequestParam(value="glAccountClassId") String glAccountClassId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountClassId",glAccountClassId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlAccountClass", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createSettlementTerm")
	public ResponseEntity<Map<String, Object>> createSettlementTerm(HttpSession session, @RequestParam(value="settlementTermId", required=false) String settlementTermId, @RequestParam(value="uomId", required=false) String uomId, @RequestParam(value="termName", required=false) String termName, @RequestParam(value="termValue", required=false) Long termValue) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("settlementTermId",settlementTermId);
		paramMap.put("uomId",uomId);
		paramMap.put("termName",termName);
		paramMap.put("termValue",termValue);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createSettlementTerm", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getGlAccountFromAccountType")
	public ResponseEntity<Map<String, Object>> getGlAccountFromAccountType(HttpSession session, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="roleTypeId", required=false) String roleTypeId, @RequestParam(value="glAccountTypeId", required=false) String glAccountTypeId, @RequestParam(value="debitCreditFlag", required=false) String debitCreditFlag, @RequestParam(value="productId", required=false) String productId, @RequestParam(value="acctgTransTypeId", required=false) String acctgTransTypeId, @RequestParam(value="paymentId", required=false) String paymentId, @RequestParam(value="fixedAssetId", required=false) String fixedAssetId, @RequestParam(value="invoiceId", required=false) String invoiceId, @RequestParam(value="partyId", required=false) String partyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("roleTypeId",roleTypeId);
		paramMap.put("glAccountTypeId",glAccountTypeId);
		paramMap.put("debitCreditFlag",debitCreditFlag);
		paramMap.put("productId",productId);
		paramMap.put("acctgTransTypeId",acctgTransTypeId);
		paramMap.put("paymentId",paymentId);
		paramMap.put("fixedAssetId",fixedAssetId);
		paramMap.put("invoiceId",invoiceId);
		paramMap.put("partyId",partyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getGlAccountFromAccountType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createAcctgTransForInventoryItemCostChange")
	public ResponseEntity<Map<String, Object>> createAcctgTransForInventoryItemCostChange(HttpSession session, @RequestParam(value="inventoryItemId") String inventoryItemId, @RequestParam(value="inventoryItemDetailSeqId") String inventoryItemDetailSeqId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("inventoryItemId",inventoryItemId);
		paramMap.put("inventoryItemDetailSeqId",inventoryItemDetailSeqId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createAcctgTransForInventoryItemCostChange", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getGlAcctgAndAmountPercentage")
	public ResponseEntity<Map<String, Object>> getGlAcctgAndAmountPercentage(HttpSession session, @RequestParam(value="organizationPartyId") String organizationPartyId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("getGlAcctgAndAmountPercentage", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateAcctgTransTypeAttr")
	public ResponseEntity<Map<String, Object>> updateAcctgTransTypeAttr(HttpSession session, @RequestParam(value="acctgTransTypeId") String acctgTransTypeId, @RequestParam(value="attrName") String attrName, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransTypeId",acctgTransTypeId);
		paramMap.put("attrName",attrName);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateAcctgTransTypeAttr", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAccountClass")
	public ResponseEntity<Map<String, Object>> createGlAccountClass(HttpSession session, @RequestParam(value="parentClassId", required=false) String parentClassId, @RequestParam(value="glAccountClassId", required=false) String glAccountClassId, @RequestParam(value="description", required=false) String description, @RequestParam(value="isAssetClass", required=false) String isAssetClass) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("parentClassId",parentClassId);
		paramMap.put("glAccountClassId",glAccountClassId);
		paramMap.put("description",description);
		paramMap.put("isAssetClass",isAssetClass);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAccountClass", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/completeAcctgTransEntries")
	public ResponseEntity<Map<String, Object>> completeAcctgTransEntries(HttpSession session, @RequestParam(value="acctgTransId") String acctgTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("completeAcctgTransEntries", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/createGlAccountCategory")
	public ResponseEntity<Map<String, Object>> createGlAccountCategory(HttpSession session, @RequestParam(value="glAccountCategoryTypeId", required=false) String glAccountCategoryTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountCategoryTypeId",glAccountCategoryTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("createGlAccountCategory", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteAcctgTrans")
	public ResponseEntity<Map<String, Object>> deleteAcctgTrans(HttpSession session, @RequestParam(value="acctgTransId") String acctgTransId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("acctgTransId",acctgTransId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteAcctgTrans", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateFinAccountTypeGlAccount")
	public ResponseEntity<Map<String, Object>> updateFinAccountTypeGlAccount(HttpSession session, @RequestParam(value="glAccountId") String glAccountId, @RequestParam(value="organizationPartyId") String organizationPartyId, @RequestParam(value="finAccountTypeId") String finAccountTypeId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountId",glAccountId);
		paramMap.put("organizationPartyId",organizationPartyId);
		paramMap.put("finAccountTypeId",finAccountTypeId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateFinAccountTypeGlAccount", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/deleteGlReconciliation")
	public ResponseEntity<Map<String, Object>> deleteGlReconciliation(HttpSession session, @RequestParam(value="glReconciliationId") String glReconciliationId) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glReconciliationId",glReconciliationId);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("deleteGlReconciliation", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/updateGlAccountCategoryType")
	public ResponseEntity<Map<String, Object>> updateGlAccountCategoryType(HttpSession session, @RequestParam(value="glAccountCategoryTypeId") String glAccountCategoryTypeId, @RequestParam(value="description", required=false) String description) {
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("glAccountCategoryTypeId",glAccountCategoryTypeId);
		paramMap.put("description",description);
		paramMap.put("userLogin", session.getAttribute("userLogin"));

		Map<String, Object> result = new HashMap<>();
		LocalDispatcher dispatcher = (LocalDispatcher) session.getServletContext().getAttribute("dispatcher");
		try {
			result = dispatcher.runSync("updateGlAccountCategoryType", paramMap);
		} catch (ServiceAuthException e) {

			e.printStackTrace();
			return unauthorized();

		} catch (ServiceValidationException e) {
			return serverError();
		} catch (GenericServiceException e) {
			e.printStackTrace();
			return badRequest();
		}
		if(result.get("responseMessage").equals("error")) {
			return badRequest();
		}

		return successful(result);
	}



}
