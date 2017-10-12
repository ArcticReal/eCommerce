package com.skytala.eCommerce.domain.humanres.relations.performanceNote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.event.PerformanceNoteAdded;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.mapper.PerformanceNoteMapper;
import com.skytala.eCommerce.domain.humanres.relations.performanceNote.model.PerformanceNote;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPerformanceNote extends Command {

private PerformanceNote elementToBeAdded;
public AddPerformanceNote(PerformanceNote elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PerformanceNote addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("PerformanceNote"));
GenericValue newValue = delegator.makeValue("PerformanceNote", elementToBeAdded.mapAttributeField());
addedElement = PerformanceNoteMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PerformanceNoteAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
