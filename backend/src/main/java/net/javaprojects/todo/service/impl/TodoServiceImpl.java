package net.javaprojects.todo.service.impl;

import lombok.AllArgsConstructor;
import net.javaprojects.todo.dto.TodoDto;
import net.javaprojects.todo.entity.Todo;
import net.javaprojects.todo.exception.ResourceNotFoundException;
import net.javaprojects.todo.repository.TodoRepository;
import net.javaprojects.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {


    private TodoRepository todoRepository;

    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        //convert TodoDto into Todo Jpa entity
        //Todo todo = new Todo();
        //todo.setTitle(todoDto.getTitle());
       // todo.setDescription((todoDto.getDescription()));
        //todo.setCompleted(todoDto.isCompleted());

        Todo todo = modelMapper.map(todoDto,Todo.class);
        // Todo jpa entity
        Todo savedTodo = todoRepository.save(todo);
        //Convert saved Todo Jpa entity object into TodoDto object
        //TodoDto savedTodoDto = new TodoDto();
        //savedTodoDto.setId(savedTodo.getId());
        //savedTodoDto.setTitle(savedTodo.getTitle());
        //savedTodoDto.setDescription(savedTodo.getDescription());
        //savedTodoDto.setCompleted(savedTodo.isCompleted());

        TodoDto savedTodoDto = modelMapper.map(savedTodo,TodoDto.class);

        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not Found with id:" +id));

        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {

        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((todo) -> modelMapper.map(todo,TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not found with id:"+id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Todo not found with id:"+id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not found with id:"+id));
        todo.setCompleted(Boolean.TRUE);

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Todo not found with id:"+id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);


        return modelMapper.map(updatedTodo, TodoDto.class);
    }


}

