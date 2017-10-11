
package com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.event.ContactMechTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.mapper.ContactMechTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.contactMechTypeAttr.model.ContactMechTypeAttr;


public class FindAllContactMechTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContactMechTypeAttr> returnVal = new ArrayList<ContactMechTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("ContactMechTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContactMechTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContactMechTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
