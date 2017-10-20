package com.skytala.eCommerce.domain.shipment.relations.shipment.command.carrierMethod;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipment.event.carrierMethod.CarrierShipmentMethodAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipment.mapper.carrierMethod.CarrierShipmentMethodMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipment.model.carrierMethod.CarrierShipmentMethod;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCarrierShipmentMethod extends Command {

private CarrierShipmentMethod elementToBeAdded;
public AddCarrierShipmentMethod(CarrierShipmentMethod elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CarrierShipmentMethod addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("CarrierShipmentMethod"));
GenericValue newValue = delegator.makeValue("CarrierShipmentMethod", elementToBeAdded.mapAttributeField());
addedElement = CarrierShipmentMethodMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CarrierShipmentMethodAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
