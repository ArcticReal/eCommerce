package com.skytala.eCommerce.domain.content.relations.mimeType.command.htmlTemplate;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.entity.GenericEntityNotFoundException;
import com.skytala.eCommerce.domain.content.relations.mimeType.event.htmlTemplate.MimeTypeHtmlTemplateUpdated;
import com.skytala.eCommerce.domain.content.relations.mimeType.model.htmlTemplate.MimeTypeHtmlTemplate;
import com.skytala.eCommerce.framework.exceptions.RecordNotFoundException;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Command;
import com.skytala.eCommerce.framework.pubsub.Event;

public class UpdateMimeTypeHtmlTemplate extends Command {

private MimeTypeHtmlTemplate elementToBeUpdated;

public UpdateMimeTypeHtmlTemplate(MimeTypeHtmlTemplate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}
public MimeTypeHtmlTemplate getElementToBeUpdated() {
return elementToBeUpdated;
}
public void setElementToBeUpdated(MimeTypeHtmlTemplate elementToBeUpdated){
this.elementToBeUpdated = elementToBeUpdated;
}

@Override
public Event execute() throws RecordNotFoundException{


Delegator delegator = DelegatorFactory.getDelegator("default");

boolean success;
try{
GenericValue newValue = delegator.makeValue("MimeTypeHtmlTemplate", elementToBeUpdated.mapAttributeField());
delegator.store(newValue);
if(delegator.store(newValue) == 0) { 
throw new RecordNotFoundException(MimeTypeHtmlTemplate.class); 
}
success = true;
} catch (GenericEntityException e) {
e.printStackTrace();
if(e.getCause().getClass().equals(GenericEntityNotFoundException.class)) {
throw new RecordNotFoundException(MimeTypeHtmlTemplate.class);
}
success = false;
}
Event resultingEvent = new MimeTypeHtmlTemplateUpdated(success);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
