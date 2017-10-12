package com.skytala.eCommerce.domain.content.relations.dataResourceRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.dataResourceRole.event.DataResourceRoleAdded;
import com.skytala.eCommerce.domain.content.relations.dataResourceRole.mapper.DataResourceRoleMapper;
import com.skytala.eCommerce.domain.content.relations.dataResourceRole.model.DataResourceRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddDataResourceRole extends Command {

private DataResourceRole elementToBeAdded;
public AddDataResourceRole(DataResourceRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

DataResourceRole addedElement = null;
boolean success = false;
try {
elementToBeAdded.setRoleTypeId(delegator.getNextSeqId("DataResourceRole"));
GenericValue newValue = delegator.makeValue("DataResourceRole", elementToBeAdded.mapAttributeField());
addedElement = DataResourceRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new DataResourceRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
