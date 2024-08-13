package com.effective.mobile.tasks.repository;

import com.effective.mobile.tasks.models.Priority;
import com.effective.mobile.tasks.models.Status;
import com.effective.mobile.tasks.models.Task;
import com.effective.mobile.tasks.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Modifying
    @Transactional
    int deleteByIdAndAuthor(Long id, User author);
    boolean existsByIdAndAuthor(Long id, User author);

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.title = :title WHERE t.id = :id")
    void updateTitleWhereId(String title, Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.description = :description WHERE t.id = :id")
    void updateDescriptionWhereId(String description, Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.status = :status WHERE t.id = :id")
    void updateStatusWhereId(Status status, Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.priority = :priority WHERE t.id = :id")
    void updatePriorityWhereId(Priority priority, Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Task t SET t.assignee = :assignee WHERE t.id = :id")
    void updateAssigneeWhereId(String assignee, Long id);
}
