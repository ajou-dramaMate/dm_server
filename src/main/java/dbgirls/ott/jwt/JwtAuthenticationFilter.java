package dbgirls.ott.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
//@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final SessionAuthenticationStrategy sessionStrategy = new NullAuthenticatedSessionStrategy();
//    private final boolean continueChainBeforeSuccessfulAuthentication = false;
//    @Autowired

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        String username = obtainUsername(request);
        username = (username != null) ? username.trim() : "";
        String password = obtainPassword(request);
        password = (password != null) ? password : "";
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,
                password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

//    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        if(!requiresAuthentication(request, response)){
//            filterChain.doFilter(request, response);
//        }
//
//        if (!requiresAuthentication(request, response)) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            Authentication authenticationResult = attemptAuthentication(request, response);
//            if (authenticationResult == null) {
//                // return immediately as subclass has indicated that it hasn't completed
//                return;
//            }
//
//            this.sessionStrategy.onAuthentication(authenticationResult, request, response);
//            // Authentication success
//            if (this.continueChainBeforeSuccessfulAuthentication) {
//                filterChain.doFilter(request, response);
//            }
//            successfulAuthentication(request, response, filterChain, authenticationResult);
//        }
//        catch (InternalAuthenticationServiceException failed) {
//            this.logger.error("An internal error occurred while trying to authenticate the user.", failed);
//            unsuccessfulAuthentication(request, response, failed);
//        }
//        catch (AuthenticationException ex) {
//            // Authentication failed
//            unsuccessfulAuthentication(request, response, ex);
//        }

//    }
}
