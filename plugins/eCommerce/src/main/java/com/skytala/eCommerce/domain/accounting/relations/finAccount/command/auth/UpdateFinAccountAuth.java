package com.skytala.eCommerce.domain.accounting.relations.finAccount.command.auth;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.auth.FinAccountAuthUpdated;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.auth.FinAccountAuth;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateFinAccountAuth extends Command {

private FinAccountAuth elementToBeUpdated;

public UpdateFinAccountAuth(FinAccountAuth elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public FinAccountAuth getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(FinAccountAuth elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("FinAccountAuth", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(FinAccountAuth.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(FinAccountAuth.class);
}
success = false;
}
Event resultingEvent = new FinAccountAuthUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
