package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.QuestionMaster;
import com.mycompany.myapp.repository.QuestionMasterRepository;
import com.mycompany.myapp.service.QuestionMasterService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link QuestionMaster}.
 */
@Service
public class QuestionMasterServiceImpl implements QuestionMasterService {

    private final Logger log = LoggerFactory.getLogger(QuestionMasterServiceImpl.class);

    private final QuestionMasterRepository questionMasterRepository;

    public QuestionMasterServiceImpl(QuestionMasterRepository questionMasterRepository) {
        this.questionMasterRepository = questionMasterRepository;
    }

    @Override
    public QuestionMaster save(QuestionMaster questionMaster) {
        log.debug("Request to save QuestionMaster : {}", questionMaster);
        return questionMasterRepository.save(questionMaster);
    }

    @Override
    public Optional<QuestionMaster> partialUpdate(QuestionMaster questionMaster) {
        log.debug("Request to partially update QuestionMaster : {}", questionMaster);

        return questionMasterRepository
            .findById(questionMaster.getId())
            .map(
                existingQuestionMaster -> {
                    if (questionMaster.getQuestionId() != null) {
                        existingQuestionMaster.setQuestionId(questionMaster.getQuestionId());
                    }
                    if (questionMaster.getQuestion() != null) {
                        existingQuestionMaster.setQuestion(questionMaster.getQuestion());
                    }
                    if (questionMaster.getType() != null) {
                        existingQuestionMaster.setType(questionMaster.getType());
                    }
                    if (questionMaster.getComplexity() != null) {
                        existingQuestionMaster.setComplexity(questionMaster.getComplexity());
                    }
                    if (questionMaster.getExperienceFrom() != null) {
                        existingQuestionMaster.setExperienceFrom(questionMaster.getExperienceFrom());
                    }
                    if (questionMaster.getExperienceTo() != null) {
                        existingQuestionMaster.setExperienceTo(questionMaster.getExperienceTo());
                    }
                    if (questionMaster.getCategory() != null) {
                        existingQuestionMaster.setCategory(questionMaster.getCategory());
                    }
                    if (questionMaster.getReference() != null) {
                        existingQuestionMaster.setReference(questionMaster.getReference());
                    }
                    if (questionMaster.getAudiolink() != null) {
                        existingQuestionMaster.setAudiolink(questionMaster.getAudiolink());
                    }
                    if (questionMaster.getSkillId() != null) {
                        existingQuestionMaster.setSkillId(questionMaster.getSkillId());
                    }

                    return existingQuestionMaster;
                }
            )
            .map(questionMasterRepository::save);
    }

    @Override
    public Page<QuestionMaster> findAll(Pageable pageable) {
        log.debug("Request to get all QuestionMasters");
        return questionMasterRepository.findAll(pageable);
    }

    @Override
    public Optional<QuestionMaster> findOne(String id) {
        log.debug("Request to get QuestionMaster : {}", id);
        return questionMasterRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete QuestionMaster : {}", id);
        questionMasterRepository.deleteById(id);
    }
}
