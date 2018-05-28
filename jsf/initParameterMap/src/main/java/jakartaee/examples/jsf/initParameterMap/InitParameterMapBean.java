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
package jakartaee.examples.jsf.initParameterMap;

import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.InitParameterMap;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A request scoped bean injecting the init parameter map.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Named(value = "initParameterMapBean")
@RequestScoped
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
public class InitParameterMapBean implements Serializable {
    
    /**
     * Stores the request cookie map.
     */
    @Inject
    @InitParameterMap
    private Map<String, String> initParameterMap;
    
    /**
     * Get the init parameter map.
     * 
     * @return the init parametere map.
     */
    public Map<String, String> getInitParameterMap() {
        return initParameterMap;
    }
}
