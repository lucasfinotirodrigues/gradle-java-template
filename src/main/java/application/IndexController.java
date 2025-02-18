package application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController 
@RequestMapping("/")
public class IndexController {

    // public List<String> frutas = Arrays.asList("Maça", "Banana", "Morango");
    private List<String> frutas = new ArrayList<String>();

    @GetMapping
    public List<String> home() {
        // return "home - Método GET";
        return frutas;
    }

    @PostMapping
    public List<String> homePost(@RequestBody String fruta) {
        frutas.add(fruta);
        return frutas;
        // "home - POST";
    }
}
