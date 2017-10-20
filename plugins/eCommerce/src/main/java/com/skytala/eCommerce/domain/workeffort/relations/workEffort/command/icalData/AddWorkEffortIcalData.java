package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.icalData;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.icalData.WorkEffortIcalDataAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.icalData.WorkEffortIcalDataMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.icalData.WorkEffortIcalData;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortIcalData extends Command {

private WorkEffortIcalData elementToBeAdded;
public AddWorkEffortIcalData(WorkEffortIcalData elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortIcalData addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortIcalData", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortIcalDataMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortIcalDataAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
