package com.fatec.pwcap04.servico;

import org.springframework.web.servlet.ModelAndView;
import com.fatec.pwcap04.model.Cliente;

public interface ClienteServico {
	public Iterable<Cliente> findAll();

	public Cliente findByCpf(String cpf);

	public void deleteById(Long id);

	public Cliente findById(Long id);

	public ModelAndView saveOrUpdate(Cliente cliente);
	
	public String sendMail(Cliente cliente); 
}