package com.fatec.pwcap04.servico;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.fatec.pwcap04.model.Cliente;
import com.fatec.pwcap04.model.ClienteRepository;

@Service
public class ClienteServicoI implements ClienteServico {
	Logger logger = LogManager.getLogger(ClienteServicoI.class);

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private MailSender mailSender;

	public Iterable<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente findByCpf(String cpf) {
		return repository.findByCpf(cpf);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
		logger.info(">>>>>> 2. comando exclusao executado para o id => " + id);
	}

	public Cliente findById(Long id) {
		return repository.findById(id).get();
	}

	
	
	public ModelAndView saveOrUpdate(Cliente cliente) {
		ModelAndView modelAndView = new ModelAndView("consultarCliente");
		try {
			
			repository.save(cliente);
			 logger.info(">>>>>> 4. comando save executado ");
			 sendMail(cliente);
			 modelAndView.addObject("clientes", repository.findAll());
			 
		} catch (Exception e) {
			modelAndView.setViewName("cadastrarCliente");
			if (e.getMessage().contains("could not execute statement")) {
				modelAndView.addObject("message", "Dados invalidos - cliente já cadastrado.");
				logger.info(">>>>>> 5. cliente ja cadastrado ==> " + e.getMessage());
			} else {
				modelAndView.addObject("message", "Erro não esperado - contate o administrador");
				logger.error(">>>>>> 5. erro nao esperado ==> " + e.getMessage());
			}
		}
		return modelAndView;
	}
	
	
	
	public String sendMail(Cliente cliente) {
		 SimpleMailMessage message = new SimpleMailMessage();
		 message.setFrom("vision.contato.grupo@gmail.com");
		 message.setTo(cliente.getEmail());
		 message.setSubject("Confirmação do cadastro de cliente");
		 message.setText(cliente.toString());
		 try {
			 mailSender.send(message);
			 logger.info(">>>>>> 5. Envio do e-mail processado com sucesso.");
			 return "Email enviado";
		 } catch (Exception e) {
			 e.printStackTrace();
			 return "Erro ao enviar e-mail.";
		 }
	}
	
	

}
