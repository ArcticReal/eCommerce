package com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.event.ShipmentItemBillingUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.model.ShipmentItemBilling;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentItemBilling extends Command {

private ShipmentItemBilling elementToBeUpdated;

public UpdateShipmentItemBilling(ShipmentItemBilling elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentItemBilling getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentItemBilling elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentItemBilling", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentItemBilling.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentItemBilling.class);
}
success = false;
}
Event resultingEvent = new ShipmentItemBillingUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
