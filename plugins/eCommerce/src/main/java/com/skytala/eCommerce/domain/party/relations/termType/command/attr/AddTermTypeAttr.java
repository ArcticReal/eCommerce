package com.skytala.eCommerce.domain.party.relations.termType.command.attr;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.termType.event.attr.TermTypeAttrAdded;
import com.skytala.eCommerce.domain.party.relations.termType.mapper.attr.TermTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.termType.model.attr.TermTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddTermTypeAttr extends Command {

private TermTypeAttr elementToBeAdded;
public AddTermTypeAttr(TermTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

TermTypeAttr addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAttrName(delegator.getNextSeqId("TermTypeAttr"));
GenericValue newValue = delegator.makeValue("TermTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = TermTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new TermTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
