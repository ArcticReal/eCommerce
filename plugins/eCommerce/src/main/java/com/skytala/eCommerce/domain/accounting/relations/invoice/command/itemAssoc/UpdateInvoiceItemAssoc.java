package com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAssoc;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssoc.InvoiceItemAssocUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAssoc.InvoiceItemAssoc;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoiceItemAssoc extends Command {

private InvoiceItemAssoc elementToBeUpdated;

public UpdateInvoiceItemAssoc(InvoiceItemAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InvoiceItemAssoc getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InvoiceItemAssoc elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InvoiceItemAssoc", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InvoiceItemAssoc.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InvoiceItemAssoc.class);
}
success = false;
}
Event resultingEvent = new InvoiceItemAssocUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
