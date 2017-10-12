package com.skytala.eCommerce.domain.shipment.relations.shippingDocument.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.event.ShippingDocumentAdded;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.mapper.ShippingDocumentMapper;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.model.ShippingDocument;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddShippingDocument extends Command {

private ShippingDocument elementToBeAdded;
public AddShippingDocument(ShippingDocument elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ShippingDocument addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ShippingDocument", elementToBeAdded.mapAttributeField());
addedElement = ShippingDocumentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ShippingDocumentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
