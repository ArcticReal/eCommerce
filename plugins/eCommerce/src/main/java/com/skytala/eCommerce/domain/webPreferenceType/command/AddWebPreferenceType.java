package com.skytala.eCommerce.domain.webPreferenceType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.webPreferenceType.event.WebPreferenceTypeAdded;
import com.skytala.eCommerce.domain.webPreferenceType.mapper.WebPreferenceTypeMapper;
import com.skytala.eCommerce.domain.webPreferenceType.model.WebPreferenceType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWebPreferenceType extends Command {

private WebPreferenceType elementToBeAdded;
public AddWebPreferenceType(WebPreferenceType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WebPreferenceType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setWebPreferenceTypeId(delegator.getNextSeqId("WebPreferenceType"));
GenericValue newValue = delegator.makeValue("WebPreferenceType", elementToBeAdded.mapAttributeField());
addedElement = WebPreferenceTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WebPreferenceTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
