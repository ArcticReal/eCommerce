package com.skytala.eCommerce.domain.shipment.relations.delivery.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.delivery.event.DeliveryUpdated;
import com.skytala.eCommerce.domain.shipment.relations.delivery.model.Delivery;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateDelivery extends Command {

private Delivery elementToBeUpdated;

public UpdateDelivery(Delivery elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Delivery getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Delivery elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Delivery", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Delivery.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Delivery.class);
}
success = false;
}
Event resultingEvent = new DeliveryUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
