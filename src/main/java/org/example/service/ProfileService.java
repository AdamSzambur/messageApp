package org.example.service;

import org.example.database.DatabaseClass;
import org.example.model.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProfileService {

    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public List<Profile> getProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String name) {
        return profiles.get(name);
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(),profile);
        return profile;
    }

    public Profile removeProfile(String name) {
        return profiles.remove(name);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size()+1L);
        profiles.put(profile.getProfileName(),profile);
        return profile;
    }
}
