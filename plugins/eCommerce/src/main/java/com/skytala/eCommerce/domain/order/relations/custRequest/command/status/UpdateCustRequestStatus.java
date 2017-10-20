package com.skytala.eCommerce.domain.order.relations.custRequest.command.status;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.status.CustRequestStatusUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.status.CustRequestStatus;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCustRequestStatus extends Command {

private CustRequestStatus elementToBeUpdated;

public UpdateCustRequestStatus(CustRequestStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CustRequestStatus getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CustRequestStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CustRequestStatus", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CustRequestStatus.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CustRequestStatus.class);
}
success = false;
}
Event resultingEvent = new CustRequestStatusUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
