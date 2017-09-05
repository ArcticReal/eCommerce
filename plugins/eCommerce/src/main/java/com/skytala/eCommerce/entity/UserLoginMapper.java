package com.skytala.eCommerce.entity;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ofbiz.entity.GenericValue;

public class UserLoginMapper {

	public static Map<String, Object> map(UserLogin userlogin) {

		Map<String, Object> returnVal = new HashMap<String, Object>();

		if (userlogin.getUserLoginId() != null) {
			returnVal.put("userLoginId", userlogin.getUserLoginId());
		}

		if (userlogin.getCurrentPassword() != null) {
			returnVal.put("currentPassword", userlogin.getCurrentPassword());
		}

		if (userlogin.getPasswordHint() != null) {
			returnVal.put("passwordHint", userlogin.getPasswordHint());
		}

		if (userlogin.getIsSystem() != null) {
			returnVal.put("isSystem", userlogin.getIsSystem());
		}

		if (userlogin.getEnabled() != null) {
			returnVal.put("enabled", userlogin.getEnabled());
		}

		if (userlogin.getHasLoggedOut() != null) {
			returnVal.put("hasLoggedOut", userlogin.getHasLoggedOut());
		}

		if (userlogin.getRequirePasswordChange() != null) {
			returnVal.put("requirePasswordChange", userlogin.getRequirePasswordChange());
		}

		if (userlogin.getLastCurrencyUom() != null) {
			returnVal.put("lastCurrencyUom", userlogin.getLastCurrencyUom());
		}

		if (userlogin.getLastLocale() != null) {
			returnVal.put("lastLocale", userlogin.getLastLocale());
		}

		if (userlogin.getLastTimeZone() != null) {
			returnVal.put("lastTimeZone", userlogin.getLastTimeZone());
		}

		if (userlogin.getDisabledDateTime() != null) {
			returnVal.put("disabledDateTime", userlogin.getDisabledDateTime());
		}

		if (userlogin.getSuccessiveFailedLogins() != null) {
			returnVal.put("successiveFailedLogins", userlogin.getSuccessiveFailedLogins());
		}

		if (userlogin.getPartyId() != null) {
			returnVal.put("partyId", userlogin.getPartyId());
		}

		if (userlogin.getExternalAuthId() != null) {
			returnVal.put("externalAuthId", userlogin.getExternalAuthId());
		}

		if (userlogin.getUserLdapDn() != null) {
			returnVal.put("userLdapDn", userlogin.getUserLdapDn());
		}

		if (userlogin.getDisabledBy() != null) {
			returnVal.put("disabledBy", userlogin.getDisabledBy());
		}

		return returnVal;
	}

	public static UserLogin map(Map<String, Object> fields) {

		UserLogin returnVal = new UserLogin();

		if (fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
		}

		if (fields.get("currentPassword") != null) {
			returnVal.setCurrentPassword((String) fields.get("currentPassword"));
		}

		if (fields.get("passwordHint") != null) {
			returnVal.setPasswordHint((String) fields.get("passwordHint"));
		}

		if (fields.get("isSystem") != null) {
			returnVal.setIsSystem((boolean) fields.get("isSystem"));
		}

		if (fields.get("enabled") != null) {
			returnVal.setEnabled((boolean) fields.get("enabled"));
		}

		if (fields.get("hasLoggedOut") != null) {
			returnVal.setHasLoggedOut((boolean) fields.get("hasLoggedOut"));
		}

		if (fields.get("requirePasswordChange") != null) {
			returnVal.setRequirePasswordChange((boolean) fields.get("requirePasswordChange"));
		}

		if (fields.get("lastCurrencyUom") != null) {
			returnVal.setLastCurrencyUom((String) fields.get("lastCurrencyUom"));
		}

		if (fields.get("lastLocale") != null) {
			returnVal.setLastLocale((String) fields.get("lastLocale"));
		}

		if (fields.get("lastTimeZone") != null) {
			returnVal.setLastTimeZone((String) fields.get("lastTimeZone"));
		}

		if (fields.get("disabledDateTime") != null) {
			returnVal.setDisabledDateTime((Timestamp) fields.get("disabledDateTime"));
		}

		if (fields.get("successiveFailedLogins") != null) {
			returnVal.setSuccessiveFailedLogins((long) fields.get("successiveFailedLogins"));
		}

		if (fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
		}

		if (fields.get("externalAuthId") != null) {
			returnVal.setExternalAuthId((String) fields.get("externalAuthId"));
		}

		if (fields.get("userLdapDn") != null) {
			returnVal.setUserLdapDn((String) fields.get("userLdapDn"));
		}

		if (fields.get("disabledBy") != null) {
			returnVal.setDisabledBy((String) fields.get("disabledBy"));
		}

		return returnVal;
	}

