package com.skytala.eCommerce.service.login.dto;

import com.skytala.eCommerce.domain.login.relations.userLogin.model.UserLogin;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.ContactMech;
import com.skytala.eCommerce.domain.party.relations.person.model.Person;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;
import com.skytala.eCommerce.framework.validation.PasswordMatches;
import com.skytala.eCommerce.framework.validation.ValidEmail;
import com.skytala.eCommerce.service.login.util.RegisterMessages;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;


@PasswordMatches
public class UserDetailsDTO {

        //Person details
    private String partyId;
    @NotNull
    @NotEmpty
    private String firstName;
    private String middleName;
    @NotNull
    @NotEmpty
    private String lastName;
    private String nickname;
    @NotNull
    private Boolean gender;
    @NotNull
    private Date birthDate;
    private String personalTitle;

        //userLogin details
    @NotNull
    @NotEmpty
    private String UserLoginId;
    @NotNull
    @NotEmpty
    @Size(min = 8, message = RegisterMessages.PASSWORD_TOO_SHORT)
    private String currentPassword;
    private String passwordRetype;
    private String oldPassword;
    private List<String> authorities;

        //postal address details
    private String addressContactMechId;
    private String toName;
    private String address1;
    private String houseNumberExt;
    private String city;
    private String postalCode;
    private String countryGeoId;
    private String stateProvinceGeoId;

        //email address
    private String emailContactMechId;
    @NotNull
    @NotEmpty
    @ValidEmail
    private String emailAddress;



    /*
        factory method
     */
    public static UserDetailsDTO create(Person person, UserLogin userLogin, PostalAddress address, ContactMech emailAddress){


        UserDetailsDTO dto = new UserDetailsDTO(person);

        if(userLogin!=null){
            dto.setUserLoginId(userLogin.getUserLoginId());
            dto.setCurrentPassword(userLogin.getCurrentPassword());
        }

        if(address!=null){

            dto.setAddressContactMechId(address.getContactMechId());
            dto.setToName(address.getToName());
            dto.setAddress1(address.getAddress1());
            dto.setHouseNumberExt(address.getHouseNumberExt());
            dto.setCity(address.getCity());
            dto.setPostalCode(address.getPostalCode());
            dto.setCountryGeoId(address.getCountryGeoId());
            dto.setStateProvinceGeoId(address.getStateProvinceGeoId());
        }

        if(emailAddress!=null){

            dto.setEmailContactMechId(emailAddress.getContactMechId());
            dto.setEmailAddress(emailAddress.getInfoString());
        }

        return dto;
    }

    public UserDetailsDTO(Person person){

        if(person != null){

            this.partyId = person.getPartyId();
            this.firstName = person.getFirstName();
            this.middleName = person.getMiddleName();
            this.lastName = person.getLastName();
            this.nickname = person.getNickname();
            this.gender = person.getGender();
            this.birthDate = person.getBirthDate();
            this.personalTitle = person.getPersonalTitle();
        }
    }

    public UserDetailsDTO(){

    }

    public Person extractPerson(){
        Person person = new Person();

        person.setPartyId(getPartyId());
        person.setFirstName(getFirstName());
        person.setMiddleName(getMiddleName());
        person.setLastName(getLastName());
        person.setNickname(getNickname());
        person.setGender(getGender());
        person.setBirthDate(getBirthDate());
        person.setPersonalTitle(getPersonalTitle());

        return person;
    }

    public UserLogin extractUserLogin(){
        UserLogin userLogin = new UserLogin();

        userLogin.setPartyId(getPartyId());
        userLogin.setUserLoginId(getUserLoginId());
        userLogin.setCurrentPassword(getCurrentPassword());

        return userLogin;

    }

    public PostalAddress extractPostalAddress(){
        PostalAddress address = new PostalAddress();

        address.setContactMechId(getAddressContactMechId());
        address.setToName(getToName());
        address.setAddress1(getAddress1());
        address.setHouseNumberExt(getHouseNumberExt());
        address.setCity(getCity());
        address.setPostalCode(getPostalCode());
        address.setCountryGeoId(getCountryGeoId());
        address.setStateProvinceGeoId(getStateProvinceGeoId());

        return address;
    }

    public ContactMech extractEmailAddress(){
        ContactMech contactMech = new ContactMech();

        contactMech.setContactMechId(getEmailContactMechId());
        contactMech.setInfoString(getEmailAddress());
        contactMech.setContactMechTypeId("EMAIL_ADDRESS");

        return contactMech;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPasswordRetype() {
        return passwordRetype;
    }

    public void setPasswordRetype(String passwordRetype) {
        this.passwordRetype = passwordRetype;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getPersonalTitle() {
        return personalTitle;
    }

    public void setPersonalTitle(String personalTitle) {
        this.personalTitle = personalTitle;
    }

    public String getUserLoginId() {
        return UserLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        UserLoginId = userLoginId;
    }

    public String getAddressContactMechId() {
        return addressContactMechId;
    }

    public void setAddressContactMechId(String addressContactMechId) {
        this.addressContactMechId = addressContactMechId;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getHouseNumberExt() {
        return houseNumberExt;
    }

    public void setHouseNumberExt(String houseNumberExt) {
        this.houseNumberExt = houseNumberExt;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryGeoId() {
        return countryGeoId;
    }

    public void setCountryGeoId(String countryGeoId) {
        this.countryGeoId = countryGeoId;
    }

    public String getStateProvinceGeoId() {
        return stateProvinceGeoId;
    }

    public void setStateProvinceGeoId(String stateProvinceGeoId) {
        this.stateProvinceGeoId = stateProvinceGeoId;
    }

    public String getEmailContactMechId() {
        return emailContactMechId;
    }

    public void setEmailContactMechId(String emailContactMechId) {
        this.emailContactMechId = emailContactMechId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
