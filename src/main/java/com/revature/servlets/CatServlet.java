package com.revature.servlets;

import com.revature.annotations.URI;
import com.revature.controllers.CatController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@URI(urlPatterns = {"/cats/*"})
public class CatServlet extends HttpServlet {

    private final CatController controller;

    private final static Logger logger = LogManager.getLogger(CatServlet.class);

    public CatServlet(CatController controller) {
        this.controller = controller;
    }

    /**
     * This method handles GET requests made to the cats uri. It parses the url to determine which
     * controller method needs to be called.
     *
     * @param request                  the request to handle
     * @param response                 the response to return to the client
     * @throws IOException             thrown when sending an error response fails
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] uri = request.getRequestURI().split("/");

        switch(uri.length) {
            case 0:
            case 1:
            case 2: {
                response.sendError(404);
                break;
            }
            case 3: {
                if(!(request.getQueryString() == null) && "cats".equals(uri[2])) {
                    // we have query parameter
                    response.sendError(501);
                } else if(request.getQueryString() == null && "cats".equals(uri[2])) {
                    // we have a request for all cats
                    this.controller.getAllCats(request, response);
                } else {
                    response.sendError(400);
                }
                break;
            }
            case 4: {
                if(uri[3].matches("[0-9]+")) {
                    // we have a request for a specific cat
                    request.setAttribute("id", uri[3]);
                    this.controller.getCatById(request, response);
                } else {
                    response.sendError(400, "Only id values are valid.");
                }
                break;
            }
            default: {
                response.sendError(400);
                break;
            }
        }
    }
}
