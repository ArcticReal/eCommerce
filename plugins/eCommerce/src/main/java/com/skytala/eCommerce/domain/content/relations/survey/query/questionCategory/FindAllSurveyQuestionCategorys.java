
package com.skytala.eCommerce.domain.content.relations.survey.query.questionCategory;
import java.util.ArrayList;
import java.util.List;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.DelegatorFactory;
import org.apache.ofbiz.entity.GenericEntityException;
import org.apache.ofbiz.entity.GenericValue;
import com.skytala.eCommerce.framework.pubsub.Broker;
import com.skytala.eCommerce.framework.pubsub.Query;
import com.skytala.eCommerce.framework.pubsub.Event;
import com.skytala.eCommerce.domain.content.relations.survey.event.questionCategory.SurveyQuestionCategoryFound;
import com.skytala.eCommerce.domain.content.relations.survey.mapper.questionCategory.SurveyQuestionCategoryMapper;
import com.skytala.eCommerce.domain.content.relations.survey.model.questionCategory.SurveyQuestionCategory;


public class FindAllSurveyQuestionCategorys extends Query {

@Override
public Event execute() {

Delegator delegator = DelegatorFactory.getDelegator("default");
List<SurveyQuestionCategory> returnVal = new ArrayList<SurveyQuestionCategory>();
try{
List<GenericValue> results = delegator.findAll("SurveyQuestionCategory", false);
for (int i = 0; i < results.size(); i++) {
returnVal.add(SurveyQuestionCategoryMapper.map(results.get(i)));
}
} catch (GenericEntityException e) {
 System.err.println(e.getMessage()); 
}
Event resultingEvent = new SurveyQuestionCategoryFound(returnVal);
Broker.instance().publish(resultingEvent);
return resultingEvent;
}
}
