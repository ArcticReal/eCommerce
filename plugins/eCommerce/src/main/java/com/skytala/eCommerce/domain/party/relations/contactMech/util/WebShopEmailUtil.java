package com.skytala.eCommerce.domain.party.relations.contactMech.util;

public class WebShopEmailUtil {

    public static Email ACCOUNT_VERIFICATION_EMAIL = createVerificationEmail();
    private static final String webShopName = "Skytala Book Shop";
    private static final String shopVerifyLink = "http://192.168.49.60:3000/profile/verify/";


    private static Email createVerificationEmail(){
        Email email = new Email();
        email.setSendFrom("gartnetzwerg@outlook.de");
        email.setSubject(webShopName + " - Account Aktivierung");
        email.setContentType("text/html");
        email.setBody("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\"" +
                "    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">" +
                    "<head>" +
                        "<title>"+webShopName+"</title>" +
                        "<meta http-equiv='content-type' content='text/html; charset=UTF-8'/>" +
                    "</head>" +
                "   <body style=\"font-family: sans-serif;\">" +
                "       <div style=\"width: 100%; height: 58px; background-color: #100542\">" +
                "           <img src=\"https://www.skytala-gmbh.com/assets/images/skytala-logo-white.png\" " +
                                "alt=\"Skytala-Logo\" " +
                                "style=\"width: 300px; height:50px; display:flex; padding: 3px 16px; color:white;\"/>" +
                "       </div>" +
                        "<div style=\"margin-left:5px;\">" +
                    "       <h1>" +
                    "           Account Aktivierungs Email" +
                    "       </h1>" +
                    "       <div>" +
                                "Für diese Email Addresse wurde ein Account auf unserer Seite erstellt. Falls dies nicht Sie waren, " +
                                "können Sie diese Email ignorieren.<br/>" +
                    "           Diese Email wurde automatisch gesendet.<br/>" +
                    "           Mit folgendem Link können Sie ihren Account beim "+ webShopName + " aktivieren: <br/>" +
                    "           <a href=\""+shopVerifyLink+"%%verifyHash%%\" target=\"_blank\" rel = \"noopener noreferrer\">"+shopVerifyLink+"%%verifyHash%%</a>" +
                    "       </div>" +
                    "       <div style=\"padding-top:8px;\">" +
                                "Zitat des Tages:<br/>" +
                    "           <cite>\"To be or not to be. That is the Question\"</cite> - Shakespeare<br/>" +
                    "       </div>" +
                    "       <div style=\"padding-top:8px;\">" +
                    "           Viel Vergnügen beim Bücher kaufen und lesen<br/>" +
                    "           Ihr "+webShopName+"<br/>" +
                    "       </div>" +
                            "<div style =\"margin-top:15px;\">" +
                                "Skytala GmbH<br/>" +
                                "Glaserstraße 3<br/>" +
                                "87436 Dietmannsried<br/>" +
                                "Deutschland" +
                            "</div>" +
                        "</div>" +
                "   </body>" +
                "</html>");

        return email;
    }

    public static void reset(){
        ACCOUNT_VERIFICATION_EMAIL = createVerificationEmail();
    }

}
