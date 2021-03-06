package com.skytala.eCommerce.domain.humanres.relations.payHistory.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.event.PayHistoryUpdated;
import com.skytala.eCommerce.domain.humanres.relations.payHistory.model.PayHistory;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdatePayHistory extends Command {

private PayHistory elementToBeUpdated;

public UpdatePayHistory(PayHistory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public PayHistory getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(PayHistory elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("PayHistory", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(PayHistory.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(PayHistory.class);
}
success = false;
}
Event resultingEvent = new PayHistoryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
