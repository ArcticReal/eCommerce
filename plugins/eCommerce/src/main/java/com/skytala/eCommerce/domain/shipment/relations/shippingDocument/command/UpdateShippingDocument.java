package com.skytala.eCommerce.domain.shipment.relations.shippingDocument.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.event.ShippingDocumentUpdated;
import com.skytala.eCommerce.domain.shipment.relations.shippingDocument.model.ShippingDocument;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateShippingDocument extends Command {

private ShippingDocument elementToBeUpdated;

public UpdateShippingDocument(ShippingDocument elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public ShippingDocument getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(ShippingDocument elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("ShippingDocument", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(ShippingDocument.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(ShippingDocument.class);
}
success = false;
}
Event resultingEvent = new ShippingDocumentUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
