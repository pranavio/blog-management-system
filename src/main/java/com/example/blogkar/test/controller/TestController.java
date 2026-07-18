@RestController
public class TestController {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @GetMapping("/check")
    public String check() {
        return "Username = " + username +
                "\nJWT = " + jwtSecret;
    }
}