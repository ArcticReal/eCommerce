package com.skytala.eCommerce.domain.login.relations.userLogin.command.securityGroup;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityGroup.UserLoginSecurityGroupUpdated;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityGroup.UserLoginSecurityGroup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateUserLoginSecurityGroup extends Command {

private UserLoginSecurityGroup elementToBeUpdated;

public UpdateUserLoginSecurityGroup(UserLoginSecurityGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public UserLoginSecurityGroup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(UserLoginSecurityGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("UserLoginSecurityGroup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(UserLoginSecurityGroup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(UserLoginSecurityGroup.class);
}
success = false;
}
Event resultingEvent = new UserLoginSecurityGroupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
