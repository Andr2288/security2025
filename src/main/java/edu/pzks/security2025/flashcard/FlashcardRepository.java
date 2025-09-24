package edu.pzks.security2025.flashcard;

/*
    @author    chorn
    @project   security2025
    @class     FlashcardRepository
    @version   1.0.0
    @since     24.09.2025 - 23:37
*/

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashcardRepository extends MongoRepository<Flashcard, String> {
}
