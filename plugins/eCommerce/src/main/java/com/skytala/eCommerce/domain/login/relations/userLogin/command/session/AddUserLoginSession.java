package com.skytala.eCommerce.domain.login.relations.userLogin.command.session;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.session.UserLoginSessionAdded;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.session.UserLoginSessionMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.session.UserLoginSession;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddUserLoginSession extends Command {

private UserLoginSession elementToBeAdded;
public AddUserLoginSession(UserLoginSession elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

UserLoginSession addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("UserLoginSession", elementToBeAdded.mapAttributeField());
addedElement = UserLoginSessionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new UserLoginSessionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
