package com.skytala.eCommerce.domain.shipment.relations.shipmentCostEstimate.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipmentCostEstimate.event.ShipmentCostEstimateUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentCostEstimate.model.ShipmentCostEstimate;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentCostEstimate extends Command {

private ShipmentCostEstimate elementToBeUpdated;

public UpdateShipmentCostEstimate(ShipmentCostEstimate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentCostEstimate getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentCostEstimate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentCostEstimate", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentCostEstimate.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentCostEstimate.class);
}
success = false;
}
Event resultingEvent = new ShipmentCostEstimateUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
