package com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.event.ApplicationSandboxUpdated;
import com.skytala.eCommerce.domain.workeffort.relations.applicationSandbox.model.ApplicationSandbox;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateApplicationSandbox extends Command {

private ApplicationSandbox elementToBeUpdated;

public UpdateApplicationSandbox(ApplicationSandbox elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ApplicationSandbox getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ApplicationSandbox elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ApplicationSandbox", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ApplicationSandbox.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ApplicationSandbox.class);
}
success = false;
}
Event resultingEvent = new ApplicationSandboxUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
