package com.skytala.eCommerce.domain.shipment.relations.shipment.command.packageRouteSeg;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.packageRouteSeg.ShipmentPackageRouteSegUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.packageRouteSeg.ShipmentPackageRouteSeg;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentPackageRouteSeg extends Command {

private ShipmentPackageRouteSeg elementToBeUpdated;

public UpdateShipmentPackageRouteSeg(ShipmentPackageRouteSeg elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentPackageRouteSeg getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentPackageRouteSeg elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentPackageRouteSeg", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentPackageRouteSeg.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentPackageRouteSeg.class);
}
success = false;
}
Event resultingEvent = new ShipmentPackageRouteSegUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
