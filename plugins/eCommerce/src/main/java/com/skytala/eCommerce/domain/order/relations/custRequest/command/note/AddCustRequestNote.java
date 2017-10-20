package com.skytala.eCommerce.domain.order.relations.custRequest.command.note;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.note.CustRequestNoteAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.note.CustRequestNoteMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.note.CustRequestNote;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestNote extends Command {

private CustRequestNote elementToBeAdded;
public AddCustRequestNote(CustRequestNote elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestNote addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CustRequestNote", elementToBeAdded.mapAttributeField());
addedElement = CustRequestNoteMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestNoteAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
