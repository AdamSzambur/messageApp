package org.example.resources;
import org.example.model.Message;
import org.example.model.Profile;
import org.example.service.MessageService;
import org.example.service.ProfileService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {

    ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles() {
        return profileService.getProfiles();
    }

    @GET
    @Path("/{name}")
    public Profile getProfile(@PathParam("name") String name) {
        return profileService.getProfile(name);
    }

    @POST
    public Profile addProfile(Profile profile) {
        return profileService.addProfile(profile);
    }

    @PUT
    @Path("/{name}")
    public Profile updateProfile(@PathParam("name") String name, Profile profile) {
        profile.setProfileName(name);
        return  profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{name}")
    public Profile deleteProfile(@PathParam("name") String name) {
        return profileService.removeProfile(name);
    }
}
