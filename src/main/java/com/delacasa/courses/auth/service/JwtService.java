package com.delacasa.courses.auth.service;

import com.delacasa.courses.auth.model.CustomAuthToken;
import com.delacasa.courses.auth.model.CustomPrincipal;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.Ed25519Signer;
import com.nimbusds.jose.crypto.Ed25519Verifier;
import com.nimbusds.jose.jwk.Curve;
import com.nimbusds.jose.jwk.OctetKeyPair;
import com.nimbusds.jose.jwk.gen.OctetKeyPairGenerator;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.time.Instant.now;


@Service
public class JwtService {

    public static final String JWT_PREFIX = "Bearer ";
    private static final int EXPIRATION_S = 3600;
    private static final String ISSUER = "Robert";

    private static final String CLAIM_ROLE = "roles";


    private final OctetKeyPair jwk = new OctetKeyPairGenerator(Curve.Ed25519)
            .keyID("123")
            .generate();

    private final JWSSigner signer = new Ed25519Signer(jwk);
    private final JWSVerifier verifier = new Ed25519Verifier(jwk.toPublicJWK());

    public JwtService() throws JOSEException {

    }

    public String createToken(CustomAuthToken authResult) {
        try {
            final JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(authResult.getPrincipal().username())
                    .issuer(ISSUER)
                    .issueTime(new Date())
                    .expirationTime(Date.from(now().plusSeconds(EXPIRATION_S)))
                    .claim(CLAIM_ROLE, authResult.getPrincipal().roles())
                    .build();
            final JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.EdDSA).keyID(jwk.getKeyID()).build();
            final SignedJWT jws = new SignedJWT(header, claims);
            jws.sign(signer);
            return jws.serialize();
        } catch (JOSEException jose) {
            jose.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Optional<SignedJWT> validate(String token) throws ParseException, JOSEException {
        final SignedJWT parsed = SignedJWT.parse(token.substring(JWT_PREFIX.length()));

        return parsed.verify(verifier) ? Optional.of(parsed) : Optional.empty();
    }

    public CustomAuthToken setUpAuthToken(SignedJWT jws) throws ParseException {
        final JWTClaimsSet claims = jws.getJWTClaimsSet();
        final CustomPrincipal principal = new CustomPrincipal(claims.getSubject(), setUpRoles(claims));
        return new CustomAuthToken(principal, null);
    }

    public List<String> setUpRoles(JWTClaimsSet claims) {
        final Object roles = claims.getClaim(CLAIM_ROLE);
        if (roles == null) return new ArrayList<>();
        return (List<String>) roles;
    }

}
