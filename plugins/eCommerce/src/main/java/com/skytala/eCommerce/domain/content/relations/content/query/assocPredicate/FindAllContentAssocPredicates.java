
package com.skytala.eCommerce.domain.content.relations.content.query.assocPredicate;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.content.event.assocPredicate.ContentAssocPredicateFound;
import com.skytala.eCommerce.domain.content.relations.content.mapper.assocPredicate.ContentAssocPredicateMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.assocPredicate.ContentAssocPredicate;


public class FindAllContentAssocPredicates extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentAssocPredicate> returnVal = new ArrayList<ContentAssocPredicate>();
try{
List<GenericValue> results = delegator.findAll("ContentAssocPredicate", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentAssocPredicateMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentAssocPredicateFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
