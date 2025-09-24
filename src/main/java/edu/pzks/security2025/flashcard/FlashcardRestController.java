package edu.pzks.security2025.flashcard;

/*
    @author    chorn
    @project   security2025
    @class     FlashcardRestController
    @version   1.0.0
    @since     24.09.2025 - 23:37
*/

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flashcards")
@AllArgsConstructor
public class FlashcardRestController {

    private final FlashcardService service;

    @GetMapping
    public List<Flashcard> getItems() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Flashcard getOneItem(@PathVariable String id) {
        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.deleteById(id);
    }

    @PostMapping
    public Flashcard create(@RequestBody Flashcard item) {
        return service.create(item);
    }

    @PutMapping
    public Flashcard update(@RequestBody Flashcard item) {
        return service.update(item);
    }
}
