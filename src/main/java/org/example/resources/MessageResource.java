package org.example.resources;
import org.example.model.Message;
import org.example.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
        return messageService.getMessage(id);
    }

    @POST

    public Message addMessage(Message message) {
        return messageService.addMessage(message);
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


    // redirect to comments resource
    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }

}
