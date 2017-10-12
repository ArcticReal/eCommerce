package com.skytala.eCommerce.domain.accounting.relations.checkAccount.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.event.CheckAccountUpdated;
import com.skytala.eCommerce.domain.accounting.relations.checkAccount.model.CheckAccount;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCheckAccount extends Command {

private CheckAccount elementToBeUpdated;

public UpdateCheckAccount(CheckAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CheckAccount getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CheckAccount elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CheckAccount", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CheckAccount.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CheckAccount.class);
}
success = false;
}
Event resultingEvent = new CheckAccountUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
