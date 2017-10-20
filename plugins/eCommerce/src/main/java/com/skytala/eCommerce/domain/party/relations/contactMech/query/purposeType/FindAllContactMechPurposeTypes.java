
package com.skytala.eCommerce.domain.party.relations.contactMech.query.purposeType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.purposeType.ContactMechPurposeTypeFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.purposeType.ContactMechPurposeTypeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.purposeType.ContactMechPurposeType;


public class FindAllContactMechPurposeTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactMechPurposeType> returnVal = new ArrayList<ContactMechPurposeType>();
try{
List<GenericValue> results = delegator.findAll("ContactMechPurposeType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContactMechPurposeTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContactMechPurposeTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
