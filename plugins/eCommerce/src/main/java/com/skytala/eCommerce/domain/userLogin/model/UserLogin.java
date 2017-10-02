package com.skytala.eCommerce.domain.userLogin.model;

import java.sql.Timestamp;
import java.util.Map;

import com.skytala.eCommerce.domain.userLogin.mapper.UserLoginMapper;

public class UserLogin {

	private String userLoginId;
	private String currentPassword;
	private String passwordHint;
	private Boolean isSystem;
	private Boolean enabled;
	private Boolean hasLoggedOut;
	private Boolean requirePasswordChange;
	private String lastCurrencyUom;
	private String lastLocale;
	private String lastTimeZone;
	private Timestamp disabledDateTime;
	private Long successiveFailedLogins;
	private String partyId;
	private String externalAuthId;
	private String userLdapDn;
	private String disabledBy;

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getPasswordHint() {
		return passwordHint;
	}

	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}

	public Boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Boolean isSystem) {
		this.isSystem = isSystem;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getHasLoggedOut() {
		return hasLoggedOut;
	}

	public void setHasLoggedOut(Boolean hasLoggedOut) {
		this.hasLoggedOut = hasLoggedOut;
	}

	public Boolean getRequirePasswordChange() {
		return requirePasswordChange;
	}

	public void setRequirePasswordChange(Boolean requirePasswordChange) {
		this.requirePasswordChange = requirePasswordChange;
	}

	public String getLastCurrencyUom() {
		return lastCurrencyUom;
	}

	public void setLastCurrencyUom(String lastCurrencyUom) {
		this.lastCurrencyUom = lastCurrencyUom;
	}

	public String getLastLocale() {
		return lastLocale;
	}

	public void setLastLocale(String lastLocale) {
		this.lastLocale = lastLocale;
	}

	public String getLastTimeZone() {
		return lastTimeZone;
	}

	public void setLastTimeZone(String lastTimeZone) {
		this.lastTimeZone = lastTimeZone;
	}

	public Timestamp getDisabledDateTime() {
		return disabledDateTime;
	}

	public void setDisabledDateTime(Timestamp disabledDateTime) {
		this.disabledDateTime = disabledDateTime;
	}

	public Long getSuccessiveFailedLogins() {
		return successiveFailedLogins;
	}

	public void setSuccessiveFailedLogins(Long successiveFailedLogins) {
		this.successiveFailedLogins = successiveFailedLogins;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getExternalAuthId() {
		return externalAuthId;
	}

	public void setExternalAuthId(String externalAuthId) {
		this.externalAuthId = externalAuthId;
	}

	public String getUserLdapDn() {
		return userLdapDn;
	}

	public void setUserLdapDn(String userLdapDn) {
		this.userLdapDn = userLdapDn;
	}

	public String getDisabledBy() {
		return disabledBy;
	}

	public void setDisabledBy(String disabledBy) {
		this.disabledBy = disabledBy;
	}

	public Map<String, Object> mapAttributeField() {
		return UserLoginMapper.map(this);
	}
}
