package com.skytala.eCommerce.domain.shipmentBoxType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipmentBoxType.event.ShipmentBoxTypeUpdated;
import com.skytala.eCommerce.domain.shipmentBoxType.model.ShipmentBoxType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShipmentBoxType extends Command {

private ShipmentBoxType elementToBeUpdated;

public UpdateShipmentBoxType(ShipmentBoxType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShipmentBoxType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShipmentBoxType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShipmentBoxType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShipmentBoxType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShipmentBoxType.class);
}
success = false;
}
Event resultingEvent = new ShipmentBoxTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
