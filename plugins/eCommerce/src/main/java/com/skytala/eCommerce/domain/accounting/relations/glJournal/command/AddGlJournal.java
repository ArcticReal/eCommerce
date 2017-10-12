package com.skytala.eCommerce.domain.accounting.relations.glJournal.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.glJournal.event.GlJournalAdded;
import com.skytala.eCommerce.domain.accounting.relations.glJournal.mapper.GlJournalMapper;
import com.skytala.eCommerce.domain.accounting.relations.glJournal.model.GlJournal;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddGlJournal extends Command {

private GlJournal elementToBeAdded;
public AddGlJournal(GlJournal elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

GlJournal addedElement = null;
boolean success = false;
try {
elementToBeAdded.setGlJournalId(delegator.getNextSeqId("GlJournal"));
GenericValue newValue = delegator.makeValue("GlJournal", elementToBeAdded.mapAttributeField());
addedElement = GlJournalMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new GlJournalAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
