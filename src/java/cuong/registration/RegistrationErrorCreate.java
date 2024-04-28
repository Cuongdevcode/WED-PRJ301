/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuong.registration;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class RegistrationErrorCreate implements Serializable{
    private String usernameLengthError;
    private String passwrodeLengthError;
    private String fullnameLengthError;
    private String usernameisexisted;
    private String confirmNotMatched;

    public RegistrationErrorCreate() {
    }

    public String getUsernameLengthError() {
        return usernameLengthError;
    }

    public void setUsernameLengthError(String usernameLengthError) {
        this.usernameLengthError = usernameLengthError;
    }

    public String getPasswrodeLengthError() {
        return passwrodeLengthError;
    }

    public void setPasswrodeLengthError(String passwrodeLengthError) {
        this.passwrodeLengthError = passwrodeLengthError;
    }

    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    public String getUsernameisexisted() {
        return usernameisexisted;
    }

    public void setUsernameisexisted(String usernameisexisted) {
        this.usernameisexisted = usernameisexisted;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }
    
    
    
    
}
