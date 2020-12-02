package br.com.libauthentication.service.impl;

import br.com.libauthentication.dto.request.UserLogin;
import br.com.libauthentication.dto.request.UserRegister;
import br.com.libauthentication.dto.response.Token;
import br.com.libauthentication.gateway.AuthenticationGateway;
import br.com.libauthentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationGateway gateway;

    @Override
    public Boolean register(String url, UserRegister request) {
        return gateway.register(url, request);
    }

    @Override
    public Token getToken(String url, UserLogin request) {
        return gateway.getToken(url, request);
    }

    @Override
    public Boolean validateToken(String url, String token) {
        return gateway.validateToken(url, token);
    }
}
