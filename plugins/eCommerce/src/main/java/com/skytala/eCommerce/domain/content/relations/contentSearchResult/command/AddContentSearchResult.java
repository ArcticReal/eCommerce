package com.skytala.eCommerce.domain.content.relations.contentSearchResult.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.contentSearchResult.event.ContentSearchResultAdded;
import com.skytala.eCommerce.domain.content.relations.contentSearchResult.mapper.ContentSearchResultMapper;
import com.skytala.eCommerce.domain.content.relations.contentSearchResult.model.ContentSearchResult;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentSearchResult extends Command {

private ContentSearchResult elementToBeAdded;
public AddContentSearchResult(ContentSearchResult elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentSearchResult addedElement = null;
boolean success = false;
try {
elementToBeAdded.setContentSearchResultId(delegator.getNextSeqId("ContentSearchResult"));
GenericValue newValue = delegator.makeValue("ContentSearchResult", elementToBeAdded.mapAttributeField());
addedElement = ContentSearchResultMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentSearchResultAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
