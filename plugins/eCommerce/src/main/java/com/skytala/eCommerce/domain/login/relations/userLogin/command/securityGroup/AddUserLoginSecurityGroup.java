package com.skytala.eCommerce.domain.login.relations.userLogin.command.securityGroup;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.userLogin.event.securityGroup.UserLoginSecurityGroupAdded;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.securityGroup.UserLoginSecurityGroupMapper;
import com.skytala.eCommerce.domain.login.relations.userLogin.model.securityGroup.UserLoginSecurityGroup;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddUserLoginSecurityGroup extends Command {

private UserLoginSecurityGroup elementToBeAdded;
public AddUserLoginSecurityGroup(UserLoginSecurityGroup elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

UserLoginSecurityGroup addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("UserLoginSecurityGroup", elementToBeAdded.mapAttributeField());
addedElement = UserLoginSecurityGroupMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new UserLoginSecurityGroupAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
