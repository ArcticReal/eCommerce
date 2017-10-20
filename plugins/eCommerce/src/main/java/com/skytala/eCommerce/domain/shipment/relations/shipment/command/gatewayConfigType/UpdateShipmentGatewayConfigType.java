package com.skytala.eCommerce.domain.shipment.relations.shipment.command.gatewayConfigType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.gatewayConfigType.ShipmentGatewayConfigTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.gatewayConfigType.ShipmentGatewayConfigType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentGatewayConfigType extends Command {

private ShipmentGatewayConfigType elementToBeUpdated;

public UpdateShipmentGatewayConfigType(ShipmentGatewayConfigType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentGatewayConfigType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentGatewayConfigType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentGatewayConfigType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentGatewayConfigType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentGatewayConfigType.class);
}
success = false;
}
Event resultingEvent = new ShipmentGatewayConfigTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
