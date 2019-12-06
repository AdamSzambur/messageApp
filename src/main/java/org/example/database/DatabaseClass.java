package org.example.database;

import org.example.model.Message;
import org.example.model.Profile;

import java.util.HashMap;
import java.util.Map;

public class DatabaseClass {
    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    static  {
        messages.put(1L, new Message(1L,"Hello World !", "Adam"));
        messages.put(2L, new Message(2L,"Hello Wroclaw", "Adamus"));
        profiles.put("Szambur", new Profile(1L, "Szambur", "Adam", "Szamburski"));
        profiles.put("Aga", new Profile(2L, "Aga", "Agnieszka", "Szamburska"));
    }

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }
}
