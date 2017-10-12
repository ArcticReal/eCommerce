package com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.event.DataResourceMetaDataAdded;
import com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.mapper.DataResourceMetaDataMapper;
import com.skytala.eCommerce.domain.content.relations.dataResourceMetaData.model.DataResourceMetaData;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDataResourceMetaData extends Command {

private DataResourceMetaData elementToBeAdded;
public AddDataResourceMetaData(DataResourceMetaData elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DataResourceMetaData addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("DataResourceMetaData", elementToBeAdded.mapAttributeField());
addedElement = DataResourceMetaDataMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DataResourceMetaDataAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
