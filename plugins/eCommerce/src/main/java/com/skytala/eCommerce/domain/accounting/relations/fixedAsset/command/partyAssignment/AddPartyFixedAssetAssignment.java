package com.skytala.eCommerce.domain.accounting.relations.fixedAsset.command.partyAssignment;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.event.partyAssignment.PartyFixedAssetAssignmentAdded;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.mapper.partyAssignment.PartyFixedAssetAssignmentMapper;
import com.skytala.eCommerce.domain.accounting.relations.fixedAsset.model.partyAssignment.PartyFixedAssetAssignment;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddPartyFixedAssetAssignment extends Command {

private PartyFixedAssetAssignment elementToBeAdded;
public AddPartyFixedAssetAssignment(PartyFixedAssetAssignment elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

PartyFixedAssetAssignment addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("PartyFixedAssetAssignment", elementToBeAdded.mapAttributeField());
addedElement = PartyFixedAssetAssignmentMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new PartyFixedAssetAssignmentAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
