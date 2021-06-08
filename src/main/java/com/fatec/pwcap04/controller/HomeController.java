package com.fatec.pwcap04.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	
	@GetMapping("/")
	public ModelAndView menu() {
		return new ModelAndView ("paginaMenu");
	}
	
	@GetMapping("/login")
	public ModelAndView autenticacao() {
		return new ModelAndView ("paginaLogin");
	}
	
	//@GetMapping("/veiculos")
	// ModelAndView veiculo() {
	//	return new ModelAndView ("consultarVeiculo");
	//}
	
	//@GetMapping("/veiculos/cadastrarVeiculo")
	//public ModelAndView cadastroVeiculo() {
	//	return new ModelAndView ("cadastrarEditarVeiculo");
	//}
	
	//@GetMapping("/clientes")
	//public ModelAndView cliente() {
	//	return new ModelAndView ("consultarCliente");
	//}
	
	//@GetMapping("/clientes/cadastrarCliente")
	//public ModelAndView cadastroClientes() {
	//	return new ModelAndView ("cadastrarCliente");
	//}
	
	//@GetMapping("/locaDev")
	//public ModelAndView locacoesDevolucoes() {
	//	return new ModelAndView ("locaDev");
	//}
	
	//@GetMapping("/locacoesDevolucoes/cadastroEdicaoLocaDev")
	//public ModelAndView cadastroLocacoesDevolucoes() {
	//	return new ModelAndView ("cadastrarLocacao");
	//}
	
	/*@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest request, Authentication authentication) {
	 logger.info("Logout para o usuario: " + authentication.getName());
	 HttpSession session= request.getSession(false);
	 SecurityContextHolder.clearContext();
	 if(session != null) {
	 session.invalidate();
	 }
	 return new ModelAndView("paginaLogin");
	}*/
}
