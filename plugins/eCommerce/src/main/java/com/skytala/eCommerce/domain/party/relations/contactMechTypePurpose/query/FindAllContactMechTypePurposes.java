
package com.skytala.eCommerce.domain.party.relations.contactMechTypePurpose.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.contactMechTypePurpose.event.ContactMechTypePurposeFound;
import com.skytala.eCommerce.domain.party.relations.contactMechTypePurpose.mapper.ContactMechTypePurposeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMechTypePurpose.model.ContactMechTypePurpose;


public class FindAllContactMechTypePurposes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactMechTypePurpose> returnVal = new ArrayList<ContactMechTypePurpose>();
try{
List<GenericValue> results = delegator.findAll("ContactMechTypePurpose", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContactMechTypePurposeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContactMechTypePurposeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}