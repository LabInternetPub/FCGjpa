package cat.tecnocampus.fgcstations.security.auth;

import cat.tecnocampus.fgcstations.security.configuration.JwtService;
import cat.tecnocampus.fgcstations.security.configuration.UserSecurityDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserSecurityDetailsService userSecurityDetailsService;

  public AuthenticationService(JwtService jwtService, AuthenticationManager authenticationManager, UserSecurityDetailsService userSecurityDetailsService) {
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
    this.userSecurityDetailsService = userSecurityDetailsService;
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    System.out.println("going to authenticate " + request.getUsername());
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    UserDetails userDetails = this.userSecurityDetailsService.loadUserByUsername(request.getUsername());
    var jwtToken = jwtService.generateToken(userDetails);
    AuthenticationResponse response = new AuthenticationResponse();
    response.setAccessToken(jwtToken);

    return response;
  }
}
