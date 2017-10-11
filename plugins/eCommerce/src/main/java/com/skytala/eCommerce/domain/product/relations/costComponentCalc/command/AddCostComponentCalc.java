package com.skytala.eCommerce.domain.product.relations.costComponentCalc.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.product.relations.costComponentCalc.event.CostComponentCalcAdded;
import com.skytala.eCommerce.domain.product.relations.costComponentCalc.mapper.CostComponentCalcMapper;
import com.skytala.eCommerce.domain.product.relations.costComponentCalc.model.CostComponentCalc;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddCostComponentCalc extends Command {

private CostComponentCalc elementToBeAdded;
public AddCostComponentCalc(CostComponentCalc elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

CostComponentCalc addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCostComponentCalcId(delegator.getNextSeqId("CostComponentCalc"));
GenericValue newValue = delegator.makeValue("CostComponentCalc", elementToBeAdded.mapAttributeField());
addedElement = CostComponentCalcMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new CostComponentCalcAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
