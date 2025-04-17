package com.polling.pollingapp.services;

import com.polling.pollingapp.model.OptionVote;
import com.polling.pollingapp.model.Poll;
import com.polling.pollingapp.repositories.PollRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PollService {
    private PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    @Transactional
    public Poll createPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Optional<Poll> getPoll(Long id) {
        return pollRepository.findById(id);
    }

    public void vote(Long pollId, int optionIndex){
        //Get poll from DB
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));

        // Get All options
        List<OptionVote> options = poll.getOptions();
        // If index for vote is not valid, throw error
        if(optionIndex < 0 || optionIndex >= options.size()){
            throw new IllegalArgumentException("Invalid option index");
        }

        // Get Selected Option
        OptionVote selectedOption = options.get(optionIndex);

        // Increment vote for selected option
        selectedOption.setVoteCount(selectedOption.getVoteCount() + 1);

        // Save incremented vote option into the database
        pollRepository.save(poll);
    }
}
