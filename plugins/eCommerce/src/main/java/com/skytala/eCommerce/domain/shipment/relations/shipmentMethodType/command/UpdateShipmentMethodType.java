package com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.event.ShipmentMethodTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipmentMethodType.model.ShipmentMethodType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentMethodType extends Command {

private ShipmentMethodType elementToBeUpdated;

public UpdateShipmentMethodType(ShipmentMethodType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentMethodType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentMethodType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentMethodType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentMethodType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentMethodType.class);
}
success = false;
}
Event resultingEvent = new ShipmentMethodTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
