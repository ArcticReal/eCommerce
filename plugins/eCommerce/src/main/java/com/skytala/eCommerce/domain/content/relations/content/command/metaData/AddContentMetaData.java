package com.skytala.eCommerce.domain.content.relations.content.command.metaData;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.content.event.metaData.ContentMetaDataAdded;
import com.skytala.eCommerce.domain.content.relations.content.mapper.metaData.ContentMetaDataMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.metaData.ContentMetaData;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddContentMetaData extends Command {

private ContentMetaData elementToBeAdded;
public AddContentMetaData(ContentMetaData elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

ContentMetaData addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("ContentMetaData", elementToBeAdded.mapAttributeField());
addedElement = ContentMetaDataMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new ContentMetaDataAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
