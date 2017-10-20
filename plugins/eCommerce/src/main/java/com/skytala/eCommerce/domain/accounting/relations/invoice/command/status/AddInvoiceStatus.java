package com.skytala.eCommerce.domain.accounting.relations.invoice.command.status;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.status.InvoiceStatusAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.status.InvoiceStatusMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.status.InvoiceStatus;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceStatus extends Command {

private InvoiceStatus elementToBeAdded;
public AddInvoiceStatus(InvoiceStatus elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceStatus addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("InvoiceStatus", elementToBeAdded.mapAttributeField());
addedElement = InvoiceStatusMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceStatusAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
