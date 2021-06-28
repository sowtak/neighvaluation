package com.zova0.rinjindoudeshouserver.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
@AllArgsConstructor
public class MailContentBuilder {
    private final SpringTemplateEngine templateEngine;
    public String build(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process("mailTemplate", context);
    }
}
