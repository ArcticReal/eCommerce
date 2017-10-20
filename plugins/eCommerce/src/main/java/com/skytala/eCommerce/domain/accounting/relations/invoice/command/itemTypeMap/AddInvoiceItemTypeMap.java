package com.skytala.eCommerce.domain.accounting.relations.invoice.command.itemTypeMap;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.invoice.event.itemTypeMap.InvoiceItemTypeMapAdded;
import com.skytala.eCommerce.domain.accounting.relations.invoice.mapper.itemTypeMap.InvoiceItemTypeMapMapper;
import com.skytala.eCommerce.domain.accounting.relations.invoice.model.itemTypeMap.InvoiceItemTypeMap;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddInvoiceItemTypeMap extends Command {

private InvoiceItemTypeMap elementToBeAdded;
public AddInvoiceItemTypeMap(InvoiceItemTypeMap elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

InvoiceItemTypeMap addedElement = null;
boolean success = false;
try {
elementToBeAdded.setInvoiceItemMapKey(delegator.getNextSeqId("InvoiceItemTypeMap"));
GenericValue newValue = delegator.makeValue("InvoiceItemTypeMap", elementToBeAdded.mapAttributeField());
addedElement = InvoiceItemTypeMapMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new InvoiceItemTypeMapAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
