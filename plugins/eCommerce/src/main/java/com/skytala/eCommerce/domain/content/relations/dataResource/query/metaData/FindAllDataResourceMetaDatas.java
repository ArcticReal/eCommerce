
package com.skytala.eCommerce.domain.content.relations.dataResource.query.metaData;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.dataResource.event.metaData.DataResourceMetaDataFound;
import com.skytala.eCommerce.domain.content.relations.dataResource.mapper.metaData.DataResourceMetaDataMapper;
import com.skytala.eCommerce.domain.content.relations.dataResource.model.metaData.DataResourceMetaData;


public class FindAllDataResourceMetaDatas extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<DataResourceMetaData> returnVal = new ArrayList<DataResourceMetaData>();
try{
List<GenericValue> results = delegator.findAll("DataResourceMetaData", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(DataResourceMetaDataMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new DataResourceMetaDataFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
