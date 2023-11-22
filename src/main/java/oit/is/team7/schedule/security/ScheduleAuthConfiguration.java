package oit.is.team7.schedule.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class ScheduleAuthConfiguration {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login.permitAll())
        .logout(logout -> logout.logoutUrl("/logout")
            .logoutSuccessUrl("/"))
        .authorizeHttpRequests(authz -> authz.requestMatchers(AntPathRequestMatcher.antMatcher("/home"))
                .authenticated().requestMatchers(AntPathRequestMatcher.antMatcher("/calendar"))
                .authenticated().requestMatchers(AntPathRequestMatcher.antMatcher("/post"))
                .authenticated().requestMatchers(AntPathRequestMatcher.antMatcher("/detail"))
                .authenticated().requestMatchers(AntPathRequestMatcher.antMatcher("/edit"))
                .authenticated().requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll())
        .csrf(csrf -> csrf
            .ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/*")))
        .headers(headers -> headers
            .frameOptions(frameOptions -> frameOptions
                .sameOrigin()));

    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    UserDetails user1 = User.withUsername("user1")
        .password("{bcrypt}$2y$10$DPZvoBEzZrecXwgAhq/69OoHbWam2Kq0taE9gXsEHJ4q9yRzGtvHO")
        .roles("USER").build();
    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$10$SbWZIjsMBTtJ6SaG34GxauRrl7rD7YBKDQihnvmwaoEU/Q0kYy4ae").roles("USER").build();
    UserDetails user3 = User.withUsername("user3")
        .password("{bcrypt}$2y$10$bifSQsD6CxLgDfbvmdJi5.MArojBdtczVtBMqwr6F3PaCFoXO7s26").roles("USER").build();
    UserDetails user4 = User.withUsername("user4")
        .password("{bcrypt}$2y$10$qBTq8Ku0XpEA.MlHdbgMz.AocTZOc53q1TMb1mUP.vmK73vgkPTkO").roles("USER").build();

    return new InMemoryUserDetailsManager(user1, user2, user3, user4);
  }
}
