package com.benjamin.smarterp.configuration;

import com.benjamin.smarterp.repository.jpa.*;
import com.benjamin.smarterp.security.JpaUserDetailsManager;
import com.benjamin.smarterp.security.oauth2.JpaOAuth2AuthorizationConsentService;
import com.benjamin.smarterp.security.oauth2.JpaRegisterClientRepository;
import com.benjamin.smarterp.security.oauth2.OAuth2AuthorizationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationCodeRequestAuthenticationProvider;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfiguration {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

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
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        http.csrf(csrf -> csrf.disable())
                .authorizeExchange(authorizeExchange -> authorizeExchange.pathMatchers("/login","/csrf","/static/**","**.png").permitAll().anyExchange().authenticated())
                .oauth2ResourceServer(oauth2ResourceServer ->oauth2ResourceServer.jwt(Customizer.withDefaults())).cors(cors -> cors.configurationSource(corsConfigurationSource()));
        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/login","/csrf","/static/**","**.png").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .oauth2ResourceServer(resource->resource.jwt(Customizer.withDefaults()))
//                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .cors((cors) -> cors.configurationSource(corsConfigurationSource()));
//        return http.build();
//    }

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
//        if(!userDetailsManager.userExists("admin")){
//            userDetailsManager.createUser(User.withUsername("admin").password("huang1100").roles("ADMIN").build());
//
//            Personnel personnel = personnelRepository.save(Personnel.builder().realName("黄文杰").email("xywenjie@outlook.com").build());
//            Optional<UserLogin> optional = userLoginRepository.findByUsername("admin");
//            if(optional.isPresent()){
//            	UserLogin userInfo = optional.get();
//                userInfo.setPersonnel(personnel);
//                userLoginRepository.save(userInfo);
//            }
//        }
        return userDetailsManager;
    }

//    @Bean
//    public RegisteredClientRepository registeredClientRepository(){
//        RegisteredClient odicClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("oidc-client")
//                .clientSecret("{noop}secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.NONE)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .redirectUri("http://localhost:5173")
//                .postLogoutRedirectUri("http://localhost:5173/")
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//        return new InMemoryRegisteredClientRepository(odicClient);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder(){
        return NimbusReactiveJwtDecoder.withPublicKey(this.rsaPublicKey).build();
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
    
    //OAuth2
    @Bean
    public RegisteredClientRepository registeredClientRepository(ClientRepository clientRepository,ObjectMapper objectMapper) {
    	return new JpaRegisterClientRepository(clientRepository, objectMapper);
    }
    
//    @Bean
//    public OAuth2AuthorizationService authorizationService(AuthorizationRepository authorizationRepository,
//    		RegisteredClientRepository registeredClientRepository,
//    		ObjectMapper objectMapper) {
//    	return new OAuth2AuthorizationServiceImpl(authorizationRepository, registeredClientRepository, objectMapper);
//    }
    
    @Bean
    public OAuth2AuthorizationConsentService authorizationConsentService(AuthorizationConsentRepository authorizationConsentRepository,
    		RegisteredClientRepository registeredClientRepository) {
    	return new JpaOAuth2AuthorizationConsentService(authorizationConsentRepository, registeredClientRepository);
    }
    
    @Bean("authorizationCodeRequestAuthenticationProider")
    public OAuth2AuthorizationCodeRequestAuthenticationProvider authorizationCodeRequestAuthenticationProider(
    		RegisteredClientRepository registeredClientRepository,OAuth2AuthorizationService authorizationService,
    		OAuth2AuthorizationConsentService authorizationConsentService) {
    	return new OAuth2AuthorizationCodeRequestAuthenticationProvider(registeredClientRepository, authorizationService, authorizationConsentService);
    }


}
