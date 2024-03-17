package com.concessionaria.projetoconcessionaria.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;

public class BindingResultValidation {
	
	private final BindingResult bindingResult;

	    public BindingResultValidation(BindingResult bindingResult) {
	        this.bindingResult = bindingResult;
	    }

	    public boolean hasErrors() {
	        return bindingResult.hasErrors();
	    }

	    public List<String> getErrors() {
	        return bindingResult.getAllErrors().stream()
	                .map(DefaultMessageSourceResolvable::getDefaultMessage)
	                .collect(Collectors.toList());
	    }
}
