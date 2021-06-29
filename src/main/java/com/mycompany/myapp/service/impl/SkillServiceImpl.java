package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Skill;
import com.mycompany.myapp.repository.SkillRepository;
import com.mycompany.myapp.service.SkillService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Implementation for managing {@link Skill}.
 */
@Service
public class SkillServiceImpl implements SkillService {

    private final Logger log = LoggerFactory.getLogger(SkillServiceImpl.class);

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill save(Skill skill) {
        log.debug("Request to save Skill : {}", skill);
        return skillRepository.save(skill);
    }

    @Override
    public Optional<Skill> partialUpdate(Skill skill) {
        log.debug("Request to partially update Skill : {}", skill);

        return skillRepository
            .findById(skill.getId())
            .map(
                existingSkill -> {
                    if (skill.getSkill() != null) {
                        existingSkill.setSkill(skill.getSkill());
                    }
                    if (skill.getSkillDesc() != null) {
                        existingSkill.setSkillDesc(skill.getSkillDesc());
                    }
                    if (skill.getIcon() != null) {
                        existingSkill.setIcon(skill.getIcon());
                    }
                    if (skill.getLanguage() != null) {
                        existingSkill.setLanguage(skill.getLanguage());
                    }
                    if (skill.getType() != null) {
                        existingSkill.setType(skill.getType());
                    }

                    return existingSkill;
                }
            )
            .map(skillRepository::save);
    }

    @Override
    public List<Skill> findAll() {
        log.debug("Request to get all Skills");
        return skillRepository.findAll();
    }

    @Override
    public Optional<Skill> findOne(String id) {
        log.debug("Request to get Skill : {}", id);
        return skillRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Skill : {}", id);
        skillRepository.deleteById(id);
    }
}
