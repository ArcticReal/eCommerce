package com.skytala.eCommerce.domain.order.relations.custRequestParty.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.order.relations.custRequestParty.event.CustRequestPartyAdded;
import com.skytala.eCommerce.domain.order.relations.custRequestParty.mapper.CustRequestPartyMapper;
import com.skytala.eCommerce.domain.order.relations.custRequestParty.model.CustRequestParty;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCustRequestParty extends Command {

private CustRequestParty elementToBeAdded;
public AddCustRequestParty(CustRequestParty elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CustRequestParty addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CustRequestParty", elementToBeAdded.mapAttributeField());
addedElement = CustRequestPartyMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CustRequestPartyAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
