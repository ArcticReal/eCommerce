package com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.event.FixedAssetRegistrationAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.mapper.FixedAssetRegistrationMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAssetRegistration.model.FixedAssetRegistration;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetRegistration extends Command {

private FixedAssetRegistration elementToBeAdded;
public AddFixedAssetRegistration(FixedAssetRegistration elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetRegistration addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FixedAssetRegistration", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetRegistrationMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetRegistrationAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
