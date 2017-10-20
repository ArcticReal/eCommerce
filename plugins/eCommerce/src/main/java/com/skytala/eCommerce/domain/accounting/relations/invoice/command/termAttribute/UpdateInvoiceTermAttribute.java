package com.skytala.eCommerce.domain.accounting.relations.invoice.command.termAttribute;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.termAttribute.InvoiceTermAttributeUpdated;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.termAttribute.InvoiceTermAttribute;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateInvoiceTermAttribute extends Command {

private InvoiceTermAttribute elementToBeUpdated;

public UpdateInvoiceTermAttribute(InvoiceTermAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public InvoiceTermAttribute getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(InvoiceTermAttribute elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("InvoiceTermAttribute", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(InvoiceTermAttribute.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(InvoiceTermAttribute.class);
}
success = false;
}
Event resultingEvent = new InvoiceTermAttributeUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
