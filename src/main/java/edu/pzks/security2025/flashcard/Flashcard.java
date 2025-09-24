package edu.pzks.security2025.flashcard;

/*
    @author    chorn
    @project   security2025
    @class     Flashcard
    @version   1.0.0
    @since     24.09.2025 - 23:37
*/

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Flashcard {

    @Id
    private String id;
    private String text;
    private String translation;
    private String explanation;

    public Flashcard(String text, String translation, String explanation) {
        this.text = text;
        this.translation = translation;
        this.explanation = explanation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Flashcard flashcard = (Flashcard) o;
        return Objects.equals(id, flashcard.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
