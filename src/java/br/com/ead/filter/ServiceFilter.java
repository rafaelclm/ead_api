package br.com.ead.filter;

import br.com.ead.service.ParseResource;
import java.io.IOException;
import java.rmi.ServerException;
import java.util.Map;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.parse4j.ParseException;
import org.parse4j.ParseObject;
import org.parse4j.ParseQuery;

/**
 *
 * @author Rafael
 */
@WebFilter("/webresources/*")
public class ServiceFilter extends ParseResource implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        String apiKey = null;
        String institutionId = null;

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        Map parameters = getParameters(request);

        HttpSession session = request.getSession(true);
        
        String token = (String) session.getAttribute("token");
        boolean refresh = Boolean.parseBoolean((String) parameters.get("refresh"));

        if (token == null || refresh) {

            if ("GET".equals(request.getMethod())) {

                apiKey = (String) parameters.get("apiKey");
                institutionId = (String) parameters.get("institutionId");

            } else {

                apiKey = request.getHeader("apiKey");
                institutionId = request.getHeader("institutionId");

            }

            if (apiKey == null || institutionId == null) {
                response.setStatus(401);
                response.getOutputStream().println("The apiKey or institutionId value not found");
                return;
            }

            ParseQuery<ParseObject> query = ParseQuery.getQuery("Institution");
            query
                    .whereEqualTo("objectId", institutionId)
                    .whereEqualTo("apiKey", apiKey);

            try {

                if (query.find() == null) {
                    response.setStatus(401);
                    response.getOutputStream().println("The apiKey or institutionId is invalid");
                    return;
                }

                UUID uuid = UUID.randomUUID();
                response.getOutputStream().println(uuid.toString());
                session.setAttribute("token", uuid.toString());
                return;

            } catch (ParseException ex) {
                throw new ServerException(ex.getMessage());
            }

        } else {
            if (!token.equals((String) parameters.get("token"))) {
                response.setStatus(401);
                response.getOutputStream().println("The token is invalid");
                return;
            }
        }

        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {
    }

}
