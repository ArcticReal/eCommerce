
package com.skytala.eCommerce.domain.party.relations.termTypeAttr.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.party.relations.termTypeAttr.event.TermTypeAttrFound;
import com.skytala.eCommerce.domain.party.relations.termTypeAttr.mapper.TermTypeAttrMapper;
import com.skytala.eCommerce.domain.party.relations.termTypeAttr.model.TermTypeAttr;


public class FindAllTermTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<TermTypeAttr> returnVal = new ArrayList<TermTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("TermTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(TermTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new TermTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
