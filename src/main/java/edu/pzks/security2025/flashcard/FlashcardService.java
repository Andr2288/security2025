package edu.pzks.security2025.flashcard;

/*
    @author    chorn
    @project   security2025
    @class     FlashcardService
    @version   1.0.0
    @since     24.09.2025 - 23:37
*/

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FlashcardService {

    private final FlashcardRepository repository;

    @PostConstruct
    void init() {

        if (repository.count() < 5) {

            repository.deleteAll();

            List<Flashcard> flashcards = new ArrayList<>();

            flashcards.add(new Flashcard("Hello", "Привіт", "Greeting word in English"));
            flashcards.add(new Flashcard("Good morning", "Доброго ранку", "Morning greeting"));
            flashcards.add(new Flashcard("Thank you", "Дякую", "Expression of gratitude"));
            flashcards.add(new Flashcard("Please", "Будь ласка", "Polite request word"));
            flashcards.add(new Flashcard("Goodbye", "До побачення", "Farewell greeting"));

            repository.saveAll(flashcards);
        }
    }

    public List<Flashcard> getAll() {
        return repository.findAll();
    }

    public Flashcard getById(String id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public Flashcard create(Flashcard flashcard) {
        return repository.save(flashcard);
    }

    public Flashcard update(Flashcard flashcard) {
        return repository.save(flashcard);
    }
}
