package com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssocType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssocType.event.InvoiceItemAssocTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssocType.mapper.InvoiceItemAssocTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceItemAssocType.model.InvoiceItemAssocType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceItemAssocType extends Command {

private InvoiceItemAssocType elementToBeAdded;
public AddInvoiceItemAssocType(InvoiceItemAssocType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceItemAssocType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInvoiceItemAssocTypeId(delegator.getNextSeqId("InvoiceItemAssocType"));
GenericValue newValue = delegator.makeValue("InvoiceItemAssocType", elementToBeAdded.mapAttributeField());
addedElement = InvoiceItemAssocTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceItemAssocTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
