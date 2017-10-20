package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.entry;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entry.AcctgTransEntryAdded;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.entry.AcctgTransEntryMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entry.AcctgTransEntry;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAcctgTransEntry extends Command {

private AcctgTransEntry elementToBeAdded;
public AddAcctgTransEntry(AcctgTransEntry elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AcctgTransEntry addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAcctgTransEntrySeqId(delegator.getNextSeqId("AcctgTransEntry"));
GenericValue newValue = delegator.makeValue("AcctgTransEntry", elementToBeAdded.mapAttributeField());
addedElement = AcctgTransEntryMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AcctgTransEntryAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
