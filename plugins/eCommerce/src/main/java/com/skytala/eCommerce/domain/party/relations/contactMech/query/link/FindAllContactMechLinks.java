
package com.skytala.eCommerce.domain.party.relations.contactMech.query.link;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.link.ContactMechLinkFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.link.ContactMechLinkMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.link.ContactMechLink;


public class FindAllContactMechLinks extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactMechLink> returnVal = new ArrayList<ContactMechLink>();
try{
List<GenericValue> results = delegator.findAll("ContactMechLink", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContactMechLinkMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContactMechLinkFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
