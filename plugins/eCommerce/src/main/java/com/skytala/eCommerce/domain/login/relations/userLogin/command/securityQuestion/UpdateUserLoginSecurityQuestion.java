package com.skytala.eCommerce.domain.login.relations.userLogin.command.securityQuestion;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityQuestion.UserLoginSecurityQuestionUpdated;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityQuestion.UserLoginSecurityQuestion;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateUserLoginSecurityQuestion extends Command {

private UserLoginSecurityQuestion elementToBeUpdated;

public UpdateUserLoginSecurityQuestion(UserLoginSecurityQuestion elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public UserLoginSecurityQuestion getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(UserLoginSecurityQuestion elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("UserLoginSecurityQuestion", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(UserLoginSecurityQuestion.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(UserLoginSecurityQuestion.class);
}
success = false;
}
Event resultingEvent = new UserLoginSecurityQuestionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
