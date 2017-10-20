package com.skytala.eCommerce.domain.accounting.relations.invoice.command.contentType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.contentType.InvoiceContentTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.contentType.InvoiceContentTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.contentType.InvoiceContentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceContentType extends Command {

private InvoiceContentType elementToBeAdded;
public AddInvoiceContentType(InvoiceContentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceContentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInvoiceContentTypeId(delegator.getNextSeqId("InvoiceContentType"));
GenericValue newValue = delegator.makeValue("InvoiceContentType", elementToBeAdded.mapAttributeField());
addedElement = InvoiceContentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceContentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
