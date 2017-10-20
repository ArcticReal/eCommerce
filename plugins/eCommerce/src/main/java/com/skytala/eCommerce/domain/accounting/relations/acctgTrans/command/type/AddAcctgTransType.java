package com.skytala.eCommerce.domain.accounting.relations.acctgTrans.command.type;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.event.type.AcctgTransTypeAdded;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.mapper.type.AcctgTransTypeMapper;
import com.skytala.eCommerce.domain.accounting.relations.acctgTrans.model.type.AcctgTransType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAcctgTransType extends Command {

private AcctgTransType elementToBeAdded;
public AddAcctgTransType(AcctgTransType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AcctgTransType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAcctgTransTypeId(delegator.getNextSeqId("AcctgTransType"));
GenericValue newValue = delegator.makeValue("AcctgTransType", elementToBeAdded.mapAttributeField());
addedElement = AcctgTransTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AcctgTransTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
