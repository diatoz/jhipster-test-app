import { QuestionType } from 'app/shared/model/enumerations/question-type.model';
import { Complexity } from 'app/shared/model/enumerations/complexity.model';
import { Category } from 'app/shared/model/enumerations/category.model';

export interface IQuestionMaster {
  id?: string;
  questionId?: string | null;
  question?: string | null;
  type?: QuestionType | null;
  complexity?: Complexity | null;
  experienceFrom?: number | null;
  experienceTo?: number | null;
  category?: Category | null;
  reference?: string | null;
  audiolink?: string | null;
  skillId?: string | null;
}

export const defaultValue: Readonly<IQuestionMaster> = {};
