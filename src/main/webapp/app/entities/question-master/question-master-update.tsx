import React, { useState, useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity, updateEntity, createEntity, reset } from './question-master.reducer';
import { IQuestionMaster } from 'app/shared/model/question-master.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const QuestionMasterUpdate = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const questionMasterEntity = useAppSelector(state => state.questionMaster.entity);
  const loading = useAppSelector(state => state.questionMaster.loading);
  const updating = useAppSelector(state => state.questionMaster.updating);
  const updateSuccess = useAppSelector(state => state.questionMaster.updateSuccess);

  const handleClose = () => {
    props.history.push('/question-master' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(props.match.params.id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...questionMasterEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...questionMasterEntity,
          type: 'MCQ',
          complexity: 'ENTRY',
          category: 'GENERAL',
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="jhipsterTestApp.questionMaster.home.createOrEditLabel" data-cy="QuestionMasterCreateUpdateHeading">
            <Translate contentKey="jhipsterTestApp.questionMaster.home.createOrEditLabel">Create or edit a QuestionMaster</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="question-master-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('jhipsterTestApp.questionMaster.questionId')}
                id="question-master-questionId"
                name="questionId"
                data-cy="questionId"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterTestApp.questionMaster.question')}
                id="question-master-question"
                name="question"
                data-cy="question"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterTestApp.questionMaster.type')}
                id="question-master-type"
                name="type"
                data-cy="type"
                type="select"
              >
                <option value="MCQ">{translate('jhipsterTestApp.QuestionType.MCQ')}</option>
                <option value="VIDEO">{translate('jhipsterTestApp.QuestionType.VIDEO')}</option>
                <option value="CODING">{translate('jhipsterTestApp.QuestionType.CODING')}</option>
                <option value="ASSESSMENT">{translate('jhipsterTestApp.QuestionType.ASSESSMENT')}</option>
              </ValidatedField>
              <ValidatedField
                label={translate('jhipsterTestApp.questionMaster.complexity')}
                id="question-master-complexity"
                name="complexity"
                data-cy="complexity"
                type="select"
              >
                <option value="ENTRY">{translate('jhipsterTestApp.Complexity.ENTRY')}</option>
                <option value="JUNIOR">{translate('jhipsterTestApp.Complexity.JUNIOR')}</option>
                <option value="MID">{translate('jhipsterTestApp.Complexity.MID')}</option>
                <option value="SENIOR">{translate('jhipsterTestApp.Complexity.SENIOR')}</option>
                <option value="EXPERT">{translate('jhipsterTestApp.Complexity.EXPERT')}</option>
              </ValidatedField>
              <ValidatedField
                label={translate('jhipsterTestApp.questionMaster.experienceFrom')}
                id="question-master-experienceFrom"
                name="experienceFrom"
                data-cy="experienceFrom"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterTestApp.questionMaster.experienceTo')}
                id="question-master-experienceTo"
                name="experienceTo"
                data-cy="experienceTo"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterTestApp.questionMaster.category')}
                id="question-master-category"
                name="category"
                data-cy="category"
                type="select"
              >
                <option value="GENERAL">{translate('jhipsterTestApp.Category.GENERAL')}</option>
                <option value="TECHNICAL">{translate('jhipsterTestApp.Category.TECHNICAL')}</option>
                <option value="COMPREHENSION">{translate('jhipsterTestApp.Category.COMPREHENSION')}</option>
              </ValidatedField>
              <ValidatedField
                label={translate('jhipsterTestApp.questionMaster.reference')}
                id="question-master-reference"
                name="reference"
                data-cy="reference"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterTestApp.questionMaster.audiolink')}
                id="question-master-audiolink"
                name="audiolink"
                data-cy="audiolink"
                type="text"
              />
              <ValidatedField
                label={translate('jhipsterTestApp.questionMaster.skillId')}
                id="question-master-skillId"
                name="skillId"
                data-cy="skillId"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/question-master" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default QuestionMasterUpdate;
