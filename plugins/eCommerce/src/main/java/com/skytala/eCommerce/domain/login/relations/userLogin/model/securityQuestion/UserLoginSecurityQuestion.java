package com.skytala.eCommerce.domain.login.relations.userLogin.model.securityQuestion;

import java.util.Map;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;
import java.io.Serializable;
import com.skytala.eCommerce.domain.login.relations.userLogin.mapper.securityQuestion.UserLoginSecurityQuestionMapper;

public class UserLoginSecurityQuestion implements Serializable{

private static final long serialVersionUID = 1L;
private String questionEnumId;
private String userLoginId;
private String securityAnswer;

public String getQuestionEnumId() {
return questionEnumId;
}

public void setQuestionEnumId(String  questionEnumId) {
this.questionEnumId = questionEnumId;
}

public String getUserLoginId() {
return userLoginId;
}

public void setUserLoginId(String  userLoginId) {
this.userLoginId = userLoginId;
}

public String getSecurityAnswer() {
return securityAnswer;
}

public void setSecurityAnswer(String  securityAnswer) {
this.securityAnswer = securityAnswer;
}


public Map<String, Object> mapAttributeField() {
return UserLoginSecurityQuestionMapper.map(this);
}
}
