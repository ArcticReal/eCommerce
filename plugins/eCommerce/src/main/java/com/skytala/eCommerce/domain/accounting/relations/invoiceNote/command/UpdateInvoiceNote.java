package com.skytala.eCommerce.domain.accounting.relations.invoiceNote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.event.InvoiceNoteUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.model.InvoiceNote;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoiceNote extends Command {

private InvoiceNote elementToBeUpdated;

public UpdateInvoiceNote(InvoiceNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InvoiceNote getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InvoiceNote elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InvoiceNote", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InvoiceNote.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InvoiceNote.class);
}
success = false;
}
Event resultingEvent = new InvoiceNoteUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
