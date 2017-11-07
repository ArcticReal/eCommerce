package com.skytala.eCommerce.domain.login.relations.userLogin.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.UserLoginAdded;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.UserLoginMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.UserLogin;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddUserLogin extends Command {

private UserLogin elementToBeAdded;
public AddUserLogin(UserLogin elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

UserLogin addedElement = null;
boolean success = false;
try {
elementToBeAdded.setUserLoginId(delegator.getNextSeqId("UserLogin"));
GenericValue newValue = delegator.makeValue("UserLogin", elementToBeAdded.mapAttributeField());
addedElement = UserLoginMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new UserLoginAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
