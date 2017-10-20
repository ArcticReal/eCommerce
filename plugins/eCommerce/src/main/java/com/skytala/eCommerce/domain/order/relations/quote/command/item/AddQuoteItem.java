package com.skytala.eCommerce.domain.order.relations.quote.command.item;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quote.event.item.QuoteItemAdded;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.item.QuoteItemMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.item.QuoteItem;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddQuoteItem extends Command {

private QuoteItem elementToBeAdded;
public AddQuoteItem(QuoteItem elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

QuoteItem addedElement = null;
boolean success = false;
try {
elementToBeAdded.setQuoteItemSeqId(delegator.getNextSeqId("QuoteItem"));
GenericValue newValue = delegator.makeValue("QuoteItem", elementToBeAdded.mapAttributeField());
addedElement = QuoteItemMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new QuoteItemAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
