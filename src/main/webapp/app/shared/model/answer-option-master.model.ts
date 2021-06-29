export interface IAnswerOptionMaster {
  answerOptionId?: string | null;
  answerOption?: string | null;
  answerOptionOrder?: number | null;
  correctAnswer?: boolean | null;
}

export const defaultValue: Readonly<IAnswerOptionMaster> = {
  correctAnswer: false,
};
