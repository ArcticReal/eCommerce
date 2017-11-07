package com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.event.X509IssuerProvisionAdded;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.mapper.X509IssuerProvisionMapper;
import com.skytala.eCommerce.domain.login.relations.x509IssuerProvision.model.X509IssuerProvision;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddX509IssuerProvision extends Command {

private X509IssuerProvision elementToBeAdded;
public AddX509IssuerProvision(X509IssuerProvision elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

X509IssuerProvision addedElement = null;
boolean success = false;
try {
elementToBeAdded.setCertProvisionId(delegator.getNextSeqId("X509IssuerProvision"));
GenericValue newValue = delegator.makeValue("X509IssuerProvision", elementToBeAdded.mapAttributeField());
addedElement = X509IssuerProvisionMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new X509IssuerProvisionAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
