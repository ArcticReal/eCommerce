package com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.event.EmploymentAppSourceTypeAdded;
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.mapper.EmploymentAppSourceTypeMapper;
import com.skytala.eCommerce.domain.humanres.relations.employmentAppSourceType.model.EmploymentAppSourceType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddEmploymentAppSourceType extends Command {

private EmploymentAppSourceType elementToBeAdded;
public AddEmploymentAppSourceType(EmploymentAppSourceType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

EmploymentAppSourceType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setEmploymentAppSourceTypeId(delegator.getNextSeqId("EmploymentAppSourceType"));
GenericValue newValue = delegator.makeValue("EmploymentAppSourceType", elementToBeAdded.mapAttributeField());
addedElement = EmploymentAppSourceTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new EmploymentAppSourceTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
