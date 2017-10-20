
package com.skytala.eCommerce.domain.content.relations.mimeType.query.htmlTemplate;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.mimeType.event.htmlTemplate.MimeTypeHtmlTemplateFound;
import com.skytala.eCommerce.domain.content.relations.mimeType.mapper.htmlTemplate.MimeTypeHtmlTemplateMapper;
import com.skytala.eCommerce.domain.content.relations.mimeType.model.htmlTemplate.MimeTypeHtmlTemplate;


public class FindAllMimeTypeHtmlTemplates extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<MimeTypeHtmlTemplate> returnVal = new ArrayList<MimeTypeHtmlTemplate>();
try{
List<GenericValue> results = delegator.findAll("MimeTypeHtmlTemplate", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(MimeTypeHtmlTemplateMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new MimeTypeHtmlTemplateFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
