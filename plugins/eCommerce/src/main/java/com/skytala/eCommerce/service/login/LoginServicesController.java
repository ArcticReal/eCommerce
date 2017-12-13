package com.skytala.eCommerce.service.login;

import com.skytala.eCommerce.config.SecurityConfig;
import com.skytala.eCommerce.domain.login.relations.userLogin.UserLoginController;
import com.skytala.eCommerce.domain.login.relations.userLogin.control.securityGroup.UserLoginSecurityGroupController;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.UserLogin;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityGroup.UserLoginSecurityGroup;
import com.skytala.eCommerce.domain.party.PartyController;
import com.skytala.eCommerce.domain.party.model.Party;
import com.skytala.eCommerce.domain.party.relations.contactMech.ContactMechController;
import com.skytala.eCommerce.domain.party.relations.contactMech.control.party.PartyContactMechController;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.ContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.party.PartyContactMech;
import com.skytala.eCommerce.domain.party.relations.contactMech.util.ContactMechTypes;
import com.skytala.eCommerce.domain.party.relations.contactMech.util.Email;
import com.skytala.eCommerce.domain.party.relations.contactMech.util.WebShopEmailUtil;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.EmailAddressVerificationController;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.model.EmailAddressVerification;
import com.skytala.eCommerce.domain.party.relations.person.PersonController;
import com.skytala.eCommerce.domain.party.relations.person.model.Person;
import com.skytala.eCommerce.domain.party.relations.postalAddress.PostalAddressController;
import com.skytala.eCommerce.domain.party.relations.postalAddress.model.PostalAddress;
import com.skytala.eCommerce.domain.product.ProductController;
import com.skytala.eCommerce.framework.util.TimestampUtil;
import com.skytala.eCommerce.service.common.CommonsServiceController;
import com.skytala.eCommerce.service.login.dto.UserDetailsDTO;
import com.skytala.eCommerce.service.login.util.LoginMessages;
import com.skytala.eCommerce.service.login.util.RegisterMessages;
import com.skytala.eCommerce.service.login.util.SecurityGroups;
import com.skytala.eCommerce.service.login.util.UpdateUserMessages;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.service.GenericServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.validation.Valid;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

import static com.skytala.eCommerce.framework.pubsub.ResponseUtil.*;
import static com.skytala.eCommerce.framework.util.AuthorizeMethods.HAS_USER_AUTHORITY;


@RestController
public class LoginServicesController {

    @Resource
    PartyController partyController;

    @Resource
    UserLoginController userLoginController;

    @Resource
    PersonController personController;

    @Resource
    ContactMechController contactMechController;

    @Resource
    EmailAddressVerificationController verificationController;

    @Resource
    PostalAddressController postalAddressController;

    @Resource
    PartyContactMechController partyContactMechController;

    @Resource
    ProductController productController;

    @Resource
    CommonsServiceController commonsServiceController;

    @Resource
    UserLoginSecurityGroupController authorityController;

    @Autowired
    SecurityConfig securityConfig;

    @Autowired
    Validator validator;

    //debug TODO take this out
    @Autowired
    DataSource dataSource;


