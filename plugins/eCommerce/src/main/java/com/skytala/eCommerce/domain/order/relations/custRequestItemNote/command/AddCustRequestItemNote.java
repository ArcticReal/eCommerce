package com.skytala.eCommerce.domain.order.relations.custRequestItemNote.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.event.CustRequestItemNoteAdded;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.mapper.CustRequestItemNoteMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestItemNote.model.CustRequestItemNote;
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
