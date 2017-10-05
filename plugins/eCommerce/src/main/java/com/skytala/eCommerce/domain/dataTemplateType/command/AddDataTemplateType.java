package com.skytala.eCommerce.domain.dataTemplateType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.dataTemplateType.event.DataTemplateTypeAdded;
import com.skytala.eCommerce.domain.dataTemplateType.mapper.DataTemplateTypeMapper;
import com.skytala.eCommerce.domain.dataTemplateType.model.DataTemplateType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDataTemplateType extends Command {

private DataTemplateType elementToBeAdded;
public AddDataTemplateType(DataTemplateType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DataTemplateType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setDataTemplateTypeId(delegator.getNextSeqId("DataTemplateType"));
GenericValue newValue = delegator.makeValue("DataTemplateType", elementToBeAdded.mapAttributeField());
addedElement = DataTemplateTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DataTemplateTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
