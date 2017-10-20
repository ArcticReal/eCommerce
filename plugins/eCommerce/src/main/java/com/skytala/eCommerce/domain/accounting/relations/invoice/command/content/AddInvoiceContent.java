package com.skytala.eCommerce.domain.accounting.relations.invoice.command.content;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.content.InvoiceContentAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.content.InvoiceContentMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.content.InvoiceContent;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceContent extends Command {

private InvoiceContent elementToBeAdded;
public AddInvoiceContent(InvoiceContent elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceContent addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InvoiceContent", elementToBeAdded.mapAttributeField());
addedElement = InvoiceContentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceContentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
