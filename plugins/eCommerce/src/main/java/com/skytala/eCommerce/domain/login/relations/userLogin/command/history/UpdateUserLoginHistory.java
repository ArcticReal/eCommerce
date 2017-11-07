package com.skytala.eCommerce.domain.login.relations.userLogin.command.history;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.history.UserLoginHistoryUpdated;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.history.UserLoginHistory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateUserLoginHistory extends Command {

private UserLoginHistory elementToBeUpdated;

public UpdateUserLoginHistory(UserLoginHistory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public UserLoginHistory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(UserLoginHistory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("UserLoginHistory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(UserLoginHistory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(UserLoginHistory.class);
}
success = false;
}
Event resultingEvent = new UserLoginHistoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
