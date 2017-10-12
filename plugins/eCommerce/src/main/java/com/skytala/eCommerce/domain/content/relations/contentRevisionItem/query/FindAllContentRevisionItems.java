
package com.skytala.eCommerce.domain.content.relations.contentRevisionItem.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.contentRevisionItem.event.ContentRevisionItemFound;
import com.skytala.eCommerce.domain.content.relations.contentRevisionItem.mapper.ContentRevisionItemMapper;
import com.skytala.eCommerce.domain.content.relations.contentRevisionItem.model.ContentRevisionItem;


public class FindAllContentRevisionItems extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentRevisionItem> returnVal = new ArrayList<ContentRevisionItem>();
try{
List<GenericValue> results = delegator.findAll("ContentRevisionItem", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentRevisionItemMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentRevisionItemFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
