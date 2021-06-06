package it.uniroma3.siw.spring.controller.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Opera;
import it.uniroma3.siw.spring.service.OperaService;




@Component
public class OperaValidator implements Validator {
	@Autowired
	private OperaService operaService;
	
    private static final Logger logger = LoggerFactory.getLogger(OperaValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "year", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");
		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			Opera opera=(Opera)o;
			if (opera.getPicture()==null)
				errors.reject("picture");
			if (this.operaService.alreadyExists((Opera)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
				logger.debug("e' un duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Opera.class.equals(aClass);
	}
}
