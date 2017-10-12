package com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.event.ValueLinkKeyAdded;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.mapper.ValueLinkKeyMapper;
import com.skytala.eCommerce.domain.accounting.relations.valueLinkKey.model.ValueLinkKey;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddValueLinkKey extends Command {

private ValueLinkKey elementToBeAdded;
public AddValueLinkKey(ValueLinkKey elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ValueLinkKey addedElement = null;
boolean success = false;
try {
elementToBeAdded.setMerchantId(delegator.getNextSeqId("ValueLinkKey"));
GenericValue newValue = delegator.makeValue("ValueLinkKey", elementToBeAdded.mapAttributeField());
addedElement = ValueLinkKeyMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ValueLinkKeyAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
