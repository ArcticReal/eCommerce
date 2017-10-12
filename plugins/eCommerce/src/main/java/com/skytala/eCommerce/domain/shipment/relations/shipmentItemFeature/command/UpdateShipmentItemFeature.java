package com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.event.ShipmentItemFeatureUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemFeature.model.ShipmentItemFeature;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentItemFeature extends Command {

private ShipmentItemFeature elementToBeUpdated;

public UpdateShipmentItemFeature(ShipmentItemFeature elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentItemFeature getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentItemFeature elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentItemFeature", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentItemFeature.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentItemFeature.class);
}
success = false;
}
Event resultingEvent = new ShipmentItemFeatureUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
