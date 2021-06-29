import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './question-master.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const QuestionMasterDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const questionMasterEntity = useAppSelector(state => state.questionMaster.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="questionMasterDetailsHeading">
          <Translate contentKey="jhipsterTestApp.questionMaster.detail.title">QuestionMaster</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{questionMasterEntity.id}</dd>
          <dt>
            <span id="questionId">
              <Translate contentKey="jhipsterTestApp.questionMaster.questionId">Question Id</Translate>
            </span>
          </dt>
          <dd>{questionMasterEntity.questionId}</dd>
          <dt>
            <span id="question">
              <Translate contentKey="jhipsterTestApp.questionMaster.question">Question</Translate>
            </span>
          </dt>
          <dd>{questionMasterEntity.question}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="jhipsterTestApp.questionMaster.type">Type</Translate>
            </span>
          </dt>
          <dd>{questionMasterEntity.type}</dd>
          <dt>
            <span id="complexity">
              <Translate contentKey="jhipsterTestApp.questionMaster.complexity">Complexity</Translate>
            </span>
          </dt>
          <dd>{questionMasterEntity.complexity}</dd>
          <dt>
            <span id="experienceFrom">
              <Translate contentKey="jhipsterTestApp.questionMaster.experienceFrom">Experience From</Translate>
            </span>
          </dt>
          <dd>{questionMasterEntity.experienceFrom}</dd>
          <dt>
            <span id="experienceTo">
              <Translate contentKey="jhipsterTestApp.questionMaster.experienceTo">Experience To</Translate>
            </span>
          </dt>
          <dd>{questionMasterEntity.experienceTo}</dd>
          <dt>
            <span id="category">
              <Translate contentKey="jhipsterTestApp.questionMaster.category">Category</Translate>
            </span>
          </dt>
          <dd>{questionMasterEntity.category}</dd>
          <dt>
            <span id="reference">
              <Translate contentKey="jhipsterTestApp.questionMaster.reference">Reference</Translate>
            </span>
          </dt>
          <dd>{questionMasterEntity.reference}</dd>
          <dt>
            <span id="audiolink">
              <Translate contentKey="jhipsterTestApp.questionMaster.audiolink">Audiolink</Translate>
            </span>
          </dt>
          <dd>{questionMasterEntity.audiolink}</dd>
          <dt>
            <span id="skillId">
              <Translate contentKey="jhipsterTestApp.questionMaster.skillId">Skill Id</Translate>
            </span>
          </dt>
          <dd>{questionMasterEntity.skillId}</dd>
        </dl>
        <Button tag={Link} to="/question-master" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/question-master/${questionMasterEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default QuestionMasterDetail;
