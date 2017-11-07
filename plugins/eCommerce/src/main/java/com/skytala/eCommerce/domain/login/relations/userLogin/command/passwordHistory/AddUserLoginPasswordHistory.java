package com.skytala.eCommerce.domain.login.relations.userLogin.command.passwordHistory;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.passwordHistory.UserLoginPasswordHistoryAdded;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.passwordHistory.UserLoginPasswordHistoryMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.passwordHistory.UserLoginPasswordHistory;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddUserLoginPasswordHistory extends Command {

private UserLoginPasswordHistory elementToBeAdded;
public AddUserLoginPasswordHistory(UserLoginPasswordHistory elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

UserLoginPasswordHistory addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("UserLoginPasswordHistory", elementToBeAdded.mapAttributeField());
addedElement = UserLoginPasswordHistoryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new UserLoginPasswordHistoryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
