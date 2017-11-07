package com.skytala.eCommerce.domain.login.relations.userLogin.command.history;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.history.UserLoginHistoryAdded;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.history.UserLoginHistoryMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.history.UserLoginHistory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddUserLoginHistory extends Command {

private UserLoginHistory elementToBeAdded;
public AddUserLoginHistory(UserLoginHistory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

UserLoginHistory addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("UserLoginHistory", elementToBeAdded.mapAttributeField());
addedElement = UserLoginHistoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new UserLoginHistoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
