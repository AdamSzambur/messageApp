package org.example.resources;
import org.example.exceptions.DataNotFoundException;
import org.example.model.Message;
import org.example.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@QueryParam("year") int year,
                                     @QueryParam("start") int start,
                                     @QueryParam("size") int size) {
        if (year > 0) {
            return messageService.getMessagesForYear(year);
        }
        if (size >=  0 && start >0) {
            return messageService.getMessagesPaginated(start,size);
        }
        return messageService.getMessages();
    }


    @GET
    @Path("/{id}")
    public Message getMessage(@PathParam("id") Long id) {
        Message message = messageService.getMessage(id);
        if (message == null) {
            throw new DataNotFoundException("Message with id "+ id + " not found");
        }
        return message;
    }

    @POST
    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message newMessage = messageService.addMessage(message);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(newMessage.getId())).build())
                .entity(newMessage)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Message updateMessage(@PathParam("id") Long id, Message message) {
        message.setId(id);
        return  messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{id}")
    public Message deleteMessage(@PathParam("id") Long id) {
        return messageService.removeMessage(id);
    }


    // redirect request to comments resource
    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }

}
