
package com.skytala.eCommerce.domain.order.relations.requirement.query.typeAttr;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.order.relations.requirement.event.typeAttr.RequirementTypeAttrFound;
import com.skytala.eCommerce.domain.order.relations.requirement.mapper.typeAttr.RequirementTypeAttrMapper;
import com.skytala.eCommerce.domain.order.relations.requirement.model.typeAttr.RequirementTypeAttr;


public class FindAllRequirementTypeAttrs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<RequirementTypeAttr> returnVal = new ArrayList<RequirementTypeAttr>();
try{
List<GenericValue> results = delegator.findAll("RequirementTypeAttr", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(RequirementTypeAttrMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new RequirementTypeAttrFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
