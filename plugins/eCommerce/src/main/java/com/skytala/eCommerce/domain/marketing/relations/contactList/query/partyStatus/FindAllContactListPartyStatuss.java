
package com.skytala.eCommerce.domain.marketing.relations.contactList.query.partyStatus;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.partyStatus.ContactListPartyStatusFound;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.partyStatus.ContactListPartyStatusMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.partyStatus.ContactListPartyStatus;


public class FindAllContactListPartyStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactListPartyStatus> returnVal = new ArrayList<ContactListPartyStatus>();
try{
List<GenericValue> results = delegator.findAll("ContactListPartyStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContactListPartyStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContactListPartyStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
