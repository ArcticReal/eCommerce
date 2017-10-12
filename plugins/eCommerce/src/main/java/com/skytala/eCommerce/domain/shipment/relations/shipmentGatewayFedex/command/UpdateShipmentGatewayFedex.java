package com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.event.ShipmentGatewayFedexUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentGatewayFedex.model.ShipmentGatewayFedex;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentGatewayFedex extends Command {

private ShipmentGatewayFedex elementToBeUpdated;

public UpdateShipmentGatewayFedex(ShipmentGatewayFedex elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentGatewayFedex getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentGatewayFedex elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentGatewayFedex", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentGatewayFedex.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentGatewayFedex.class);
}
success = false;
}
Event resultingEvent = new ShipmentGatewayFedexUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