    @PostMapping("/register")
    public ResponseEntity registerUserAccount(HttpSession session,
                                              @RequestBody @Valid UserDetailsDTO userDetails) throws Exception {


        List<ContactMech> emailAddresses = contactMechController
                .findContactMechsBy(UtilMisc.toMap("contactMechTypeId", ContactMechTypes.EMAIL_ADDRESS,
                        "infoString", userDetails.getEmailAddress()))
                .getBody();

        if (emailAddresses.size() != 0)
            throw new IllegalArgumentException(RegisterMessages.EMAIL_ALREADY_IN_USE);

        UserLogin userLogin = userLoginController.findById(userDetails.getUserLoginId()).getBody();

        if (userLogin != null)
            throw new IllegalArgumentException(RegisterMessages.USERNAME_ALREADY_TAKEN);


        try {

            //Encrypt passwords
            userDetails.setCurrentPassword(securityConfig.passwordEncoder().encode(userDetails.getCurrentPassword()));


            //create Party and set ID in userDetails
            Party addedParty = partyController.createParty(new Party()).getBody();
            userDetails.setPartyId(addedParty.getPartyId());

            //create userLogin
            userLogin = userDetails.extractUserLogin();
            userLogin.setEnabled(false);
            UserLogin addedUserLogin = userLoginController.createUserLogin(userLogin).getBody();

            //create UserLoginSecurityGroup(analog to Spring authority)
            UserLoginSecurityGroup authority = new UserLoginSecurityGroup();
            authority.setUserLoginId(addedUserLogin.getUserLoginId());
            authority.setFromDate(TimestampUtil.currentTime());
            authority.setGroupId(SecurityGroups.USER);
            authorityController.createUserLoginSecurityGroup(authority);

            //create person
            Person addedPerson = personController.createPerson(userDetails.extractPerson()).getBody();

            ContactMech contactMech = new ContactMech();
            PartyContactMech partyContactMech = new PartyContactMech();
            PostalAddress address = null;
            if (userDetails.extractPostalAddress().isValid()) {

                //create postal address (with contact mech) and set ID in userDetails
                contactMech.setContactMechTypeId(ContactMechTypes.POSTAL_ADDRESS);
                ContactMech addedAddressMech = contactMechController.createContactMech(contactMech).getBody();

                userDetails.setAddressContactMechId(contactMech.getContactMechId());
                PostalAddress addressToBeAdded = userDetails.extractPostalAddress();
                address = postalAddressController.createPostalAddress(addressToBeAdded).getBody();

                //link party with postal address
                partyContactMech.setContactMechId(userDetails.getAddressContactMechId());
                partyContactMech.setPartyId(userDetails.getPartyId());
                partyContactMech.setFromDate(TimestampUtil.currentTime());
                partyContactMechController.createPartyContactMech(partyContactMech);
            }

            //create email and set ID in userDetails
            contactMech = userDetails.extractEmailAddress();
            contactMech = contactMechController.createContactMech(contactMech).getBody();
            userDetails.setEmailContactMechId(contactMech.getContactMechId());

            //link party  with emailAddress
            partyContactMech = new PartyContactMech();
            partyContactMech.setContactMechId(userDetails.getEmailContactMechId());
            partyContactMech.setPartyId(userDetails.getPartyId());
            partyContactMech.setFromDate(TimestampUtil.currentTime());
            partyContactMechController.createPartyContactMech(partyContactMech);


            sendVerificationMail(session, userDetails.getUserLoginId(), contactMech);
            return successful(UserDetailsDTO.create(addedPerson, addedUserLogin, address, contactMech));

        }catch(Exception e){

            //TODO: somehow handle transactions
            throw e;
        }

    }

