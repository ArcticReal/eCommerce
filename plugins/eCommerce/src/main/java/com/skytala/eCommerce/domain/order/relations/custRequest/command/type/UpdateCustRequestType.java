package com.skytala.eCommerce.domain.order.relations.custRequest.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.type.CustRequestTypeUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.type.CustRequestType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCustRequestType extends Command {

private CustRequestType elementToBeUpdated;

public UpdateCustRequestType(CustRequestType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CustRequestType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CustRequestType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CustRequestType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CustRequestType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CustRequestType.class);
}
success = false;
}
Event resultingEvent = new CustRequestTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
