package com.skytala.eCommerce.domain.login.relations.securityGroup.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.login.relations.securityGroup.event.SecurityGroupUpdated;
import com.skytala.eCommerce.domain.login.relations.securityGroup.model.SecurityGroup;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSecurityGroup extends Command {

private SecurityGroup elementToBeUpdated;

public UpdateSecurityGroup(SecurityGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SecurityGroup getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SecurityGroup elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SecurityGroup", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SecurityGroup.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SecurityGroup.class);
}
success = false;
}
Event resultingEvent = new SecurityGroupUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
