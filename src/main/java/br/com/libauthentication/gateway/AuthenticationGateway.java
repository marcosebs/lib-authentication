package br.com.libauthentication.gateway;

import br.com.libauthentication.dto.request.UserLogin;
import br.com.libauthentication.dto.request.UserRegister;
import br.com.libauthentication.dto.response.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class AuthenticationGateway {

    private RestTemplate restTemplate = new RestTemplate();

    public Boolean register(String url, UserRegister request) {
        log.info("Iniciando requisição de REGISTRO de usuário");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserRegister> httpEntity = new HttpEntity<>(request, headers);
        try {
            restTemplate.postForEntity(url + "/login/registrar", httpEntity, String.class);
            log.info("Sucesso ao cadastrar usuário");
            return true;
        }catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("ERRO: falha ao registrar usuário, " + e.getResponseBodyAsString());
            throw e;
        }
    }

    public Token getToken(String url, UserLogin request) {
        log.info("Iniciando requisição de LOGIN de usuário");
        HttpEntity<UserLogin> httpEntity = new HttpEntity<>(request);
        try {
            ResponseEntity<Token> response = restTemplate.postForEntity(url + "/login", httpEntity, Token.class);
            log.info("Sucesso ao logar usuário");
            return response.getBody();
        }catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("ERRO: falha ao logar usuário, " + e.getResponseBodyAsString());
            throw e;
        }
    }

    public Boolean validateToken(String url, String token) {
        log.info("Iniciando requisição de VALIDAÇÃO de token");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        try {
            restTemplate.exchange(url + "/api/secure", HttpMethod.GET, entity, String.class);
            log.info("Sucesso ao validar token");
            return true;
        }catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("ERRO: Token inválido " + e.getResponseBodyAsString());
            return false;
        }
    }

}
