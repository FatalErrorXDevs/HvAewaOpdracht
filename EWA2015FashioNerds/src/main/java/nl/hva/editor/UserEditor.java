/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hva.editor;

import java.beans.PropertyEditorSupport;
import nl.hva.model.UserRole;
import nl.hva.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author FatalErrorX
 */
public class UserEditor extends PropertyEditorSupport{
    
    
    @Autowired 
    private UserService userService;
    
    @Override
    public void setAsText(String text){
            
//            UserRole role = this.userService;
    
    
                    }
}
