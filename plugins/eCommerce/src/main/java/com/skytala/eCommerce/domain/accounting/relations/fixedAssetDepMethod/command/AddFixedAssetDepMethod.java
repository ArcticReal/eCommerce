package com.skytala.eCommerce.domain.accounting.relations.fixedAssetDepMethod.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetDepMethod.event.FixedAssetDepMethodAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetDepMethod.mapper.FixedAssetDepMethodMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetDepMethod.model.FixedAssetDepMethod;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetDepMethod extends Command {

private FixedAssetDepMethod elementToBeAdded;
public AddFixedAssetDepMethod(FixedAssetDepMethod elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetDepMethod addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FixedAssetDepMethod", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetDepMethodMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetDepMethodAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
