package com.skytala.eCommerce.domain.content.relations.content.command.keyword;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.event.keyword.ContentKeywordAdded;
import com.skytala.eCommerce.domain.content.relations.content.mapper.keyword.ContentKeywordMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.keyword.ContentKeyword;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentKeyword extends Command {

private ContentKeyword elementToBeAdded;
public AddContentKeyword(ContentKeyword elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentKeyword addedElement = null;
boolean success = false;
try {
elementToBeAdded.setKeyword(delegator.getNextSeqId("ContentKeyword"));
GenericValue newValue = delegator.makeValue("ContentKeyword", elementToBeAdded.mapAttributeField());
addedElement = ContentKeywordMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentKeywordAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
