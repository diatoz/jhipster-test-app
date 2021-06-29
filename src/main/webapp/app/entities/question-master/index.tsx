import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import QuestionMaster from './question-master';
import QuestionMasterDetail from './question-master-detail';
import QuestionMasterUpdate from './question-master-update';
import QuestionMasterDeleteDialog from './question-master-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={QuestionMasterUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={QuestionMasterUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={QuestionMasterDetail} />
      <ErrorBoundaryRoute path={match.url} component={QuestionMaster} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={QuestionMasterDeleteDialog} />
  </>
);

export default Routes;
