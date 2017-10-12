
package com.skytala.eCommerce.domain.marketing.relations.contactListType.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.marketing.relations.contactListType.event.ContactListTypeFound;
import com.skytala.eCommerce.domain.marketing.relations.contactListType.mapper.ContactListTypeMapper;
import com.skytala.eCommerce.domain.marketing.relations.contactListType.model.ContactListType;


public class FindAllContactListTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactListType> returnVal = new ArrayList<ContactListType>();
try{
List<GenericValue> results = delegator.findAll("ContactListType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContactListTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContactListTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
