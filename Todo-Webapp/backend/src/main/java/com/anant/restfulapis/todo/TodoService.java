package com.anant.restfulapis.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;



@Service
public class TodoService {
	private static List<Todo> todos = new ArrayList<>();
	private static int todosCount =0;
	
	static {
		todos.add(new Todo(++todosCount,"Anant",
				"Learn React for Frontend",LocalDate.now().plusYears(2),false ));
		todos.add(new Todo(++todosCount,"Anant",
				"Learn Spring Boot for Backend",LocalDate.now().plusYears(2),false ));
		todos.add(new Todo(++todosCount,"Anant",
				"Make Fullstack Projects",LocalDate.now().plusYears(2),false ));
	}
	
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate =
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
				
	}
	
	public Todo addTodo(String username, String description, LocalDate targetDate, Boolean done) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
		return todo;
	}
	
	public void deleteById(int id) {
		Predicate<? super Todo> predicate =
				todo -> todo.getId()==id;
		todos.removeIf(predicate);
	}
	
	public Todo findById(int id) {
		Predicate<? super Todo> predicte =
				todo -> todo.getId()==id;
		Todo todo = todos.stream().filter(predicte).findFirst().get();
		return todo;
	}
	
	public void updateTodo(Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}
