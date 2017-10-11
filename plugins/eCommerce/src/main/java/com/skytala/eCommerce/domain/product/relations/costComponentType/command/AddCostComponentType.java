package com.skytala.eCommerce.domain.product.relations.costComponentType.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.costComponentType.event.CostComponentTypeAdded;
import com.skytala.eCommerce.domain.product.relations.costComponentType.mapper.CostComponentTypeMapper;
import com.skytala.eCommerce.domain.product.relations.costComponentType.model.CostComponentType;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCostComponentType extends Command {

private CostComponentType elementToBeAdded;
public AddCostComponentType(CostComponentType elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CostComponentType addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCostComponentTypeId(delegator.getNextSeqId("CostComponentType"));
GenericValue newValue = delegator.makeValue("CostComponentType", elementToBeAdded.mapAttributeField());
addedElement = CostComponentTypeMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CostComponentTypeAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
