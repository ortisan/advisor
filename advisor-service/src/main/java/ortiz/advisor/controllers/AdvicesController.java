package ortiz.advisor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ortiz.advisor.model.dao.AdviceDao;
import ortiz.advisor.model.entity.Advice;

import java.util.List;

@RestController
public class AdvicesController {

    @Autowired
    private AdviceDao adviceDao;

    @RequestMapping("/advices")
    public List<Advice> geAllAdvices() {
        return adviceDao.getAll();
    }

    @PostMapping("/advices")
    public Advice postLastAdvices(@RequestBody Advice newAdvice) {
        adviceDao.addAdvice(newAdvice);
        return newAdvice;
    }

    @DeleteMapping("/advices")
    public void deleteAll() {
        adviceDao.removeAll();
    }
}