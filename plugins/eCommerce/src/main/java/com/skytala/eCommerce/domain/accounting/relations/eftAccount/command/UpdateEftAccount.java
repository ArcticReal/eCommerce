package com.skytala.eCommerce.domain.accounting.relations.eftAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.event.EftAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.eftAccount.model.EftAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateEftAccount extends Command {

private EftAccount elementToBeUpdated;

public UpdateEftAccount(EftAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public EftAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(EftAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("EftAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(EftAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(EftAccount.class);
}
success = false;
}
Event resultingEvent = new EftAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
