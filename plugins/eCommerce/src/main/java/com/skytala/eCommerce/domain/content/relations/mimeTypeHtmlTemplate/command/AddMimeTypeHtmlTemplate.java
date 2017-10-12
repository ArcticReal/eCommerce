package com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.command;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.event.MimeTypeHtmlTemplateAdded;
import com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.mapper.MimeTypeHtmlTemplateMapper;
import com.skytala.eCommerce.domain.content.relations.mimeTypeHtmlTemplate.model.MimeTypeHtmlTemplate;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;
import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class AddMimeTypeHtmlTemplate extends Command {

private MimeTypeHtmlTemplate elementToBeAdded;
public AddMimeTypeHtmlTemplate(MimeTypeHtmlTemplate elementToBeAdded){
this.elementToBeAdded = elementToBeAdded;
}

@Override
public Event execute(){


Delegator delegator = DelegatorFactory.getDelegator("default");

MimeTypeHtmlTemplate addedElement = null;
boolean success = false;
try {
GenericValue newValue = delegator.makeValue("MimeTypeHtmlTemplate", elementToBeAdded.mapAttributeField());
addedElement = MimeTypeHtmlTemplateMapper.map(delegator.create(newValue));
success = true;
} catch(GenericEntityException e) {
 e.printStackTrace(); 
addedElement = null;
}

Event resultingEvent = new MimeTypeHtmlTemplateAdded(addedElement, success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
