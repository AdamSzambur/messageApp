package org.example.service;

import org.example.database.DatabaseClass;
import org.example.model.Comment;
import org.example.model.Message;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class CommentService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public List<Comment> getComments(Long messageId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return new ArrayList<Comment>(comments.values());
    }

    public Comment getComment(Long messageId, Long commentId) {
        return messages.get(messageId).getComments().get(commentId);
    }

    public Comment updateComment(Long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        if (comment.getId() <=0) {
            return null;
        }
        comments.put(comment.getId(),comment);
        return comment;
    }

    public Comment removeComment(Long messageId, Long commentId) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        return comments.remove(commentId);
    }

    public Comment addComment(Long messageId, Comment comment) {
        Map<Long, Comment> comments = messages.get(messageId).getComments();
        comment.setId(comments.size()+1L);
        comments.put(comment.getId(),comment);
        return comment;
    }
}
