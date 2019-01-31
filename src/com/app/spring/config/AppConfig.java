/**
 * 
 */
package com.app.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author suhas
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.app.spring")
public class AppConfig {

}
