package by.fedorenko.repository;

import by.fedorenko.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskJpaRepository extends JpaRepository<Task, Long> {
}
