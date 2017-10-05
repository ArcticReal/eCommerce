package com.skytala.eCommerce.domain.invoiceItemType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.invoiceItemType.event.InvoiceItemTypeAdded;
import com.skytala.eCommerce.domain.invoiceItemType.mapper.InvoiceItemTypeMapper;
import com.skytala.eCommerce.domain.invoiceItemType.model.InvoiceItemType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceItemType extends Command {

private InvoiceItemType elementToBeAdded;
public AddInvoiceItemType(InvoiceItemType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceItemType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInvoiceItemTypeId(delegator.getNextSeqId("InvoiceItemType"));
GenericValue newValue = delegator.makeValue("InvoiceItemType", elementToBeAdded.mapAttributeField());
addedElement = InvoiceItemTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceItemTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