    @PutMapping("/userDetails/update/{partyId}")
    @PreAuthorize(HAS_USER_AUTHORITY)
    public ResponseEntity updateUserDetails(@PathVariable("partyId") String partyId,
                                            @RequestBody UserDetailsDTO userDetails,
                                            Errors errors) throws Exception {

        userDetails.setPartyId(partyId);

        UserLogin currentUserLogin = userLoginController.findById(userDetails.getUserLoginId()).getBody();
        String oldPasswordFromDb = currentUserLogin.getCurrentPassword();

        if(userDetails.getCurrentPassword()!=null&&!userDetails.getCurrentPassword().equals("")) {

            if (!securityConfig.passwordEncoder().matches(userDetails.getOldPassword(), oldPasswordFromDb))
                return unauthorized(UpdateUserMessages.WRONG_PASSWORD);

        }else{
            userDetails.setCurrentPassword(oldPasswordFromDb);
            userDetails.setPasswordRetype(oldPasswordFromDb);
        }

        //email is not updatable and wont be updated in this following method!
        userDetails.setEmailAddress("not.failing@validation.de");
        validator.validate(userDetails, errors);
        if(errors.hasErrors()){

            throw new IllegalArgumentException();

        }

        UserLogin userLogin = userDetails.extractUserLogin();

        //only update pw if new pw is another pw
        if(!(securityConfig.passwordEncoder().matches(userDetails.getCurrentPassword(), oldPasswordFromDb)
                ||userDetails.getCurrentPassword().equals(oldPasswordFromDb)))
            userLogin.setCurrentPassword(securityConfig.passwordEncoder().encode(userLogin.getCurrentPassword()));
        else
            userLogin.setCurrentPassword(oldPasswordFromDb);

        userLoginController.updateUserLogin(userLogin, userLogin.getUserLoginId()).getBody();


        Person person = userDetails.extractPerson();
        personController.updatePerson(person, person.getPartyId());



        PostalAddress address = userDetails.extractPostalAddress();
        if(address.isValid()) {
            ContactMech contactMech = contactMechController.getPostalAddressFor(userDetails.getPartyId());
            if (contactMech != null)
                postalAddressController.updatePostalAddress(address, contactMech.getContactMechId());
            else {

                contactMech = new ContactMech();
                PartyContactMech partyContactMech = new PartyContactMech();

                //create postal address (with contact mech) and set ID in userDetails
                contactMech.setContactMechTypeId(ContactMechTypes.POSTAL_ADDRESS);
                ContactMech addedAddressMech = contactMechController.createContactMech(contactMech).getBody();

                userDetails.setAddressContactMechId(contactMech.getContactMechId());
                PostalAddress addressToBeAdded = userDetails.extractPostalAddress();
                address = postalAddressController.createPostalAddress(addressToBeAdded).getBody();

                //link party with postal address
                partyContactMech.setContactMechId(userDetails.getAddressContactMechId());
                partyContactMech.setPartyId(userDetails.getPartyId());
                partyContactMech.setFromDate(TimestampUtil.currentTime());
                partyContactMechController.createPartyContactMech(partyContactMech);

            }
        }




        return successful();

    }

    private void sendVerificationMail(HttpSession session, String hashVal, ContactMech emailAddress) throws Exception {


        //generate hash for account verification
        String hash = securityConfig.passwordEncoder()
                                    .encode(hashVal)
                                    .replace("/", "")
                                    .replace("?", "")
                                    .replace(".", "");

        //write hash to database
        EmailAddressVerification verification = new EmailAddressVerification();
        verification.setEmailAddress(emailAddress.getInfoString());
        verification.setVerifyHash(hash);
        verification.setExpireDate(TimestampUtil.emailVerificationExpireTime());
        verification = verificationController.createEmailAddressVerification(verification).getBody();

        //prepare email
        WebShopEmailUtil.reset();   /**TODO: this could become an extra endpoint
                                      to call after somebody edited the
                                      email body. Now this is for hot swap debug reasons
                                    */
        Email email = WebShopEmailUtil.ACCOUNT_VERIFICATION_EMAIL;
        email.setBody(email.getBody().replace("%%verifyHash%%", hash));
        email.setSendTo(emailAddress.getInfoString());


        //send verification email
        emailAddress.sendEmail(session, email , null, null);
    }

