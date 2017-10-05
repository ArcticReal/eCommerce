package com.skytala.eCommerce.domain.webAnalyticsType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.webAnalyticsType.event.WebAnalyticsTypeAdded;
import com.skytala.eCommerce.domain.webAnalyticsType.mapper.WebAnalyticsTypeMapper;
import com.skytala.eCommerce.domain.webAnalyticsType.model.WebAnalyticsType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWebAnalyticsType extends Command {

private WebAnalyticsType elementToBeAdded;
public AddWebAnalyticsType(WebAnalyticsType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WebAnalyticsType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setWebAnalyticsTypeId(delegator.getNextSeqId("WebAnalyticsType"));
GenericValue newValue = delegator.makeValue("WebAnalyticsType", elementToBeAdded.mapAttributeField());
addedElement = WebAnalyticsTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WebAnalyticsTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
