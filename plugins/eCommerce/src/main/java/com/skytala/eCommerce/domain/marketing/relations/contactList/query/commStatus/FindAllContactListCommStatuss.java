
package com.skytala.eCommerce.domain.marketing.relations.contactList.query.commStatus;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.contactList.event.commStatus.ContactListCommStatusFound;
import com.skytala.eCommerce.domain.marketing.relations.contactList.mapper.commStatus.ContactListCommStatusMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactList.model.commStatus.ContactListCommStatus;


public class FindAllContactListCommStatuss extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactListCommStatus> returnVal = new ArrayList<ContactListCommStatus>();
try{
List<GenericValue> results = delegator.findAll("ContactListCommStatus", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContactListCommStatusMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContactListCommStatusFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
