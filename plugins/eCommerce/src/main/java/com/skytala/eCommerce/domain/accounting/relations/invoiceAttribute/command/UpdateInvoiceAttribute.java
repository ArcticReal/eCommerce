package com.skytala.eCommerce.domain.accounting.relations.invoiceAttribute.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.invoiceAttribute.event.InvoiceAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoiceAttribute.model.InvoiceAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoiceAttribute extends Command {

private InvoiceAttribute elementToBeUpdated;

public UpdateInvoiceAttribute(InvoiceAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InvoiceAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InvoiceAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InvoiceAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InvoiceAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InvoiceAttribute.class);
}
success = false;
}
Event resultingEvent = new InvoiceAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
