package com.skytala.eCommerce.domain.accounting.relations.invoice.command.contactMech;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contactMech.InvoiceContactMechUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.contactMech.InvoiceContactMech;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoiceContactMech extends Command {

private InvoiceContactMech elementToBeUpdated;

public UpdateInvoiceContactMech(InvoiceContactMech elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InvoiceContactMech getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InvoiceContactMech elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InvoiceContactMech", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InvoiceContactMech.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InvoiceContactMech.class);
}
success = false;
}
Event resultingEvent = new InvoiceContactMechUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
