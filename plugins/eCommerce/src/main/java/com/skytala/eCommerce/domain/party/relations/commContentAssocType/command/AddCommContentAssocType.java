package com.skytala.eCommerce.domain.party.relations.commContentAssocType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.event.CommContentAssocTypeAdded;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.mapper.CommContentAssocTypeMapper;
import com.skytala.eCommerce.domain.party.relations.commContentAssocType.model.CommContentAssocType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCommContentAssocType extends Command {

private CommContentAssocType elementToBeAdded;
public AddCommContentAssocType(CommContentAssocType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CommContentAssocType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCommContentAssocTypeId(delegator.getNextSeqId("CommContentAssocType"));
GenericValue newValue = delegator.makeValue("CommContentAssocType", elementToBeAdded.mapAttributeField());
addedElement = CommContentAssocTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CommContentAssocTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
