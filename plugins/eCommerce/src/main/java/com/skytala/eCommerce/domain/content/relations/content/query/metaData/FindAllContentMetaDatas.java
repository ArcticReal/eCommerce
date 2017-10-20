
package com.skytala.eCommerce.domain.content.relations.content.query.metaData;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.content.event.metaData.ContentMetaDataFound;
import com.skytala.eCommerce.domain.content.relations.content.mapper.metaData.ContentMetaDataMapper;
import com.skytala.eCommerce.domain.content.relations.content.model.metaData.ContentMetaData;


public class FindAllContentMetaDatas extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<ContentMetaData> returnVal = new ArrayList<ContentMetaData>();
try{
List<GenericValue> results = delegator.findAll("ContentMetaData", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(ContentMetaDataMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new ContentMetaDataFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
