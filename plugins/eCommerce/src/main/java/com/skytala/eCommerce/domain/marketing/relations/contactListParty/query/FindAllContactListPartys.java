
package com.skytala.eCommerce.domain.marketing.relations.contactListParty.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.event.ContactListPartyFound;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.mapper.ContactListPartyMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactListParty.model.ContactListParty;


public class FindAllContactListPartys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactListParty> returnVal = new ArrayList<ContactListParty>();
try{
List<GenericValue> results = delegator.findAll("ContactListParty", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContactListPartyMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContactListPartyFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
