package controllers;

import java.util.*;

import play.*;
import play.mvc.*;

import models.*;
import play.data.validation.Required;

public class Application extends Controller {

    public static void index() {
        Post frontPost = Post.find("order by postedAt desc").first();
        List<Post> olderPosts = Post.find(
                "order by postedAt desc"
        ).from(1).fetch(10);
        render(frontPost, olderPosts);
    }

    @Before
    static void addDefaults() {
        renderArgs.put("blogTitle", Play.configuration.getProperty("blog.title"));
        renderArgs.put("blogBaseline", Play.configuration.getProperty("blog.baseline"));
    }

    public static void show(Long id) {
        Post post = Post.findById(id);
        render(post);
    }
   public static void postComment(Long postId, @Required String author, @Required String content) {
    Post post = Post.findById(postId);
    if(validation.hasErrors()) {
        render("Application/show.html", post);
    }
    post.addComment(author, content);
    flash.success("Thanks for posting %s", author);
    show(postId);
}
}