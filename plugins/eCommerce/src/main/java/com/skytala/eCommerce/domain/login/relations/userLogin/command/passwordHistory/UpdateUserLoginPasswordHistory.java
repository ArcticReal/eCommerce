package com.skytala.eCommerce.domain.login.relations.userLogin.command.passwordHistory;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.passwordHistory.UserLoginPasswordHistoryUpdated;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.passwordHistory.UserLoginPasswordHistory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateUserLoginPasswordHistory extends Command {

private UserLoginPasswordHistory elementToBeUpdated;

public UpdateUserLoginPasswordHistory(UserLoginPasswordHistory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public UserLoginPasswordHistory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(UserLoginPasswordHistory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("UserLoginPasswordHistory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(UserLoginPasswordHistory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(UserLoginPasswordHistory.class);
}
success = false;
}
Event resultingEvent = new UserLoginPasswordHistoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
