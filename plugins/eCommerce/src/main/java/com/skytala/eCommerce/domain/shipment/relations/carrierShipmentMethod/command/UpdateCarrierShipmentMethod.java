package com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.event.CarrierShipmentMethodUpdated;
import com.skytala.eCommerce.domain.shipment.relations.carrierShipmentMethod.model.CarrierShipmentMethod;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateCarrierShipmentMethod extends Command {

private CarrierShipmentMethod elementToBeUpdated;

public UpdateCarrierShipmentMethod(CarrierShipmentMethod elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public CarrierShipmentMethod getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(CarrierShipmentMethod elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("CarrierShipmentMethod", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(CarrierShipmentMethod.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(CarrierShipmentMethod.class);
}
success = false;
}
Event resultingEvent = new CarrierShipmentMethodUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
