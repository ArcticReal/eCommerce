package com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.event.ShipmentItemBillingAdded;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.mapper.ShipmentItemBillingMapper;
import com.skytala.eCommerce.domain.shipment.relations.shipmentItemBilling.model.ShipmentItemBilling;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShipmentItemBilling extends Command {

private ShipmentItemBilling elementToBeAdded;
public AddShipmentItemBilling(ShipmentItemBilling elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShipmentItemBilling addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShipmentItemBilling", elementToBeAdded.mapAttributeField());
addedElement = ShipmentItemBillingMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShipmentItemBillingAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
