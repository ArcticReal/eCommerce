package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.typeGlAccount;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.typeGlAccount.FixedAssetTypeGlAccountAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.typeGlAccount.FixedAssetTypeGlAccountMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.typeGlAccount.FixedAssetTypeGlAccount;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddFixedAssetTypeGlAccount extends Command {

private FixedAssetTypeGlAccount elementToBeAdded;
public AddFixedAssetTypeGlAccount(FixedAssetTypeGlAccount elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

FixedAssetTypeGlAccount addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("FixedAssetTypeGlAccount", elementToBeAdded.mapAttributeField());
addedElement = FixedAssetTypeGlAccountMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new FixedAssetTypeGlAccountAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
