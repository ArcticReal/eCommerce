package com.skytala.eCommerce.domain.party.relations.contactMech.util;

public class Email {
        private String subject;
        private String sendFrom;
        private String sendTo;
        private String sendBcc;
        private String sendCc;
        private String contentType;
        private String body;
        private String port;

    public String getSubject() {
        return subject;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSendFrom() {
        return sendFrom;
    }

    public void setSendFrom(String sendFrom) {
        this.sendFrom = sendFrom;
    }

    public String getSendBcc() {
        return sendBcc;
    }

    public void setSendBcc(String sendBcc) {
        this.sendBcc = sendBcc;
    }

    public String getSendCc() {
        return sendCc;
    }

    public void setSendCc(String sendCc) {
        this.sendCc = sendCc;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
