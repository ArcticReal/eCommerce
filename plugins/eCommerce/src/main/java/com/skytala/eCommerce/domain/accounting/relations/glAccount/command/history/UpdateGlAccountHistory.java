package com.skytala.eCommerce.domain.accounting.relations.glAccount.command.history;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.event.history.GlAccountHistoryUpdated;
import com.skytala.eCommerce.domain.accounting.relations.glAccount.model.history.GlAccountHistory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateGlAccountHistory extends Command {

private GlAccountHistory elementToBeUpdated;

public UpdateGlAccountHistory(GlAccountHistory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public GlAccountHistory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(GlAccountHistory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("GlAccountHistory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(GlAccountHistory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(GlAccountHistory.class);
}
success = false;
}
Event resultingEvent = new GlAccountHistoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
