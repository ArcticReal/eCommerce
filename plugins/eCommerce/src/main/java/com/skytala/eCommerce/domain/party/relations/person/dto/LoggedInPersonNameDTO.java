package com.skytala.eCommerce.domain.party.relations.person.dto;

import com.skytala.eCommerce.domain.party.relations.person.model.Person;

public class LoggedInPersonNameDTO {

    private String personalTitle;
    private String firstName;
    private String middleName;
    private String lastName;


    public LoggedInPersonNameDTO(Person person) {

        if(person != null){

            this.personalTitle = person.getPersonalTitle();
            this.firstName = person.getFirstName();
            this.middleName = person.getMiddleName();
            this.lastName = person.getLastName();
        }

    }

    public String getPersonalTitle() {
        return personalTitle;
    }

    public void setPersonalTitle(String personalTitle) {
        this.personalTitle = personalTitle;
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
}
