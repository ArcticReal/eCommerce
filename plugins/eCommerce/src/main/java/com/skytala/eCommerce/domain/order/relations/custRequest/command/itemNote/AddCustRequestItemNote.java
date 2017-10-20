package com.skytala.eCommerce.domain.order.relations.custRequest.command.itemNote;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequest.event.itemNote.CustRequestItemNoteAdded;
import com.skytala.eCommerce.domain.order.relations.custRequest.mapper.itemNote.CustRequestItemNoteMapper;
import com.skytala.eCommerce.domain.order.relations.custRequest.model.itemNote.CustRequestItemNote;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestItemNote extends Command {

private CustRequestItemNote elementToBeAdded;
public AddCustRequestItemNote(CustRequestItemNote elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestItemNote addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCustRequestItemSeqId(delegator.getNextSeqId("CustRequestItemNote"));
GenericValue newValue = delegator.makeValue("CustRequestItemNote", elementToBeAdded.mapAttributeField());
addedElement = CustRequestItemNoteMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestItemNoteAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
