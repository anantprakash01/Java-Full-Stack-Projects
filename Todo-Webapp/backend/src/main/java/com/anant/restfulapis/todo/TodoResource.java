package com.anant.restfulapis.todo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class TodoResource {

	private TodoService todoService;
	
	
	
	public TodoResource(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	


	@GetMapping(path="/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username) {
		return todoService.findByUsername(username);
	}
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username,@PathVariable int id) {
		return todoService.findById(id);
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public void deleteById(@PathVariable String username,@PathVariable int id) {
		todoService.deleteById(id);
	}
	
	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable String username,@PathVariable int id ,
			@RequestBody Todo todo) {
		todoService.updateTodo(todo);
		return todo;
	}
	@PostMapping("/users/{username}/todos")
	public Todo createTodo(@PathVariable String username,
			@RequestBody Todo todo) {
		Todo createdTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
		return createdTodo;
	}
}
