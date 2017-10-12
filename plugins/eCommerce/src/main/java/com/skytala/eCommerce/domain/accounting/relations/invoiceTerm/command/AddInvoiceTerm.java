package com.skytala.eCommerce.domain.accounting.relations.invoiceTerm.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTerm.event.InvoiceTermAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTerm.mapper.InvoiceTermMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoiceTerm.model.InvoiceTerm;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceTerm extends Command {

private InvoiceTerm elementToBeAdded;
public AddInvoiceTerm(InvoiceTerm elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceTerm addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInvoiceTermId(delegator.getNextSeqId("InvoiceTerm"));
GenericValue newValue = delegator.makeValue("InvoiceTerm", elementToBeAdded.mapAttributeField());
addedElement = InvoiceTermMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceTermAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
