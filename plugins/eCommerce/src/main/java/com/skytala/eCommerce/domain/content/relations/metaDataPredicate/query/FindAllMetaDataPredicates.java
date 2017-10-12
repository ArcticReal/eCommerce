
package com.skytala.eCommerce.domain.content.relations.metaDataPredicate.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.event.MetaDataPredicateFound;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.mapper.MetaDataPredicateMapper;
import com.skytala.eCommerce.domain.content.relations.metaDataPredicate.model.MetaDataPredicate;


public class FindAllMetaDataPredicates extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MetaDataPredicate> returnVal = new ArrayList<MetaDataPredicate>();
try{
List<GenericValue> results = delegator.findAll("MetaDataPredicate", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(MetaDataPredicateMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new MetaDataPredicateFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
