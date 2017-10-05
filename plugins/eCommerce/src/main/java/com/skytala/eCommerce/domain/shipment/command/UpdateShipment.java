package com.skytala.eCommerce.domain.shipment.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.event.ShipmentUpdated;
import com.skytala.eCommerce.domain.shipment.model.Shipment;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipment extends Command {

private Shipment elementToBeUpdated;

public UpdateShipment(Shipment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Shipment getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Shipment elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Shipment", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Shipment.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Shipment.class);
}
success = false;
}
Event resultingEvent = new ShipmentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
