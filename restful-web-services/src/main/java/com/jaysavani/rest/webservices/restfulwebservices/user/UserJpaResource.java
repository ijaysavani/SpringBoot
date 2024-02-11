package com.jaysavani.rest.webservices.restfulwebservices.user;

import com.jaysavani.rest.webservices.restfulwebservices.jpa.PostRepository;
import com.jaysavani.rest.webservices.restfulwebservices.jpa.UserRepository;
import com.jaysavani.rest.webservices.restfulwebservices.user.User;
import com.jaysavani.rest.webservices.restfulwebservices.user.UserDaoService;
import com.jaysavani.rest.webservices.restfulwebservices.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class UserJpaResource {


    @Autowired
    private UserRepository repository;



    @Autowired
    private PostRepository postRepository;



    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers(){
        return repository.findAll();
    }


    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id){
        Optional<User> user = repository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id : " + id );
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }


    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){

        User savedUser =  repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


    @DeleteMapping (path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable Integer id){
        repository.deleteById(id);
    }


    @GetMapping (path = "/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable Integer id){

        Optional<User> user = repository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id : " + id );
        }

        return user.get().getPosts();
    }



    @PostMapping (path = "/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostsForUser(@PathVariable Integer id, @Valid @RequestBody Post post){

        Optional<User> user = repository.findById(id);

        if(user.isEmpty()){
            throw new UserNotFoundException("id : " + id );
        }

        post.setUser(user.get());

        Post savedPost = postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
                buildAndExpand(savedPost.getId()).toUri();

        return ResponseEntity.created(location).build();
    }


}
