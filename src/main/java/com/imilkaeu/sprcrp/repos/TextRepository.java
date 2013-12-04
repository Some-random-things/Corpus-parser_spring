package com.imilkaeu.sprcrp.repos;

import com.imilkaeu.sprcrp.models.Text;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by imilka on 05.12.13.
 */
public interface TextRepository extends JpaRepository<Text, Long> {
}
