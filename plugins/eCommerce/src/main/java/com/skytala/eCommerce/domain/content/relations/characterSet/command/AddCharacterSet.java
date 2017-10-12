package com.skytala.eCommerce.domain.content.relations.characterSet.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.characterSet.event.CharacterSetAdded;
import com.skytala.eCommerce.domain.content.relations.characterSet.mapper.CharacterSetMapper;
import com.skytala.eCommerce.domain.content.relations.characterSet.model.CharacterSet;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCharacterSet extends Command {

private CharacterSet elementToBeAdded;
public AddCharacterSet(CharacterSet elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CharacterSet addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCharacterSetId(delegator.getNextSeqId("CharacterSet"));
GenericValue newValue = delegator.makeValue("CharacterSet", elementToBeAdded.mapAttributeField());
addedElement = CharacterSetMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CharacterSetAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
