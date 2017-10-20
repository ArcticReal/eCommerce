
package com.skytala.eCommerce.domain.party.relations.contactMech.query.attribute;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.attribute.ContactMechAttributeFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.attribute.ContactMechAttributeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.attribute.ContactMechAttribute;


public class FindAllContactMechAttributes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactMechAttribute> returnVal = new ArrayList<ContactMechAttribute>();
try{
List<GenericValue> results = delegator.findAll("ContactMechAttribute", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContactMechAttributeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContactMechAttributeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
