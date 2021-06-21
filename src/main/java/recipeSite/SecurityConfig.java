package recipeSite;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //Spring Securityの基本的な設計
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/css/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/loginForm").permitAll()
                .antMatchers("/loginPage").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/newUser").permitAll()
                .antMatchers("/recipe").permitAll()
                .antMatchers("/recipeCategory").permitAll()
                .antMatchers("/recipeCategoryChange").permitAll()
                .antMatchers("/searchResult").permitAll()
                .anyRequest().authenticated() //myPageのみ認証なしのアクセス不可
                .and()
                .formLogin()
                .loginProcessingUrl("/loginForm")
                .loginPage("/loginForm")
                .failureUrl("/loginForm?error")
                .defaultSuccessUrl("/myPage", true)
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
