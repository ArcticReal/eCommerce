package com.skytala.eCommerce.domain.order.relations.quote.command.item;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.order.relations.quote.event.item.QuoteItemUpdated;
import com.skytala.eCommerce.domain.order.relations.quote.model.item.QuoteItem;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateQuoteItem extends Command {

private QuoteItem elementToBeUpdated;

public UpdateQuoteItem(QuoteItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public QuoteItem getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(QuoteItem elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("QuoteItem", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(QuoteItem.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(QuoteItem.class);
}
success = false;
}
Event resultingEvent = new QuoteItemUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
