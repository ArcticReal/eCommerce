package com.skytala.eCommerce.domain.shipment.relations.shipment.command.contactMech;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.contactMech.ShipmentContactMechUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.contactMech.ShipmentContactMech;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentContactMech extends Command {

private ShipmentContactMech elementToBeUpdated;

public UpdateShipmentContactMech(ShipmentContactMech elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentContactMech getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentContactMech elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentContactMech", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentContactMech.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentContactMech.class);
}
success = false;
}
Event resultingEvent = new ShipmentContactMechUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
