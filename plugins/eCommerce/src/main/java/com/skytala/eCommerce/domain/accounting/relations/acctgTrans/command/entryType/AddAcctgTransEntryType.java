package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.entryType;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.entryType.AcctgTransEntryTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.entryType.AcctgTransEntryTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.entryType.AcctgTransEntryType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAcctgTransEntryType extends Command {

private AcctgTransEntryType elementToBeAdded;
public AddAcctgTransEntryType(AcctgTransEntryType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AcctgTransEntryType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAcctgTransEntryTypeId(delegator.getNextSeqId("AcctgTransEntryType"));
GenericValue newValue = delegator.makeValue("AcctgTransEntryType", elementToBeAdded.mapAttributeField());
addedElement = AcctgTransEntryTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AcctgTransEntryTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
