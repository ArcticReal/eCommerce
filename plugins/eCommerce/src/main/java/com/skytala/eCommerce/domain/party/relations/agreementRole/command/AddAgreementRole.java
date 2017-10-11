package com.skytala.eCommerce.domain.party.relations.agreementRole.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreementRole.event.AgreementRoleAdded;
import com.skytala.eCommerce.domain.party.relations.agreementRole.mapper.AgreementRoleMapper;
import com.skytala.eCommerce.domain.party.relations.agreementRole.model.AgreementRole;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementRole extends Command {

private AgreementRole elementToBeAdded;
public AddAgreementRole(AgreementRole elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementRole addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("AgreementRole", elementToBeAdded.mapAttributeField());
addedElement = AgreementRoleMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementRoleAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
