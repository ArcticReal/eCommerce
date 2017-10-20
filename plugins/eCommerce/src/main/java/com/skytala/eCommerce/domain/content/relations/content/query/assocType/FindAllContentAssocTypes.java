
package com.skytala.eCommerce.domain.content.relations.content.query.assocType;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.content.event.assocType.ContentAssocTypeFound;
import com.skytala.eCommerce.domain.content.relations.content.mapper.assocType.ContentAssocTypeMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.assocType.ContentAssocType;


public class FindAllContentAssocTypes extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentAssocType> returnVal = new ArrayList<ContentAssocType>();
try{
List<GenericValue> results = delegator.findAll("ContentAssocType", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentAssocTypeMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentAssocTypeFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
