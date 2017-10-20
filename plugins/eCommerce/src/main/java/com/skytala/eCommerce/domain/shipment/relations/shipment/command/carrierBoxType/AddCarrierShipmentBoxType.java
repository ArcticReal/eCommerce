package com.skytala.eCommerce.domain.shipment.relations.shipment.command.carrierBoxType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierBoxType.CarrierShipmentBoxTypeAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.carrierBoxType.CarrierShipmentBoxTypeMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierBoxType.CarrierShipmentBoxType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCarrierShipmentBoxType extends Command {

private CarrierShipmentBoxType elementToBeAdded;
public AddCarrierShipmentBoxType(CarrierShipmentBoxType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CarrierShipmentBoxType addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CarrierShipmentBoxType", elementToBeAdded.mapAttributeField());
addedElement = CarrierShipmentBoxTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CarrierShipmentBoxTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
