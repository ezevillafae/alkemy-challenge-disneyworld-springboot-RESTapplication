package com.alkemy.disneyworldspringbootapplication.service.impl;

import com.alkemy.disneyworldspringbootapplication.service.EmailService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private Environment environment;

    @Value("${disney.app.email.sender}")
    private String sender;
    @Value("${disney.app.email.enabled}")
    private boolean enabled;

    @Override
    public void sendWelcomeEmailTo(String to) {
        if(!enabled)
            return;
        String apiKey = environment.getProperty("EMAIL_API_KEY");
        Email fromEmail = new Email(sender);
        Email toEmail = new Email(to);
        Content content = new Content(
                "text/plain",
                "Welcome to disney app"
                );
        String subject = "Disney app";
        Mail mail = new Mail(fromEmail,subject,toEmail,content);
        SendGrid sendGrid = new SendGrid(apiKey);
        Request request = new Request();
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            log.info(String.valueOf(response.getStatusCode()));
            log.info(response.getBody());
            log.info(response.getHeaders().toString());
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }
}
