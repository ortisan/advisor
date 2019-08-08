package br.com.ortiz.security.filters;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import br.com.ortiz.security.model.User;
import br.com.ortiz.security.services.JwtService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private JwtService jwtUtil;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, ApplicationContext ctx) {
        super(authenticationManager);
         this.jwtUtil = ctx.getBean(JwtService.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if(authentication == null) {
            chain.doFilter(request, response);
            return;
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtService.TOKEN_HEADER);
        if (StringUtils.isNotEmpty(token) && token.startsWith(JwtService.TOKEN_PREFIX)) {
            Optional<User> userFromToken = jwtUtil.getUserFromToken(token);
            if (userFromToken.isPresent()) {
                return new UsernamePasswordAuthenticationToken(userFromToken.get().getId(), null, null);
            }
        }
        return null;
    }
}