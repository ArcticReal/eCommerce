
package com.skytala.eCommerce.domain.party.relations.contactMech.query.type;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.contactMech.event.type.ContactMechTypeFound;
import com.skytala.eCommerce.domain.party.relations.contactMech.mapper.type.ContactMechTypeMapper;
import com.skytala.eCommerce.domain.party.relations.contactMech.model.type.ContactMechType;


public class FindAllContactMechTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactMechType> returnVal = new ArrayList<ContactMechType>();
try{
List<GenericValue> results = delegator.findAll("ContactMechType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContactMechTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContactMechTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
