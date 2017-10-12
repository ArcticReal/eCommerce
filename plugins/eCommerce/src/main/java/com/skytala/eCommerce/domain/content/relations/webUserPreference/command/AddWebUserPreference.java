package com.skytala.eCommerce.domain.content.relations.webUserPreference.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.event.WebUserPreferenceAdded;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.mapper.WebUserPreferenceMapper;
import com.skytala.eCommerce.domain.content.relations.webUserPreference.model.WebUserPreference;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWebUserPreference extends Command {

private WebUserPreference elementToBeAdded;
public AddWebUserPreference(WebUserPreference elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WebUserPreference addedElement = null;
boolean success = false;
try {
elementToBeAdded.setVisitId(delegator.getNextSeqId("WebUserPreference"));
GenericValue newValue = delegator.makeValue("WebUserPreference", elementToBeAdded.mapAttributeField());
addedElement = WebUserPreferenceMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WebUserPreferenceAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
