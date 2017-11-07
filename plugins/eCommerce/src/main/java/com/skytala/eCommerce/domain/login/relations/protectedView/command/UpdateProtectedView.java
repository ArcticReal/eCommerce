package com.skytala.eCommerce.domain.login.relations.protectedView.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.login.relations.protectedView.event.ProtectedViewUpdated;
import com.skytala.eCommerce.domain.login.relations.protectedView.model.ProtectedView;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateProtectedView extends Command {

private ProtectedView elementToBeUpdated;

public UpdateProtectedView(ProtectedView elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ProtectedView getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ProtectedView elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ProtectedView", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ProtectedView.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ProtectedView.class);
}
success = false;
}
Event resultingEvent = new ProtectedViewUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
