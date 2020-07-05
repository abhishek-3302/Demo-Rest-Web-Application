package com.demo.application.restWeb.DemoRestWebApplication.UserService;

import com.demo.application.restWeb.DemoRestWebApplication.Beans.Email;
import com.demo.application.restWeb.DemoRestWebApplication.Beans.EmailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("eservice")
public class EServiceImpl implements EService {

    @Autowired
    public JavaMailSender MailSender;
    @Override
    public EmailResponse sendMail(Email email) {
        EmailResponse emailResponse = new EmailResponse();
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getTo());
            message.setSubject(email.getSubject());
            message.setText(email.getText());
            MailSender.send(message);
            emailResponse.setCode(0);
            emailResponse.setMessage("E-mail is sent!!!");
        }
        catch (Exception ex)
        {
            emailResponse.setCode(1);
            emailResponse.setMessage("Error while sending mail"+ex.getMessage());
        }
        return emailResponse;
    }
}
