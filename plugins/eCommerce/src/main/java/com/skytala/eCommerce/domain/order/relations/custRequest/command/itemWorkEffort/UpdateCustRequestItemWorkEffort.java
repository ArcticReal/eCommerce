package com.skytala.eCommerce.domain.order.relations.custRequest.command.itemWorkEffort;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemWorkEffort.CustRequestItemWorkEffortUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.itemWorkEffort.CustRequestItemWorkEffort;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCustRequestItemWorkEffort extends Command {

private CustRequestItemWorkEffort elementToBeUpdated;

public UpdateCustRequestItemWorkEffort(CustRequestItemWorkEffort elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CustRequestItemWorkEffort getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CustRequestItemWorkEffort elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CustRequestItemWorkEffort", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CustRequestItemWorkEffort.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CustRequestItemWorkEffort.class);
}
success = false;
}
Event resultingEvent = new CustRequestItemWorkEffortUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
