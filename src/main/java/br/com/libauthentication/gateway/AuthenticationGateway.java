package br.com.libauthentication.gateway;

import br.com.libauthentication.dto.request.UserLogin;
import br.com.libauthentication.dto.request.UserRegister;
import br.com.libauthentication.dto.response.Token;

public interface AuthenticationGateway {

    Boolean register(String url, UserRegister request);

    Token getToken(String url, UserLogin request);

    Boolean validateToken(String url, String token);

}
