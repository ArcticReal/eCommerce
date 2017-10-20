package com.skytala.eCommerce.domain.order.relations.quote.command.note;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.quote.event.note.QuoteNoteAdded;
import com.skytala.eCommerce.domain.order.relations.quote.mapper.note.QuoteNoteMapper;
import com.skytala.eCommerce.domain.order.relations.quote.model.note.QuoteNote;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddQuoteNote extends Command {

private QuoteNote elementToBeAdded;
public AddQuoteNote(QuoteNote elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

QuoteNote addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("QuoteNote", elementToBeAdded.mapAttributeField());
addedElement = QuoteNoteMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new QuoteNoteAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
