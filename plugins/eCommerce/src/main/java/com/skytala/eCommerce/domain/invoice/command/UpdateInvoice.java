package com.skytala.eCommerce.domain.invoice.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.invoice.event.InvoiceUpdated;
import com.skytala.eCommerce.domain.invoice.model.Invoice;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoice extends Command {

private Invoice elementToBeUpdated;

public UpdateInvoice(Invoice elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public Invoice getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(Invoice elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("Invoice", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(Invoice.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(Invoice.class);
}
success = false;
}
Event resultingEvent = new InvoiceUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
