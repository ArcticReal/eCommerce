package com.skytala.eCommerce.domain.accounting.relations.invoiceNote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.event.InvoiceNoteAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.mapper.InvoiceNoteMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceNote.model.InvoiceNote;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceNote extends Command {

private InvoiceNote elementToBeAdded;
public AddInvoiceNote(InvoiceNote elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceNote addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InvoiceNote", elementToBeAdded.mapAttributeField());
addedElement = InvoiceNoteMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceNoteAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
