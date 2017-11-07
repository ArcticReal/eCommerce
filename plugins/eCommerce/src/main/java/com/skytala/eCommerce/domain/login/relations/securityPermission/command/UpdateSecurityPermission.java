package com.skytala.eCommerce.domain.login.relations.securityPermission.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.login.relations.securityPermission.event.SecurityPermissionUpdated;
import com.skytala.eCommerce.domain.login.relations.securityPermission.model.SecurityPermission;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateSecurityPermission extends Command {

private SecurityPermission elementToBeUpdated;

public UpdateSecurityPermission(SecurityPermission elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public SecurityPermission getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(SecurityPermission elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("SecurityPermission", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(SecurityPermission.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(SecurityPermission.class);
}
success = false;
}
Event resultingEvent = new SecurityPermissionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
