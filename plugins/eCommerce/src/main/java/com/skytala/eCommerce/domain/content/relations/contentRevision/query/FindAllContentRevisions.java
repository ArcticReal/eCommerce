
package com.skytala.eCommerce.domain.content.relations.contentRevision.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.contentRevision.event.ContentRevisionFound;
import com.skytala.eCommerce.domain.content.relations.contentRevision.mapper.ContentRevisionMapper;
import com.skytala.eCommerce.domain.content.relations.contentRevision.model.ContentRevision;


public class FindAllContentRevisions extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentRevision> returnVal = new ArrayList<ContentRevision>();
try{
List<GenericValue> results = delegator.findAll("ContentRevision", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentRevisionMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentRevisionFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
