
package com.skytala.eCommerce.domain.content.relations.content.query.keyword;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.content.event.keyword.ContentKeywordFound;
import com.skytala.eCommerce.domain.content.relations.content.mapper.keyword.ContentKeywordMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.keyword.ContentKeyword;


public class FindAllContentKeywords extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentKeyword> returnVal = new ArrayList<ContentKeyword>();
try{
List<GenericValue> results = delegator.findAll("ContentKeyword", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentKeywordMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentKeywordFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