    @GetMapping("/account/verify/{verificationHash}")
    public ResponseEntity verifyAccount(@PathVariable("verificationHash") String verificationHash) throws Exception {

        EmailAddressVerification verification = verificationController.findByHash(verificationHash).getBody();

        if(verification==null)
            return notFound();      //link with that hash not found

        if(verification.getExpireDate().before(TimestampUtil.currentTime()))
            return badRequest();    //verification link expired


        //look for email
        List<ContactMech> emailAddresses = contactMechController
                .findContactMechsBy(UtilMisc.toMap("contactMechTypeId", ContactMechTypes.EMAIL_ADDRESS,
                                                    "infoString", verification.getEmailAddress()))
                .getBody();

        if(emailAddresses.size()!=1)
            return notFound();

        //look for link to party
        List<PartyContactMech> partyContactMechs = partyContactMechController
                .findPartyContactMechsBy(UtilMisc.toMap("contactMechId", emailAddresses.get(0).getContactMechId()))
                .getBody();


        if(partyContactMechs.size()!=1)
            return notFound();

        //get party
        Party party = partyController.findById(partyContactMechs.get(0).getPartyId()).getBody();

        //get userLogin
        List<UserLogin> userLogins = userLoginController
                .findUserLoginsBy(UtilMisc.toMap("partyId", party.getPartyId()))
                .getBody();

        if(userLogins.size()!=1)
            return notFound();

        UserLogin login = userLogins.get(0);

        //enable user
        if(!login.getEnabled()){
            login.setEnabled(true);
            userLoginController.updateUserLogin(login, login.getUserLoginId());
            verificationController.deleteEmailAddressVerificationByHash(verificationHash);
            if(login.getRequirePasswordChange()!=null&&login.getRequirePasswordChange())
                return successful("require password change");
            else
                return successful();
        }else
            return badRequest();

    }

    @GetMapping("/resendVerificationMail/{oldHash}")
    public ResponseEntity resendVerificationMail(HttpSession session,
                                                 @PathVariable("oldHash") String oldHash) throws Exception {

        EmailAddressVerification verification = verificationController.findByHash(oldHash).getBody();
        ContactMech contactMech = new ContactMech();
        contactMech.setInfoString(verification.getEmailAddress());

        sendVerificationMail(session, oldHash, contactMech);

        verificationController.deleteEmailAddressVerificationByHash(oldHash);


        return successful();
    }

    @GetMapping("/loginNeeded")
    public ResponseEntity loginNeeded(){
        return unauthorized(LoginMessages.LOGIN_NEEDED);
    }

    @GetMapping("/loginFailed/badCredentials")
    public ResponseEntity loginFailedBadCredentials(){
        return unauthorized(LoginMessages.BAD_CREDENTIALS);
    }

    @GetMapping("/loginFailed/userIsDisabled")
    public ResponseEntity loginFailedDisabled(){
        return unauthorized(LoginMessages.USER_DISABLED);
    }

    @GetMapping("/successfulLogin")
    public ResponseEntity successfulLogin(HttpSession session) throws GenericEntityException, GenericServiceException {

        Object userLogin = commonsServiceController.userLogin(session,
                "flexadmin",
                "ofbiz",
                "1000",
                true).getBody().get("userLogin");

        session.setAttribute("userLogin", userLogin);

        return successful(LoginMessages.LOGIN_SUCCESSFUL);
    }

    @GetMapping("/successfulLogout")
    public ResponseEntity successfulLogout(){
        return successful(LoginMessages.LOGOUT_SUCCESSFUL);
    }



    @GetMapping("/loggedInPerson")
    public ResponseEntity<UserDetailsDTO> getLoggedInPerson(Principal principal) throws Exception {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken)principal;
        if(token==null)
            return notFound();

        User user = (User)token.getPrincipal();

        if(user == null)
            return notFound();

        UserLogin userLogin = userLoginController.findById(user.getUsername()).getBody();
        Person person = personController.findById(userLogin.getPartyId()).getBody();
        ContactMech emailAddress = contactMechController.getEmailAddressFor(userLogin.getPartyId());
        ContactMech postalAddress = contactMechController.getPostalAddressFor(userLogin.getPartyId());
        PostalAddress address = null;
        if(postalAddress!=null)
            address = postalAddressController.findById(postalAddress.getContactMechId()).getBody();
        UserDetailsDTO dto = UserDetailsDTO.create(person, userLogin, address, emailAddress);

        List<String> authorities = user.getAuthorities()
                .stream()
                .distinct()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        dto.setAuthorities(authorities);

        return successful(dto);

    }


}
