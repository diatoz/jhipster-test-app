import React, { useEffect } from 'react';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { getEntity } from './skill.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

export const SkillDetail = (props: RouteComponentProps<{ id: string }>) => {
  const dispatch = useAppDispatch();

  useEffect(() => {
    dispatch(getEntity(props.match.params.id));
  }, []);

  const skillEntity = useAppSelector(state => state.skill.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="skillDetailsHeading">
          <Translate contentKey="jhipsterTestApp.skill.detail.title">Skill</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{skillEntity.id}</dd>
          <dt>
            <span id="skill">
              <Translate contentKey="jhipsterTestApp.skill.skill">Skill</Translate>
            </span>
          </dt>
          <dd>{skillEntity.skill}</dd>
          <dt>
            <span id="skillDesc">
              <Translate contentKey="jhipsterTestApp.skill.skillDesc">Skill Desc</Translate>
            </span>
          </dt>
          <dd>{skillEntity.skillDesc}</dd>
          <dt>
            <span id="icon">
              <Translate contentKey="jhipsterTestApp.skill.icon">Icon</Translate>
            </span>
          </dt>
          <dd>{skillEntity.icon}</dd>
          <dt>
            <span id="language">
              <Translate contentKey="jhipsterTestApp.skill.language">Language</Translate>
            </span>
          </dt>
          <dd>{skillEntity.language}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="jhipsterTestApp.skill.type">Type</Translate>
            </span>
          </dt>
          <dd>{skillEntity.type}</dd>
        </dl>
        <Button tag={Link} to="/skill" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/skill/${skillEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SkillDetail;
