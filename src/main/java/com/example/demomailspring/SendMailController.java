package com.example.demomailspring;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * SendMailController
 */
@Controller
public class SendMailController {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration configuration;

    private final static Logger LOG = LoggerFactory.getLogger(SendMailController.class);

    @RequestMapping("/sendmail")
    @ResponseBody
    public String home() {
        try {
            sendMail();
            LOG.debug("Email sent!!!");
            return "SendMail";
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
            return "Send Mail failed...";
        }

    }

    private void sendMail() throws Exception {

        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        Map<String, Object> model = new HashMap<>();
        model.put("user", "Johann Pando");

        configuration.setClassForTemplateLoading(this.getClass(), "/templates/");

        Template template = configuration.getTemplate("welcome.html");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

        helper.setFrom("jpandode@outlook.es");

        helper.setTo("jpandode@outlook.es");
        helper.setText(text, true);
        helper.setSubject("Hola, tienes un nuevo coreeo");

        LOG.debug("Sending email...");
        sender.send(message);
    }
}