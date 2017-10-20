package com.skytala.eCommerce.domain.workeffort.relations.workEffort.command.fixedAssetStd;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.event.fixedAssetStd.WorkEffortFixedAssetStdAdded;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.mapper.fixedAssetStd.WorkEffortFixedAssetStdMapper;
import com.skytala.eCommerce.domain.workeffort.relations.workEffort.model.fixedAssetStd.WorkEffortFixedAssetStd;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddWorkEffortFixedAssetStd extends Command {

private WorkEffortFixedAssetStd elementToBeAdded;
public AddWorkEffortFixedAssetStd(WorkEffortFixedAssetStd elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

WorkEffortFixedAssetStd addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("WorkEffortFixedAssetStd", elementToBeAdded.mapAttributeField());
addedElement = WorkEffortFixedAssetStdMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new WorkEffortFixedAssetStdAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
