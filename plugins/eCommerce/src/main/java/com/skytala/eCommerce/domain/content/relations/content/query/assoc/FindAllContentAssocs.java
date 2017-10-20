
package com.skytala.eCommerce.domain.content.relations.content.query.assoc;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.content.event.assoc.ContentAssocFound;
import com.skytala.eCommerce.domain.content.relations.content.mapper.assoc.ContentAssocMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.assoc.ContentAssoc;


public class FindAllContentAssocs extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentAssoc> returnVal = new ArrayList<ContentAssoc>();
try{
List<GenericValue> results = delegator.findAll("ContentAssoc", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentAssocMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentAssocFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
