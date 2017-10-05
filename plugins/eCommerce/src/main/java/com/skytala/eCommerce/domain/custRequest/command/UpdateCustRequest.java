package com.skytala.eCommerce.domain.custRequest.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.custRequest.event.CustRequestUpdated;
import com.skytala.eCommerce.domain.custRequest.model.CustRequest;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCustRequest extends Command {

private CustRequest elementToBeUpdated;

public UpdateCustRequest(CustRequest elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CustRequest getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CustRequest elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CustRequest", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CustRequest.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CustRequest.class);
}
success = false;
}
Event resultingEvent = new CustRequestUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
