package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.event.ShipmentGatewayUspsUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayUsps.model.ShipmentGatewayUsps;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentGatewayUsps extends Command {

private ShipmentGatewayUsps elementToBeUpdated;

public UpdateShipmentGatewayUsps(ShipmentGatewayUsps elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentGatewayUsps getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentGatewayUsps elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentGatewayUsps", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentGatewayUsps.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentGatewayUsps.class);
}
success = false;
}
Event resultingEvent = new ShipmentGatewayUspsUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
