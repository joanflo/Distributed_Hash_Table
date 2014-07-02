
package webservice;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 */
@Path("generic")
public class Peer {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public Peer() {
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("getXml")
    public String getXml() {
        return "Hola mundo!";
    }

    /**
     * PUT method for updating or creating an instance of Peer
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }
}
