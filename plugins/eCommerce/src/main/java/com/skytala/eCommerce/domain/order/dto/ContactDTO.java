package com.skytala.eCommerce.domain.order.dto;

import com.skytala.eCommerce.domain.party.relations.contactMech.model.ContactMech;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;

public class ContactDTO {

    // postal address
    private String addressContactMechId;
    private String toName;
    private String address1;
    private String city;
    private String postalCode;
    private String countryGeoId;
    private String stateProvinceGeoId;

    // email
    private String eMailContactMechId;
    private String eMailAddress;

    public ContactDTO(){

    }


    public PostalAddress extractPostalAddress(){
        PostalAddress address = new PostalAddress();
        address.setContactMechId(addressContactMechId);
        address.setToName(toName);
        address.setAddress1(address1);
        address.setCity(city);
        address.setPostalCode(postalCode);
        address.setCountryGeoId(countryGeoId);
        address.setStateProvinceGeoId(stateProvinceGeoId);

        return address;
    }


    public String getAddressContactMechId() {
        return addressContactMechId;
    }

    public void setAddressContactMechId(String addressContactMechId) {
        this.addressContactMechId = addressContactMechId;
    }

    public String geteMailContactMechId() {
        return eMailContactMechId;
    }

    public void seteMailContactMechId(String eMailContactMechId) {
        this.eMailContactMechId = eMailContactMechId;
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

    public String geteMailAddress() {
        return eMailAddress;
    }

    public void seteMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }

    public ContactMech extractEMailAddress() {
        ContactMech mech = new ContactMech();
        mech.setContactMechId(eMailContactMechId);
        mech.setInfoString(eMailAddress);
        mech.setContactMechTypeId("EMAIL_ADDRESS");
        return mech;
    }
}
