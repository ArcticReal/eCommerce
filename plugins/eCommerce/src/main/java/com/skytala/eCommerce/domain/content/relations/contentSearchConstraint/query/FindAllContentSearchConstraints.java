
package com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.event.ContentSearchConstraintFound;
import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.mapper.ContentSearchConstraintMapper;
import com.skytala.eCommerce.domain.content.relations.contentSearchConstraint.model.ContentSearchConstraint;


public class FindAllContentSearchConstraints extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentSearchConstraint> returnVal = new ArrayList<ContentSearchConstraint>();
try{
List<GenericValue> results = delegator.findAll("ContentSearchConstraint", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentSearchConstraintMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentSearchConstraintFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
