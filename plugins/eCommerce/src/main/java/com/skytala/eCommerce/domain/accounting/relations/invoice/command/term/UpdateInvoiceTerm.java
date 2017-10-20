package com.skytala.eCommerce.domain.accounting.relations.invoice.command.term;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.term.InvoiceTermUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.term.InvoiceTerm;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoiceTerm extends Command {

private InvoiceTerm elementToBeUpdated;

public UpdateInvoiceTerm(InvoiceTerm elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InvoiceTerm getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InvoiceTerm elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InvoiceTerm", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InvoiceTerm.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InvoiceTerm.class);
}
success = false;
}
Event resultingEvent = new InvoiceTermUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}