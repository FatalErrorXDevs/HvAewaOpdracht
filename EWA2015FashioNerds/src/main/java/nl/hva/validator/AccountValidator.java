package nl.hva.validator;

import nl.hva.model.User;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * This is the validator for the model Account.
 *
 * @version 1.0
 * @since 09-10-2015
 * @author Bert Kooij
 */
@Service
public class AccountValidator implements Validator {

//    @Autowired
//    private AccountService accountService; 
    @Override
    public boolean supports(Class<?> type) {
        return User.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User account = (User) o;

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "GebruikersNaam", "required.GebruikersNaam");
//        if (account.isNew()) {
//            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "Password", "required.Password");
//        }
//        
//        if (account.isNew() && accountService.findAllByUsername(account.getGebruikersNaam()).size() > 0) {
//            errors.rejectValue("GebruikersNaam", "notUnique.GebruikersNaam");
//        }
//
//        if (account.getRecht_Naam().equalsIgnoreCase("none")) {
//            errors.rejectValue("Recht_Naam", "required.Recht_Naam");
//        }
//        
//        if(account.getGebruikersNaam().length() < 5)
//        {
//            errors.rejectValue("GebruikersNaam", "validator.GebruikersNaam.short");
//        }
//        else if(account.getGebruikersNaam().length() > 50)
//        {
//            errors.rejectValue("GebruikersNaam", "validator.GebruikersNaam.long");
//        }
         
    }

}
