package com.todoapp.Todoapp.controller;


import com.todoapp.Todoapp.entity.TodoEntity;
import com.todoapp.Todoapp.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class TodoController {

   private  final TodoRepository todoRepository;
    @GetMapping({"","/","/home"})
    public String showHomePage(Model model ){

        model.addAttribute("todos",todoRepository.findAll());

        todoRepository.findAll();
        return "index";

    }

    @PostMapping
    public String add(@RequestParam String title){
        TodoEntity newTodo = TodoEntity.builder()
                .title(title)
                .completed(false)
                .build();
        todoRepository.save(newTodo);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public  String update(@PathVariable Long id){
        TodoEntity existingTable= todoRepository.findById(id)
                .orElseGet(()-> new RuntimeException("Todo not found:"+id));

        existingTable.setCompleted(true);
        todoRepository.save(existingTable);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        TodoEntity existingTable= todoRepository.findById(id)
                .orElseGet(()-> new RuntimeException("Todo not found:"+id));
        todoRepository.delete(existingTable);
        return  "redirect:/";

    }
}
