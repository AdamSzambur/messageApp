package org.example.service;
import org.example.database.DatabaseClass;
import org.example.model.Message;

import java.util.*;
import java.util.stream.Collectors;

public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();


    public List<Message> getMessagesForYear(final int year) {
        List<Message> result = new ArrayList<>();
        for (Message msg : messages.values()) {
            Calendar cal = Calendar.getInstance();
            if (cal.get(Calendar.YEAR) == year) {
                result.add(msg);
            }
        }
        return result;
    }

    public List<Message> getMessagesPaginated(int start, int size) {
        List<Message> list = new ArrayList<>(messages.values());
        if (start+size > list.size()) return new ArrayList<Message>();
        return list.subList(start, start+ size);
    }

    public List<Message> getMessages() {
        return new ArrayList<Message>(messages.values());
    }

    public Message getMessage(Long id) {
        return messages.get(id);
    }

    public Message updateMessage(Message message) {
        if (message.getId() <=0) {
            return null;
        }
        messages.put(message.getId(),message);
        return message;
    }

    public Message removeMessage(Long id) {
        return messages.remove(id);
    }

    public Message addMessage(Message message) {
        message.setId(messages.size()+1L);
        messages.put(message.getId(),message);
        return message;
    }
}
