package com.skytala.eCommerce.domain.order.relations.returnItem.command.shipment;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.returnItem.event.shipment.ReturnItemShipmentUpdated;
import com.skytala.eCommerce.domain.order.relations.returnItem.model.shipment.ReturnItemShipment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateReturnItemShipment extends Command {

private ReturnItemShipment elementToBeUpdated;

public UpdateReturnItemShipment(ReturnItemShipment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ReturnItemShipment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ReturnItemShipment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ReturnItemShipment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ReturnItemShipment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ReturnItemShipment.class);
}
success = false;
}
Event resultingEvent = new ReturnItemShipmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
