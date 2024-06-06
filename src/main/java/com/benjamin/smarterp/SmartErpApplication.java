package com.benjamin.smarterp;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme(value = "hilla-crm")
public class SmartErpApplication implements AppShellConfigurator {

	public static void main(String[] args) {
		SpringApplication.run(SmartErpApplication.class, args);
	}

}
