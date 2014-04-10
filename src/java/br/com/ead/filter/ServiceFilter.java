package br.com.ead.filter;

import br.com.ead.service.ParseResource;
import java.io.IOException;
import java.util.Map;
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
import org.json.JSONObject;

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

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(true);

        if (!"/ead/webresources/connection".equals(request.getRequestURI())) {

            Map parameters = getParameters(request);

            String token = (String) session.getAttribute("token");

            if (null != token) {
                if (!token.equals((String) parameters.get("token"))) {
                    response.setStatus(401);
                    response.getOutputStream().println("The token is invalid");
                    return;
                }
            } else {
                response.setStatus(401);
                JSONObject error = new JSONObject();
                error.put("error", "The token is required");
                response.getOutputStream().println(error.toString(4));
                return;
            }

        }

        chain.doFilter(req, resp);

        String token = (String) req.getAttribute("token");
        if (token != null) {
            session.setAttribute("token", token);
        }

    }

    @Override
    public void destroy() {
    }

}
