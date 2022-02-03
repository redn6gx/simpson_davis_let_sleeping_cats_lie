package com.revature.servlets;

import com.revature.annotations.URI;
import com.revature.controllers.CatController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@URI(urlPatterns = {"/cats/*"})
public class CatServlet extends HttpServlet {

    private final CatController controller;

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
        String[] uriTokens = request.getRequestURI().split("/");

        switch(uriTokens.length) {
            case 0:
            case 1:
            case 2: {
                response.sendError(404);
                break;
            }
            case 3: {
                if(!(request.getQueryString() == null) && "cats".equals(uriTokens[2])) {
                    // we have query parameter
                    response.sendError(501);
                } else if(request.getQueryString() == null && "cats".equals(uriTokens[2])) {
                    // we have a request for all cats
                    this.controller.getAllCats(request, response);
                } else {
                    response.sendError(400);
                }
                break;
            }
            case 4: {
                if(uriTokens[3].matches("[0-9]+")) {
                    // we have a request for a specific cat
                    request.setAttribute("id", Integer.parseInt(uriTokens[3]));
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

    /**
     * This method handles POST requests made to the cats uri. It parses the url to determine which
     * controller method needs to be called.
     *
     * @param request                  the request to handle
     * @param response                 the response to return to the client
     * @throws IOException             thrown when sending an error response fails
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] uriTokens = request.getRequestURI().split("/");

        switch(uriTokens.length) {
            case 0:
            case 1:
            case 2: {
                response.sendError(404);
                break;
            }
            case 3: {
                if(request.getQueryString() == null && "cats".equals(uriTokens[2])) {
                    this.controller.createCat(request, response);
                } else {
                    response.sendError(400);
                }
                break;
            }
            default: {
                response.sendError(400);
                break;
            }
        }
    }

    /**
     * This method handles PUT requests made to the cats uri. It parses the url to determine which
     * controller method needs to be called.
     *
     * @param request                  the request to handle
     * @param response                 the response to return to the client
     * @throws IOException             thrown when sending an error response fails
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] uriTokens = request.getRequestURI().split("/");

        switch(uriTokens.length) {
            case 0:
            case 1:
            case 2: {
                response.sendError(404);
                break;
            }
            case 4: {
                if(request.getQueryString() == null && "cats".equals(uriTokens[2]) && uriTokens[3].matches("[0-9]+")) {
                        request.setAttribute("id", Integer.parseInt(uriTokens[3]));
                        this.controller.updateCat(request, response);
                } else {
                    response.sendError(400);
                }
                break;
            }
            default: {
                response.sendError(400);
                break;
            }
        }
    }

    /**
     * This method handles DELETE requests made to the cats uri. It parses the url to determine which
     * controller method needs to be called.
     *
     * @param request                  the request to handle
     * @param response                 the response to return to the client
     * @throws IOException             thrown when sending an error response fails
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] uriTokens = request.getRequestURI().split("/");

        switch(uriTokens.length) {
            case 0:
            case 1:
            case 2: {
                response.sendError(404);
                break;
            }
            case 4: {
                if(request.getQueryString() == null && "cats".equals(uriTokens[2]) && uriTokens[3].matches("[0-9]+")) {
                    request.setAttribute("id", Integer.parseInt(uriTokens[3]));
                    this.controller.deleteCat(request, response);
                } else {
                    response.sendError(400);
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
