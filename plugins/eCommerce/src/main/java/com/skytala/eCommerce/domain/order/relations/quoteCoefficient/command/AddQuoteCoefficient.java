package com.skytala.eCommerce.domain.order.relations.quoteCoefficient.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quoteCoefficient.event.QuoteCoefficientAdded;
import com.skytala.eCommerce.domain.order.relations.quoteCoefficient.mapper.QuoteCoefficientMapper;
import com.skytala.eCommerce.domain.order.relations.quoteCoefficient.model.QuoteCoefficient;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddQuoteCoefficient extends Command {

private QuoteCoefficient elementToBeAdded;
public AddQuoteCoefficient(QuoteCoefficient elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

QuoteCoefficient addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCoeffName(delegator.getNextSeqId("QuoteCoefficient"));
GenericValue newValue = delegator.makeValue("QuoteCoefficient", elementToBeAdded.mapAttributeField());
addedElement = QuoteCoefficientMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new QuoteCoefficientAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
