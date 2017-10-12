package com.skytala.eCommerce.domain.accounting.relations.finAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.FinAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.FinAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFinAccount extends Command {

private FinAccount elementToBeUpdated;

public UpdateFinAccount(FinAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FinAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FinAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FinAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FinAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FinAccount.class);
}
success = false;
}
Event resultingEvent = new FinAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
