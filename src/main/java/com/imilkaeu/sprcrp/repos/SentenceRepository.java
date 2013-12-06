package com.imilkaeu.sprcrp.repos;

import com.imilkaeu.sprcrp.models.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: imilka
 * Date: 06.12.13
 * Time: 11:05
 */
public interface SentenceRepository extends JpaRepository<Sentence, Long> {
}
