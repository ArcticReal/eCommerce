package com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.event.FinAccountStatusUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccountStatus.model.FinAccountStatus;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFinAccountStatus extends Command {

private FinAccountStatus elementToBeUpdated;

public UpdateFinAccountStatus(FinAccountStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FinAccountStatus getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FinAccountStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FinAccountStatus", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FinAccountStatus.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FinAccountStatus.class);
}
success = false;
}
Event resultingEvent = new FinAccountStatusUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
