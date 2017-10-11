
package com.skytala.eCommerce.domain.party.relations.emailAddressVerification.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.event.EmailAddressVerificationFound;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.mapper.EmailAddressVerificationMapper;
import com.skytala.eCommerce.domain.party.relations.emailAddressVerification.model.EmailAddressVerification;


public class FindAllEmailAddressVerifications extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<EmailAddressVerification> returnVal = new ArrayList<EmailAddressVerification>();
try{
List<GenericValue> results = delegator.findAll("EmailAddressVerification", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(EmailAddressVerificationMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new EmailAddressVerificationFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
