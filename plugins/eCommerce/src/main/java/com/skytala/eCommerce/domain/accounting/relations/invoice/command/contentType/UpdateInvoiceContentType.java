package com.skytala.eCommerce.domain.accounting.relations.invoice.command.contentType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contentType.InvoiceContentTypeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.contentType.InvoiceContentType;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoiceContentType extends Command {

private InvoiceContentType elementToBeUpdated;

public UpdateInvoiceContentType(InvoiceContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InvoiceContentType getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InvoiceContentType elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InvoiceContentType", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InvoiceContentType.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InvoiceContentType.class);
}
success = false;
}
Event resultingEvent = new InvoiceContentTypeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
