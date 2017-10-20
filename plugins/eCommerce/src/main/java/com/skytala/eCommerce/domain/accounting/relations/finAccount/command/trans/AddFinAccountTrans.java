package com.skytala.eCommerce.domain.accounting.relations.finAccount.command.trans;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.event.trans.FinAccountTransAdded;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.mapper.trans.FinAccountTransMapper;
import com.skytala.eCommerce.domain.accounting.relations.finAccount.model.trans.FinAccountTrans;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFinAccountTrans extends Command {

private FinAccountTrans elementToBeAdded;
public AddFinAccountTrans(FinAccountTrans elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FinAccountTrans addedElement = null;
boolean success = false;
try {
elementToBeAdded.setFinAccountTransId(delegator.getNextSeqId("FinAccountTrans"));
GenericValue newValue = delegator.makeValue("FinAccountTrans", elementToBeAdded.mapAttributeField());
addedElement = FinAccountTransMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FinAccountTransAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
