/*
 * Permission to use, copy, modify, and/or distribute this software for any 
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 * OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package jakartaee.examples.soteria.customFormWithJsf;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import static javax.security.enterprise.AuthenticationStatus.SEND_CONTINUE;
import static javax.security.enterprise.AuthenticationStatus.SEND_FAILURE;
import javax.security.enterprise.SecurityContext;
import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The login bean.
 *
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    /**
     * Helper bean to populate tables.
     */
    @Inject
    private PopulateBean populateBean;
    
    /**
     * Stores the External context.
     */
    @Inject
    private ExternalContext externalContext;

    /**
     * Stores the Faces context.
     */
    @Inject
    private FacesContext facesContext;

    /**
     * Stores the Security context.
     */
    @Inject
    private SecurityContext securityContext;

    /**
     * Stores the password.
     */
    private String password;

    /**
     * Stores the username.
     */
    private String username;

    /**
     * Get the password.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the username.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Initialize.
     */
    @PostConstruct
    public void initialize() {
        populateBean.populate();
    }

    /**
     * Execute the login.
     */
    public void login() {
        Credential credential = new UsernamePasswordCredential(username, new Password(password));
        AuthenticationStatus status = securityContext.authenticate(
                (HttpServletRequest) externalContext.getRequest(),
                (HttpServletResponse) externalContext.getResponse(),
                withParams().credential(credential));

        if (status.equals(SEND_CONTINUE)) {
            facesContext.responseComplete();
        } else if (status.equals(SEND_FAILURE)) {
            facesContext.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Authentication failed", null));
        }
    }

    /**
     * Set the password.
     *
     * @param password the password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Set the username.
     *
     * @param username the username.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
