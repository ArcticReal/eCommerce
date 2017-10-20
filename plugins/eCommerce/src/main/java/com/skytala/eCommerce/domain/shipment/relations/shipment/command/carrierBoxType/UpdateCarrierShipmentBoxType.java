package com.skytala.eCommerce.domain.shipment.relations.shipment.command.carrierBoxType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierBoxType.CarrierShipmentBoxTypeUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierBoxType.CarrierShipmentBoxType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCarrierShipmentBoxType extends Command {

private CarrierShipmentBoxType elementToBeUpdated;

public UpdateCarrierShipmentBoxType(CarrierShipmentBoxType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CarrierShipmentBoxType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CarrierShipmentBoxType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CarrierShipmentBoxType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CarrierShipmentBoxType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CarrierShipmentBoxType.class);
}
success = false;
}
Event resultingEvent = new CarrierShipmentBoxTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
