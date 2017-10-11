package com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.event.CustRequestCommEventUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequestCommEvent.model.CustRequestCommEvent;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCustRequestCommEvent extends Command {

private CustRequestCommEvent elementToBeUpdated;

public UpdateCustRequestCommEvent(CustRequestCommEvent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CustRequestCommEvent getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CustRequestCommEvent elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CustRequestCommEvent", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CustRequestCommEvent.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CustRequestCommEvent.class);
}
success = false;
}
Event resultingEvent = new CustRequestCommEventUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
