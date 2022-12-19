import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class ParseJwt {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NiIsInN1YiI6ImVhc3kiLCJpYXQiOjE2MjE1ODUxNzYsImNvbXBhbnlJZCI6IjEyMzQiLCJjb21wYW55TmFtZSI6ImF4In0.rEYqEnypFFHRm2VH7UvILr2pi36orJs_g_fs5dLAQDA";

        Claims claims = Jwts.parser().setSigningKey("asdf").parseClaimsJws(token).getBody();

        String companyId = (String) claims.get("companyId");
        String companyName = (String) claims.get("companyName");

    }
}
