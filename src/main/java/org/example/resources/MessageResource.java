package org.example.resources;
import org.example.exceptions.DataNotFoundException;
import org.example.model.Message;
import org.example.service.MessageService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    public List<Message> getMessages(@QueryParam("year") int year,
                                     @QueryParam("start") int start,
                                     @QueryParam("size") int size,
                                     @Context UriInfo uriInfo) {
        List<Message> messages;
        if (year > 0) {
            messages = messageService.getMessagesForYear(year);
        } else if (size > 0 && start >=0) {
            messages = messageService.getMessagesPaginated(start,size);
        } else {
            messages = messageService.getMessages();
        }
        // HATEOAS :)
        messages.forEach(message->addLinks(uriInfo,message));
        return messages;
    }


    @GET
    @Path("/{id}")
    public Message getMessage(@PathParam("id") Long id, @Context UriInfo uriInfo) {
        Message message = messageService.getMessage(id);
        if (message == null) {
            throw new DataNotFoundException("Message with id "+ id + " not found");
        }
        // HATEOAS :)
        addLinks(uriInfo, message);
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

    // redirect request to CommentResource
    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }


    // part for HATEOAS --------------------------------------------------

    private void addLinks(@Context UriInfo uriInfo, Message message) {
        message.addLink(getUriSelf(uriInfo, message),"self");
        message.addLink(getUriProfile(uriInfo, message),"profile");
        message.addLink(getUriComments(uriInfo, message),"comments");
    }
    private String getUriSelf(@Context UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(message.getId().toString())
                .build().toString();
    }

    private String getUriProfile(@Context UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build().toString();
    }

    private String getUriComments(@Context UriInfo uriInfo, Message message) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource")
                .path(CommentResource.class)
                .resolveTemplate("messageId", message.getId())
                .build().toString();
    }
}
