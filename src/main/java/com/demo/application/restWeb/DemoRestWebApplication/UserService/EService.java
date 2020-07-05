package com.demo.application.restWeb.DemoRestWebApplication.UserService;

import com.demo.application.restWeb.DemoRestWebApplication.Beans.Email;
import com.demo.application.restWeb.DemoRestWebApplication.Beans.EmailResponse;

public interface EService {

    EmailResponse sendMail(Email email);
}
