package com.skytala.eCommerce.domain.login.relations.userLogin.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.UserLoginUpdated;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.UserLogin;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateUserLogin extends Command {

private UserLogin elementToBeUpdated;

public UpdateUserLogin(UserLogin elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public UserLogin getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(UserLogin elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("UserLogin", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(UserLogin.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(UserLogin.class);
}
success = false;
}
Event resultingEvent = new UserLoginUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
