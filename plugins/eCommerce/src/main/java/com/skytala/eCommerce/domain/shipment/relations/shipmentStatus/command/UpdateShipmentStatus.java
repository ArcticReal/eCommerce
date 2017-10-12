package com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.event.ShipmentStatusUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentStatus.model.ShipmentStatus;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentStatus extends Command {

private ShipmentStatus elementToBeUpdated;

public UpdateShipmentStatus(ShipmentStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentStatus getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentStatus elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentStatus", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentStatus.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentStatus.class);
}
success = false;
}
Event resultingEvent = new ShipmentStatusUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
