package com.lordkadoc.bingo_back.tasks;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lordkadoc.bingo_back.party.Party;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    @Query("SELECT t from Task t WHERE t.party =:party OR t.party IS NULL")
    public List<Task> findAllByPartyIncludingDefault(Party party);

    public List<Task> findAllByParty(Party party);

}
