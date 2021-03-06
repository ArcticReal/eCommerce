package com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemAssocType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemAssocType.InvoiceItemAssocTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemAssocType.InvoiceItemAssocTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemAssocType.InvoiceItemAssocType;
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
