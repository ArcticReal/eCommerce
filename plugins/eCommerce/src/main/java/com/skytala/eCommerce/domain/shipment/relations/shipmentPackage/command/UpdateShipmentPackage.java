package com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.event.ShipmentPackageUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentPackage.model.ShipmentPackage;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentPackage extends Command {

private ShipmentPackage elementToBeUpdated;

public UpdateShipmentPackage(ShipmentPackage elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentPackage getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentPackage elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentPackage", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentPackage.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentPackage.class);
}
success = false;
}
Event resultingEvent = new ShipmentPackageUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
