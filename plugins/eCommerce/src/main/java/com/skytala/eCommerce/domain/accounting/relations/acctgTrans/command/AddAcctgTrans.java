package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.AcctgTransAdded;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.AcctgTransMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.AcctgTrans;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAcctgTrans extends Command {

private AcctgTrans elementToBeAdded;
public AddAcctgTrans(AcctgTrans elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AcctgTrans addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAcctgTransId(delegator.getNextSeqId("AcctgTrans"));
GenericValue newValue = delegator.makeValue("AcctgTrans", elementToBeAdded.mapAttributeField());
addedElement = AcctgTransMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AcctgTransAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
