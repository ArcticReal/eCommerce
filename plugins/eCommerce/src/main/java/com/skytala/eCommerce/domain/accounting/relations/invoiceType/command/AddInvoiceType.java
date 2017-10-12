package com.skytala.eCommerce.domain.accounting.relations.invoiceType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceType.event.InvoiceTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceType.mapper.InvoiceTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceType.model.InvoiceType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceType extends Command {

private InvoiceType elementToBeAdded;
public AddInvoiceType(InvoiceType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInvoiceTypeId(delegator.getNextSeqId("InvoiceType"));
GenericValue newValue = delegator.makeValue("InvoiceType", elementToBeAdded.mapAttributeField());
addedElement = InvoiceTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
