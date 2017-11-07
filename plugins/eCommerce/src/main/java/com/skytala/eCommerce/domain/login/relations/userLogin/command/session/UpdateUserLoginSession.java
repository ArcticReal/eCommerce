package com.skytala.eCommerce.domain.login.relations.userLogin.command.session;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.session.UserLoginSessionUpdated;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.session.UserLoginSession;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateUserLoginSession extends Command {

private UserLoginSession elementToBeUpdated;

public UpdateUserLoginSession(UserLoginSession elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public UserLoginSession getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(UserLoginSession elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("UserLoginSession", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(UserLoginSession.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(UserLoginSession.class);
}
success = false;
}
Event resultingEvent = new UserLoginSessionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
