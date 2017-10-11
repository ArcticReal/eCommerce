package com.skytala.eCommerce.domain.order.relations.quoteTerm.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quoteTerm.event.QuoteTermAdded;
import com.skytala.eCommerce.domain.order.relations.quoteTerm.mapper.QuoteTermMapper;
import com.skytala.eCommerce.domain.order.relations.quoteTerm.model.QuoteTerm;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddQuoteTerm extends Command {

private QuoteTerm elementToBeAdded;
public AddQuoteTerm(QuoteTerm elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

QuoteTerm addedElement = null;
boolean success = false;
try {
elementToBeAdded.setQuoteItemSeqId(delegator.getNextSeqId("QuoteTerm"));
GenericValue newValue = delegator.makeValue("QuoteTerm", elementToBeAdded.mapAttributeField());
addedElement = QuoteTermMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new QuoteTermAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
