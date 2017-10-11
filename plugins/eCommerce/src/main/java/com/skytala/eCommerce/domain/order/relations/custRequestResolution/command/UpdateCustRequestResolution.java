package com.skytala.eCommerce.domain.order.relations.custRequestResolution.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.custRequestResolution.event.CustRequestResolutionUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequestResolution.model.CustRequestResolution;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCustRequestResolution extends Command {

private CustRequestResolution elementToBeUpdated;

public UpdateCustRequestResolution(CustRequestResolution elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CustRequestResolution getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CustRequestResolution elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CustRequestResolution", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CustRequestResolution.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CustRequestResolution.class);
}
success = false;
}
Event resultingEvent = new CustRequestResolutionUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
