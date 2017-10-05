
package com.skytala.eCommerce.domain.contentSearchResult.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
	import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.contentSearchResult.event.ContentSearchResultFound;
import com.skytala.eCommerce.domain.contentSearchResult.mapper.ContentSearchResultMapper;
import com.skytala.eCommerce.domain.contentSearchResult.model.ContentSearchResult;


public class FindAllContentSearchResults extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentSearchResult> returnVal = new ArrayList<ContentSearchResult>();
try{
List<GenericValue> results = delegator.findAll("ContentSearchResult", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentSearchResultMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentSearchResultFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
