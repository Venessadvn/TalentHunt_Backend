package com.code.api.reposatories;

import com.code.api.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    // You can add custom queries if needed, for example:
    // List<Vote> findByContestantId(int contestantId);
}
