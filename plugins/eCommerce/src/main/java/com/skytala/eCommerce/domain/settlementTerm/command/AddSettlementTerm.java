package com.skytala.eCommerce.domain.settlementTerm.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.settlementTerm.event.SettlementTermAdded;
import com.skytala.eCommerce.domain.settlementTerm.mapper.SettlementTermMapper;
import com.skytala.eCommerce.domain.settlementTerm.model.SettlementTerm;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddSettlementTerm extends Command {

private SettlementTerm elementToBeAdded;
public AddSettlementTerm(SettlementTerm elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

SettlementTerm addedElement = null;
boolean success = false;
try {
elementToBeAdded.setSettlementTermId(delegator.getNextSeqId("SettlementTerm"));
GenericValue newValue = delegator.makeValue("SettlementTerm", elementToBeAdded.mapAttributeField());
addedElement = SettlementTermMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new SettlementTermAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
