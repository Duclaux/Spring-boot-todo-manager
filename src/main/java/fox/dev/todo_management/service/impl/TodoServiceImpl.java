package fox.dev.todo_management.service.impl;

import fox.dev.todo_management.dto.TodoDto;
import fox.dev.todo_management.entity.Todo;
import fox.dev.todo_management.exception.ResourceNotFoundException;
import fox.dev.todo_management.repository.TodoRepository;
import fox.dev.todo_management.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        TodoDto savecTodoDto = modelMapper.map(savedTodo, TodoDto.class);
        return savecTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sorry, but there isn't exist todo with this ID: " + id));
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> allTodos = todoRepository.findAll();

        return allTodos.stream()
                .map((todo) -> modelMapper.map(todo, TodoDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(Long id, TodoDto todoDto) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sorry, but there isn't exist todo with this ID: " + id));

        todo.setTodoTitle(todoDto.getTodoTitle());
        todo.setTodoDescription(todoDto.getTodoDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo saveTodo = todoRepository.save(todo);
        return modelMapper.map(saveTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sorry, but there isn't exist todo with this ID: " + id));
        todoRepository.delete(todo);
    }
}
