package recipeSite.web;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegisterUserForm {
    private String username;
    private String encoded_password;

    public void setEncoded_password(String encoded_password) {
        this.encoded_password = passwordEncoder().encode(encoded_password);
    }

    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