	public static UserLogin mapstrstr(Map<String, String> fields) throws Exception {

		UserLogin returnVal = new UserLogin();

		if (fields.get("userLoginId") != null) {
			returnVal.setUserLoginId((String) fields.get("userLoginId"));
		}

		if (fields.get("currentPassword") != null) {
			returnVal.setCurrentPassword((String) fields.get("currentPassword"));
		}

		if (fields.get("passwordHint") != null) {
			returnVal.setPasswordHint((String) fields.get("passwordHint"));
		}

		if (fields.get("isSystem") != null) {
			String buf;
			buf = fields.get("isSystem");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsSystem(ibuf);
		}

		if (fields.get("enabled") != null) {
			String buf;
			buf = fields.get("enabled");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setEnabled(ibuf);
		}

		if (fields.get("hasLoggedOut") != null) {
			String buf;
			buf = fields.get("hasLoggedOut");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasLoggedOut(ibuf);
		}

		if (fields.get("requirePasswordChange") != null) {
			String buf;
			buf = fields.get("requirePasswordChange");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequirePasswordChange(ibuf);
		}

		if (fields.get("lastCurrencyUom") != null) {
			returnVal.setLastCurrencyUom((String) fields.get("lastCurrencyUom"));
		}

		if (fields.get("lastLocale") != null) {
			returnVal.setLastLocale((String) fields.get("lastLocale"));
		}

		if (fields.get("lastTimeZone") != null) {
			returnVal.setLastTimeZone((String) fields.get("lastTimeZone"));
		}

		if (fields.get("disabledDateTime") != null) {
			String buf = fields.get("disabledDateTime");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDisabledDateTime(ibuf);
		}

		if (fields.get("successiveFailedLogins") != null) {
			String buf;
			buf = fields.get("successiveFailedLogins");
			long ibuf = Long.parseLong(buf);
			returnVal.setSuccessiveFailedLogins(ibuf);
		}

		if (fields.get("partyId") != null) {
			returnVal.setPartyId((String) fields.get("partyId"));
		}

		if (fields.get("externalAuthId") != null) {
			returnVal.setExternalAuthId((String) fields.get("externalAuthId"));
		}

		if (fields.get("userLdapDn") != null) {
			returnVal.setUserLdapDn((String) fields.get("userLdapDn"));
		}

		if (fields.get("disabledBy") != null) {
			returnVal.setDisabledBy((String) fields.get("disabledBy"));
		}

		return returnVal;
	}

	public static UserLogin map(GenericValue val) {

		UserLogin returnVal = new UserLogin();
		returnVal.setUserLoginId(val.getString("userLoginId"));
		returnVal.setCurrentPassword(val.getString("currentPassword"));
		returnVal.setPasswordHint(val.getString("passwordHint"));
		returnVal.setIsSystem(val.getBoolean("isSystem"));
		returnVal.setEnabled(val.getBoolean("enabled"));
		returnVal.setHasLoggedOut(val.getBoolean("hasLoggedOut"));
		returnVal.setRequirePasswordChange(val.getBoolean("requirePasswordChange"));
		returnVal.setLastCurrencyUom(val.getString("lastCurrencyUom"));
		returnVal.setLastLocale(val.getString("lastLocale"));
		returnVal.setLastTimeZone(val.getString("lastTimeZone"));
		returnVal.setDisabledDateTime(val.getTimestamp("disabledDateTime"));
		returnVal.setSuccessiveFailedLogins(val.getLong("successiveFailedLogins"));
		returnVal.setPartyId(val.getString("partyId"));
		returnVal.setExternalAuthId(val.getString("externalAuthId"));
		returnVal.setUserLdapDn(val.getString("userLdapDn"));
		returnVal.setDisabledBy(val.getString("disabledBy"));

		return returnVal;

	}

	public static UserLogin map(HttpServletRequest request) throws Exception {

		UserLogin returnVal = new UserLogin();

		Map<String, String[]> paramMap = request.getParameterMap();

		if (!paramMap.containsKey("userLoginId")) {
			throw new Exception("Error! Id required");
		} else {
			returnVal.setUserLoginId(request.getParameter("productId"));
		}

		if (paramMap.containsKey("currentPassword")) {
			returnVal.setCurrentPassword(request.getParameter("currentPassword"));
		}
		if (paramMap.containsKey("passwordHint")) {
			returnVal.setPasswordHint(request.getParameter("passwordHint"));
		}
		if (paramMap.containsKey("isSystem")) {
			String buf = request.getParameter("isSystem");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setIsSystem(ibuf);
		}
		if (paramMap.containsKey("enabled")) {
			String buf = request.getParameter("enabled");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setEnabled(ibuf);
		}
		if (paramMap.containsKey("hasLoggedOut")) {
			String buf = request.getParameter("hasLoggedOut");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setHasLoggedOut(ibuf);
		}
		if (paramMap.containsKey("requirePasswordChange")) {
			String buf = request.getParameter("requirePasswordChange");
			Boolean ibuf = Boolean.parseBoolean(buf);
			returnVal.setRequirePasswordChange(ibuf);
		}
		if (paramMap.containsKey("lastCurrencyUom")) {
			returnVal.setLastCurrencyUom(request.getParameter("lastCurrencyUom"));
		}
		if (paramMap.containsKey("lastLocale")) {
			returnVal.setLastLocale(request.getParameter("lastLocale"));
		}
		if (paramMap.containsKey("lastTimeZone")) {
			returnVal.setLastTimeZone(request.getParameter("lastTimeZone"));
		}
		if (paramMap.containsKey("disabledDateTime")) {
			String buf = request.getParameter("disabledDateTime");
			Timestamp ibuf = Timestamp.valueOf(buf);
			returnVal.setDisabledDateTime(ibuf);
		}
		if (paramMap.containsKey("successiveFailedLogins")) {
			String buf = request.getParameter("successiveFailedLogins");
			Long ibuf = Long.parseLong(buf);
			returnVal.setSuccessiveFailedLogins(ibuf);
		}
		if (paramMap.containsKey("partyId")) {
			returnVal.setPartyId(request.getParameter("partyId"));
		}
		if (paramMap.containsKey("externalAuthId")) {
			returnVal.setExternalAuthId(request.getParameter("externalAuthId"));
		}
		if (paramMap.containsKey("userLdapDn")) {
			returnVal.setUserLdapDn(request.getParameter("userLdapDn"));
		}
		if (paramMap.containsKey("disabledBy")) {
			returnVal.setDisabledBy(request.getParameter("disabledBy"));
		}
		return returnVal;

	}
}
