package com.skytala.eCommerce.domain.product.relations.costComponentTypeAttr.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.costComponentTypeAttr.event.CostComponentTypeAttrAdded;
import com.skytala.eCommerce.domain.product.relations.costComponentTypeAttr.mapper.CostComponentTypeAttrMapper;
import com.skytala.eCommerce.domain.product.relations.costComponentTypeAttr.model.CostComponentTypeAttr;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCostComponentTypeAttr extends Command {

private CostComponentTypeAttr elementToBeAdded;
public AddCostComponentTypeAttr(CostComponentTypeAttr elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CostComponentTypeAttr addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("CostComponentTypeAttr", elementToBeAdded.mapAttributeField());
addedElement = CostComponentTypeAttrMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CostComponentTypeAttrAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
