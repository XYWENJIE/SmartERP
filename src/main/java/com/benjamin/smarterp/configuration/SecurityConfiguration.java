package com.benjamin.smarterp.configuration;

import com.benjamin.smarterp.domain.entity.Personnel;
import com.benjamin.smarterp.domain.entity.UserLogin;
import com.benjamin.smarterp.repository.jpa.*;
import com.benjamin.smarterp.security.JpaUserDetailsManager;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Value("${jwt.public.key}")
    private RSAPublicKey rsaPublicKey;

    @Value("${jwt.private.key}")
    private RSAPrivateKey rsaPrivateKey;

//    @Bean
//    @Order(1)
//    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception{
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(Customizer.withDefaults());
//        http.oauth2ResourceServer((oauth2)->oauth2.jwt(Customizer.withDefaults()));
//        http.exceptionHandling((exceptions) ->
//                        exceptions.defaultAuthenticationEntryPointFor(new BasicAuthenticationEntryPoint(),new MediaTypeRequestMatcher(MediaType.TEXT_HTML)));
//        return http.cors(Customizer.withDefaults()).build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/login","/csrf","/static/**","**.png").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(resource->resource.jwt(Customizer.withDefaults()))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors((cors) -> cors.configurationSource(corsConfigurationSource()));
        return http.build();
    }

    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173/"));
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowedHeaders(List.of("content-type","Authorization"));
        corsConfiguration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }


    @Bean
    public UserDetailsManager userDetailsManager(UserLoginRepository userLoginRepository,
                                                 AuthoritiesRepository authoritiesRepository,
                                                 PasswordEncoder passwordEncoder, PersonnelRepository personnelRepository,
                                                 UserLoginHistoryRepository userLoginHistoryRepository,
                                                 UserLoginPasswordHistoryRepository userLoginPasswordHistoryRepository){
        JpaUserDetailsManager userDetailsManager = new JpaUserDetailsManager(
                userLoginRepository,authoritiesRepository,passwordEncoder,userLoginHistoryRepository,userLoginPasswordHistoryRepository);
        if(!userDetailsManager.userExists("admin")){
            userDetailsManager.createUser(User.withUsername("admin").password("huang1100").roles("ADMIN").build());
            Personnel personnel = personnelRepository.save(Personnel.builder().name("黄文杰").email("xywenjie@outlook.com").build());
            Optional<UserLogin> optional = userLoginRepository.findByUsername("admin");
            if(optional.isPresent()){
            	UserLogin userInfo = optional.get();
                userInfo.setPersonnel(personnel);
                userLoginRepository.save(userInfo);
            }
        }
        return userDetailsManager;
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(){
        RegisteredClient odicClient = RegisteredClient.withId(UUID.randomUUID().toString())
                .clientId("oidc-client")
                .clientSecret("{noop}secret")
                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:5173")
                .postLogoutRedirectUri("http://localhost:5173/")
                .scope(OidcScopes.OPENID)
                .scope(OidcScopes.PROFILE)
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
        return new InMemoryRegisteredClientRepository(odicClient);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(this.rsaPublicKey).build();
    }

    @Bean
    public JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(this.rsaPublicKey).privateKey(this.rsaPrivateKey).build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

    public static void main(String[] args) {
        String privateKeyPath;
        String publicKeyPath;
        KeyPair keyPair;
        try{
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
            String privateKeyPem = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            String publicKeyPem = Base64.getEncoder().encodeToString(publicKey.getEncoded());

            log.info("privateKey:{}",privateKeyPem);
            log.info("publicKeyPem:{}",publicKeyPem);
            log.info("privateKeyPem:{}",formatKey(privateKeyPem,"RSA PRIVATE KEY"));
            log.info("publicKeyPem:{}",formatKey(publicKeyPem,"PUBLIC KET"));
        }catch (Exception exception){
            log.error(exception.getMessage());
        }
    }

    public static String formatKey(String key,String type) throws Exception{
        byte[] keyBytes = Base64.getDecoder().decode(key);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        if("RSA PRIVATE KEY".equals(type)){
            Key privateKey =  keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));
            return "-----BEGIN "+type+"-----\n"+Base64.getEncoder().encodeToString(privateKey.getEncoded()) + "\n-----END "+type+"-----";
        } else if ("PUBLIC KEY".equals(type)) {
            Key publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));
            return "-----BEGIN "+type+"-----\n" + Base64.getEncoder().encodeToString(publicKey.getEncoded())+"\n-----END "+type+"-----";
        }else {
            throw new Exception("Unsupporte key type"+type);
        }
    }


}
