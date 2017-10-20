package com.skytala.eCommerce.domain.order.relations.custRequest.command.attribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.attribute.CustRequestAttributeUpdated;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.attribute.CustRequestAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCustRequestAttribute extends Command {

private CustRequestAttribute elementToBeUpdated;

public UpdateCustRequestAttribute(CustRequestAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CustRequestAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CustRequestAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CustRequestAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CustRequestAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CustRequestAttribute.class);
}
success = false;
}
Event resultingEvent = new CustRequestAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
