package pdp.uz.codingbat.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "result")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id",nullable = false)
    private Question question;

    @Column(name = "response",nullable = false)
    private String response;

    @Column(name = "is_correct",nullable = false)
    private boolean isCorrect;
}
