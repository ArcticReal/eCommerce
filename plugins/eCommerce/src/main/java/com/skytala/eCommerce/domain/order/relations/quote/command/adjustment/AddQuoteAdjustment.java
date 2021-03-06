package com.skytala.eCommerce.domain.order.relations.quote.command.adjustment;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quote.event.adjustment.QuoteAdjustmentAdded;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.adjustment.QuoteAdjustmentMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.adjustment.QuoteAdjustment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddQuoteAdjustment extends Command {

private QuoteAdjustment elementToBeAdded;
public AddQuoteAdjustment(QuoteAdjustment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

QuoteAdjustment addedElement = null;
boolean success = false;
try {
elementToBeAdded.setQuoteAdjustmentId(delegator.getNextSeqId("QuoteAdjustment"));
GenericValue newValue = delegator.makeValue("QuoteAdjustment", elementToBeAdded.mapAttributeField());
addedElement = QuoteAdjustmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new QuoteAdjustmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
