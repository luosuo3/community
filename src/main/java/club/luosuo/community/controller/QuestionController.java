package club.luosuo.community.controller;

import club.luosuo.community.dto.QuestionDTO;
import club.luosuo.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("/question/{id}")
    public  String Question(@PathVariable(name = "id") Integer id , Model model) {

       QuestionDTO questionDTO= questionService.getById(id);
        model.addAttribute("question", questionDTO);

        return "question";
    }

}
