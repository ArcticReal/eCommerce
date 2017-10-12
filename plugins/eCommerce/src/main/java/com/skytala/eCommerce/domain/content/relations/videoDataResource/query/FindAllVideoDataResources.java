
package com.skytala.eCommerce.domain.content.relations.videoDataResource.query;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.event.VideoDataResourceFound;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.mapper.VideoDataResourceMapper;
import com.skytala.eCommerce.domain.content.relations.videoDataResource.model.VideoDataResource;


public class FindAllVideoDataResources extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<VideoDataResource> returnVal = new ArrayList<VideoDataResource>();
try{
List<GenericValue> results = delegator.findAll("VideoDataResource", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(VideoDataResourceMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new VideoDataResourceFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
