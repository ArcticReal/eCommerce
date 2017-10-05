
package com.skytala.eCommerce.domain.contactList.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.contactList.event.ContactListFound;
import com.skytala.eCommerce.domain.contactList.mapper.ContactListMapper;
import com.skytala.eCommerce.domain.contactList.model.ContactList;


public class FindAllContactLists extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactList> returnVal = new ArrayList<ContactList>();
try{
List<GenericValue> results = delegator.findAll("ContactList", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContactListMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContactListFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
