package com.skytala.eCommerce.domain.party.relations.agreement.command.productAppl;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.party.relations.agreement.event.productAppl.AgreementProductApplAdded;
import com.skytala.eCommerce.domain.party.relations.agreement.mapper.productAppl.AgreementProductApplMapper;
import com.skytala.eCommerce.domain.party.relations.agreement.model.productAppl.AgreementProductAppl;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddAgreementProductAppl extends Command {

private AgreementProductAppl elementToBeAdded;
public AddAgreementProductAppl(AgreementProductAppl elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

AgreementProductAppl addedElement = null;
boolean success = false;
try {
elementToBeAdded.setAgreementItemSeqId(delegator.getNextSeqId("AgreementProductAppl"));
GenericValue newValue = delegator.makeValue("AgreementProductAppl", elementToBeAdded.mapAttributeField());
addedElement = AgreementProductApplMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new AgreementProductApplAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
