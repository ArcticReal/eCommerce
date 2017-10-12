package com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.event.ShipmentReceiptUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentReceipt.model.ShipmentReceipt;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentReceipt extends Command {

private ShipmentReceipt elementToBeUpdated;

public UpdateShipmentReceipt(ShipmentReceipt elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentReceipt getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentReceipt elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentReceipt", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentReceipt.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentReceipt.class);
}
success = false;
}
Event resultingEvent = new ShipmentReceiptUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
